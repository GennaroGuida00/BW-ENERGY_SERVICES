package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.payloads.UtenteDTO;
import Team3_BW.energy_services.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.findAll(page, size, sortBy);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteDTO save(@RequestBody @Validated UtenteDTO payload) {
        Utente newUtente = utenteService.save(payload);
        return new UtenteDTO(newUtente.getId());
    }
    }

    @GetMapping("/{utenteId}")
    public UtenteDTO getById(@PathVariable long utenteId) {
        return toDTO(utenteService.findByIdOrThrow(utenteId));
    }

    @PutMapping("/{utenteId}")
    public UtenteDTO getByIdAndUpdate(@PathVariable long utenteId, @RequestBody UtenteDTO payload) {
        return toDTO(utenteService.findByIdAndUpdate(utenteId, payload));
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long utenteId) {
        utenteService.findByIdAndDelete(utenteId);
    }


}
