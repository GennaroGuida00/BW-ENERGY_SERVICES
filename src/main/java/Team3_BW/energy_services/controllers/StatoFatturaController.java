package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.StatoFattura;
import Team3_BW.energy_services.exceptions.ValidationException;
import Team3_BW.energy_services.payloads.StatoFatturaDTO;
import Team3_BW.energy_services.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statifattura")
public class StatoFatturaController {
    @Autowired
    private StatoFatturaService statoFatturaService;

    @GetMapping
    public Page<StatoFatturaDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "20") int size,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        return statoFatturaService.findAll(page, size, sortBy)
                .map(sf -> new StatoFatturaDTO(sf.getId(), sf.getNome()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatoFatturaDTO save(@RequestBody @Validated StatoFatturaDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        StatoFattura newStato = new StatoFattura();
        newStato.setNome(payload.nome());
        StatoFattura saved = statoFatturaService.save(newStato);
        return new StatoFatturaDTO(saved.getId(), saved.getNome());
    }

    @GetMapping("/{id}")
    public StatoFatturaDTO getById(@PathVariable long id) {
        StatoFattura sf = statoFatturaService.findByIdOrThrow(id);
        return new StatoFatturaDTO(sf.getId(), sf.getNome());
    }

    @PutMapping("/{id}")
    public StatoFatturaDTO getByIdAndUpdate(@PathVariable long id, @RequestBody StatoFatturaDTO payload) {
        StatoFattura updated = statoFatturaService.findByIdAndUpdate(id, payload);
        return new StatoFatturaDTO(updated.getId(), updated.getNome());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long id) {
        statoFatturaService.findByIdAndDelete(id);
    }
}
