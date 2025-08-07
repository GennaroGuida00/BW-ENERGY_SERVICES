package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Indirizzo;
import Team3_BW.energy_services.payloads.NewIndirizzoDTO;
import Team3_BW.energy_services.services.IndirizzoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indirizzi")

public class IndirizziController {
    @Autowired
    IndirizzoService indirizzoService;

    @GetMapping
    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoService.findAll();
    }

    @GetMapping("/{id}")
    public Indirizzo getById(@PathVariable long id) {
        return indirizzoService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Indirizzo createIndirizzo(@Valid @RequestBody NewIndirizzoDTO indirizzoDTO) {
        return indirizzoService.add(indirizzoDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteIndirizzo(@PathVariable Long id) {

        indirizzoService.deleteById(id);
    }
}
