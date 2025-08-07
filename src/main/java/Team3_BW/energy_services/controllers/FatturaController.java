package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Fattura;
import Team3_BW.energy_services.exceptions.ValidationException;
import Team3_BW.energy_services.payloads.NewFatturaDTO;
import Team3_BW.energy_services.payloads.NewFatturaRespDTO;
import Team3_BW.energy_services.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy
    ) {
        return (Page<Fattura>) this.fatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/filter")
    public List<Fattura> filterFatture(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) Long statoId,
            @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) Double importoMin,
            @RequestParam(required = false) Double importoMax
    ) {
        return fatturaService.filterFatture(clienteId, statoId, data, anno, importoMin, importoMax);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public NewFatturaRespDTO save(@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList()
            );
        } else {
            Fattura newFattura = this.fatturaService.save(payload);
            return new NewFatturaRespDTO(newFattura.getId(), newFattura.getData(),
                    newFattura.getImporto()
            );
        }
    }

    @GetMapping("/{fatturaId}")
    public Fattura getById(@PathVariable long fatturaId) {
        return this.fatturaService.findById(fatturaId);
    }

    @PutMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura getByIdAndUpdate(@PathVariable long userId, @RequestBody NewFatturaDTO payload) {
        return this.fatturaService.findByIdAndUpdate(userId, payload);
    }

    @DeleteMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long fatturaId) {
        this.fatturaService.findByIdAndDelete(fatturaId);
    }

}