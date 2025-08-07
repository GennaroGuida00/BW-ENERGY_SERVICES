package Team3_BW.energy_services.controllers;

import Team3_BW.energy_services.entities.Cliente;
import Team3_BW.energy_services.exceptions.ValidationException;
import Team3_BW.energy_services.payloads.NewClienteDTO;
import Team3_BW.energy_services.payloads.NewClienteRespDTO;
import Team3_BW.energy_services.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/filter")
    public List<Cliente> filterCliente(
            @RequestParam(required = false) Integer fatturato,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate dataUltimoContatto,
            @RequestParam(required = false) String nome
    ) {
        return clienteService.filterCliente(fatturato, dataInserimento, dataUltimoContatto, nome);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewClienteRespDTO save(@RequestBody @Validated NewClienteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList()
            );
        } else {
            Cliente newCliente = clienteService.save(payload);
            return new NewClienteRespDTO(newCliente.getId());
        }

    }

    @GetMapping("/{clienteId}")
//    @PreAuthorize("hasAuthority('')")
    public Cliente getById(@PathVariable long clienteId) {
        return clienteService.findById(clienteId);
    }

    @PutMapping("/{clienteId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente getByIdAndUpdate(@PathVariable long clienteId, @RequestBody NewClienteDTO payload) {
        return this.clienteService.findByIdAndUpdate(clienteId, payload);
    }

    @DeleteMapping("/{clienteId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long clienteId) {
        this.clienteService.findByIdAndDelete(clienteId);
    }
}