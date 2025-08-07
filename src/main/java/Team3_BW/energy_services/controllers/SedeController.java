package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Sede;
import Team3_BW.energy_services.payloads.NewSedeDTO;
import Team3_BW.energy_services.services.SedeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sedi")

public class SedeController {

    @Autowired
    private SedeService sedeService;

    @GetMapping
    public List<Sede> getAllSedi() {
        return sedeService.findAll();
    }

    @GetMapping("/{id}")
    public Sede getById(@PathVariable long id) {
        return sedeService.findById(id);
    }

    @PostMapping("/salvasede")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Sede createSede(@Valid @RequestBody NewSedeDTO sedeDTO) {
        return sedeService.add(sedeDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletesede(@PathVariable Long id) {

        sedeService.deleteById(id);
    }

}
