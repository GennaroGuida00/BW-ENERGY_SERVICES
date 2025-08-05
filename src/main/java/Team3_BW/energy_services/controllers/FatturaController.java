package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.Payloads.NewFatturaDTO;
import Team3_BW.energy_services.Payloads.NewFatturaRespDTO;
import Team3_BW.energy_services.entities.Fattura;
import Team3_BW.energy_services.services.FatturaService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    @PreAuthorize("hasAuthority('Utente_Normale')")
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy
    ) {
        return (Page<Fattura>) this.fatturaService.findAll(page, size, sortBy);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('')")
    public NewFatturaRespDTO save(@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Errore");
        } else {
            Fattura newFattura = this.fatturaService.save(payload);
            return new NewFatturaRespDTO(newFattura.getId(), newFattura.getData(),
                    newFattura.getImporto()
            );
        }
    }

    @GetMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('')")
    public Fattura getById(@PathVariable long fatturaId) {
        return this.fatturaService.findById(fatturaId);
    }

}