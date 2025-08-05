package Team3_BW.energy_services.controlers;

import Team3_BW.energy_services.entities.Ruolo;
import Team3_BW.energy_services.services.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ruoli")
public class RuoloController {
    @Autowired
    private RuoloService ruoloService;

    @GetMapping
    public List<Ruolo> getAll() {
        return ruoloService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruolo> getById(@PathVariable Long id) {
        return ruoloService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ruolo create(@RequestBody Ruolo ruolo) {
        return ruoloService.save(ruolo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ruolo> update(@PathVariable Long id, @RequestBody Ruolo ruolo) {
        return ruoloService.findById(id)
                .map(existingRuolo -> {
                    existingRuolo.setNome(ruolo.getNome());
                    // add altro, da aggiornare se necessario
                    return ResponseEntity.ok(ruoloService.save(existingRuolo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ruoloService.findById(id).isPresent()) {
            ruoloService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

