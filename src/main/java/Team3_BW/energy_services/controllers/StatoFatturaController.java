package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.StatoFattura;
import Team3_BW.energy_services.exceptions.ValidationException;
import Team3_BW.energy_services.payloads.NewStatoFatturaDTO;
import Team3_BW.energy_services.payloads.NewStatoFatturaRespDTO;
import Team3_BW.energy_services.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statofatture")
public class StatoFatturaController {
    @Autowired
    private StatoFatturaService statoFatturaService;

    @GetMapping
    public Page<StatoFattura> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "20") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return statoFatturaService.findAll(page, size, sortBy);
    }

    @PostMapping("/salvastatofattura")
    @ResponseStatus(HttpStatus.CREATED)
    public NewStatoFatturaRespDTO save(@RequestBody @Validated NewStatoFatturaDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList()
            );
        }
        StatoFattura newStato = statoFatturaService.save(payload);
        ;
        return new NewStatoFatturaRespDTO(newStato.getId());
    }

    @GetMapping("/{id}")
    public StatoFattura getById(@PathVariable long id) {
        return statoFatturaService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StatoFattura getByIdAndUpdate(@PathVariable long id, @RequestBody NewStatoFatturaDTO payload) {

        return statoFatturaService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void getByIdAndDelete(@PathVariable long id) {
        statoFatturaService.findByIdAndDelete(id);
    }
}
