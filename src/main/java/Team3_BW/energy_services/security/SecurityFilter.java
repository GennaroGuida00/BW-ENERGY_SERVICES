package Team3_BW.energy_services.security;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.exceptions.UnauthorizedException;
import Team3_BW.energy_services.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Questo è il metodo che verrà richiamato ad ogni richiesta che dovrà verificare il token (quindi a parte /login e /register)
        // Questo filtro sarà responsabile di recuperare il token, verificare che questo sia valido, se tutto è ok mandare avanti la richiesta
        // Il token sarà posizionato negli headers (Authorization header)

        // *********************************************** AUTENTICAZIONE ***************************************************

        // 1. Verifichiamo se nella richiesta è presente l'Authorization Header e se esso è ben formato ("Bearer 34j1k2lkjxcljxkjclkj..."), se
        // non c'è oppure se non ha il formato giusto --> 401
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire token in formato valido");

        // 2. Estraiamo il token dall'header
        String accessToken = authHeader.replace("Bearer ", "");

        // 3. Verifichiamo se il token è ok, cioè: controlliamo se è stato manipolato (tramite signature), o se è scaduto (tramite Expiration Date)
        jwtTools.verifyToken(accessToken);

        // ****************************************** AUTORIZZAZIONE *******************************************************

        // 1. Cerco l'utente nel db tramite id (l'id sta nel token)
        String utenteId = jwtTools.extractIdFromToken(accessToken);
        Utente utenteCorrente = this.utenteService.findById(Long.parseLong(utenteId));

        //creo un oggetto dell'interfaccia Authentication che contenga:
        //L'utente che vuole svolgere le prossime operazioni HTTP
        //le credenziali ( che sono null , perchè le ricaviaamo dal token)
        // la lista dei ruoli che l'utente potrebbe avere
        Authentication authentication = new UsernamePasswordAuthenticationToken(utenteCorrente, null, utenteCorrente.getAuthorities());

        //classe contentitore alla quale viene caricato all'interno l'utente in questione per tenere traccia dei permessi all'interno dei controller
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //passa il controllo al prossimo filtro nella catena
        //in pratica dice : "Ho verificato e autenticato l’utente con il JWT, ora lascio continuare la richiesta fino ad arrivare al controller"
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
        // Ignoriamo il filtro per tutte le richieste su http://localhost:3001/auth/.....
    }
}