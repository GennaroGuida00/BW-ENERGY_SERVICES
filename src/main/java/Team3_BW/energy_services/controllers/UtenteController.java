package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.payloads.UtenteDTO;
import Team3_BW.energy_services.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.findAll(page, size, sortBy);

    }

    @GetMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente getById(@PathVariable long utenteId) {
        return utenteService.findById(utenteId);
    }

    @PutMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente getByIdAndUpdate(@PathVariable long utenteId, @RequestBody UtenteDTO payload) {
        return utenteService.findByIdAndUpdate(utenteId, payload);
    }

    @DeleteMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long utenteId) {
        utenteService.findByIdAndDelete(utenteId);
    }
}


