package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.exceptions.UnauthorizedException;
import Team3_BW.energy_services.payloads.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.utenteService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
