package Team3_BW.energy_services.security;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

//    la classe JWTTools utilizza due metodi: uno per creare e uno per verificare il token
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente utente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))//data di emissione
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))//data di scadenza
                .subject(String.valueOf(utente.getId()))//Subject, oggetto a cui specifichiamo a chi appartiene il token
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))// Firmo il token tramite un algoritmo specifico che si chiama HMAC, passandogli il segreto
                .compact();// Assemblo il tutto ottenendo la stringa finale che sarà il token!
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("token non valido");
        }
    }

    public String extractIdFromToken(String accessToken) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getSubject();
    }
}
