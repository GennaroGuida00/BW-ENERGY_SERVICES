package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.payloads.UtenteDTO;
import Team3_BW.energy_services.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<UtenteDTO> getAll() {
        return utenteService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getById(@PathVariable Long id) {
        return utenteService.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UtenteDTO create(@RequestBody UtenteDTO utenteDTO) {
        Utente utente = fromDTO(utenteDTO);
        Utente saved = utenteService.save(utente);
        return toDTO(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> update(@PathVariable Long id, @RequestBody UtenteDTO utenteDTO) {
        return utenteService.findById(id)
                .map(u -> {
                    u.setNome(utenteDTO.nome());
                    u.setCognome(utenteDTO.cognome());
                    u.setEmail(utenteDTO.email());
                    u.setUsername(utenteDTO.username());
                    u.setAvatar(utenteDTO.avatar());
                    Utente updated = utenteService.save(u);
                    return ResponseEntity.ok(toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (utenteService.findById(id).isPresent()) {
            utenteService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private UtenteDTO toDTO(Utente utente) {
        Set<String> ruoli = utente.getRuoli().stream().map(r -> r.getNome()).collect(Collectors.toSet());
        return new UtenteDTO(
                utente.getId(),
                utente.getUsername(),
                utente.getEmail(),
                utente.getNome(),
                utente.getCognome(),
                utente.getAvatar(),
                ruoli
        );
    }

    private Utente fromDTO(UtenteDTO dto) {
        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setAvatar(dto.avatar());
        return utente;
    }
}
