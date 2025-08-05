package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.StatoFattura;
import Team3_BW.energy_services.payloads.StatoFatturaDTO;
import Team3_BW.energy_services.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statifattura")
public class StatoFatturaController {
    @Autowired
    private StatoFatturaService statoFatturaService;

    @GetMapping
    public List<StatoFatturaDTO> getAll() {
        return statoFatturaService.findAll().stream()
                .map(sf -> new StatoFatturaDTO(sf.getId(), sf.getNome()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatoFatturaDTO> getById(@PathVariable Long id) {
        return statoFatturaService.findById(id)
                .map(sf -> ResponseEntity.ok(new StatoFatturaDTO(sf.getId(), sf.getNome())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StatoFatturaDTO create(@RequestBody StatoFatturaDTO statoFatturaDTO) {
        StatoFattura statoFattura = new StatoFattura();
        statoFattura.setNome(statoFatturaDTO.nome());
        StatoFattura saved = statoFatturaService.save(statoFattura);
        return new StatoFatturaDTO(saved.getId(), saved.getNome());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatoFatturaDTO> update(@PathVariable Long id, @RequestBody StatoFatturaDTO statoFatturaDTO) {
        return statoFatturaService.findById(id)
                .map(sf -> {
                    sf.setNome(statoFatturaDTO.nome());
                    StatoFattura updated = statoFatturaService.save(sf);
                    return ResponseEntity.ok(new StatoFatturaDTO(updated.getId(), updated.getNome()));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (statoFatturaService.findById(id).isPresent()) {
            statoFatturaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
