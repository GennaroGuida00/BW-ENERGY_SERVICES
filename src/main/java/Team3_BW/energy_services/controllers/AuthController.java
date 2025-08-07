package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.exceptions.ValidationException;
import Team3_BW.energy_services.payloads.LoginDTO;
import Team3_BW.energy_services.payloads.LoginRespDTO;
import Team3_BW.energy_services.payloads.NewUtenteRespDTO;
import Team3_BW.energy_services.payloads.UtenteDTO;
import Team3_BW.energy_services.services.AuthService;
import Team3_BW.energy_services.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginDTO body) {
        String accessToken = authService.checkCredentialsAndGenerateToken(body);
        return new LoginRespDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO save(@RequestBody @Validated UtenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            //validationResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Utente newUtente = this.utenteService.save(payload);
            return new NewUtenteRespDTO(newUtente.getId());
        }

    }

}
