package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.payloads.NewClienteDTO;
import Team3_BW.energy_services.payloads.NewClienteRespDTO;
import Team3_BW.energy_services.entities.Cliente;
import Team3_BW.energy_services.services.ClienteService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<Cliente> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return clienteService.findAll(page, size, sortBy);
    }

    @GetMapping("/filterfatturato")
    public List<Cliente> findAllFilterFatturato(@RequestParam int fatturato){
        return clienteService.filterToFatturato(fatturato);
    }
    @GetMapping("/filterdatainserimento")
    public List<Cliente> findAllFilterDataInserimento(@RequestParam LocalDate dataInserimento) {
        return clienteService.filterToDataInserimento(dataInserimento);
    }
    @GetMapping("/filterNomeContatto")
    public List<Cliente> findAllFilterNomeContatto(@RequestParam String nomeContatto) {
        return clienteService.filterToNomeContatto(nomeContatto);
    }

    @GetMapping ("/filterDataUltimoContatto")
    public List<Cliente> findAllFilterDataUltimoContatto(@RequestParam LocalDate dataUltimoContatto) {
        return clienteService.filterToDataUltimoContatto(dataUltimoContatto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewClienteRespDTO save(@RequestBody @Validated NewClienteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        Cliente newCliente = clienteService.save(payload);
        return new NewClienteRespDTO(newCliente.getId());
    }

    @GetMapping("/{clienteId}")
//    @PreAuthorize("hasAuthority('')")
    public Cliente getById(@PathVariable long clienteId) {
        return clienteService.findById(clienteId);
    }

    @PutMapping("/{clienteId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente getByIdAndUpdate(@PathVariable long userId, @RequestBody NewClienteDTO payload) {
        return this.clienteService.findByIdAndUpdate(userId, payload);
    }

    @DeleteMapping("/{clienteId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long clienteId) {
        this.clienteService.findByIdAndDelete(clienteId);
    }
}