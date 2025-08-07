package Team3_BW.energy_services.services;


import Team3_BW.energy_services.entities.Fattura;
import Team3_BW.energy_services.payloads.NewClienteDTO;
import Team3_BW.energy_services.entities.Cliente;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.repositories.ClienteRepository;
import Team3_BW.energy_services.specifications.ClienteSpecification;
import Team3_BW.energy_services.specifications.FatturaSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
//    @Autowired
//    private PasswordEncoder bcrypt;

    public Cliente save(NewClienteDTO payload) {

        Cliente newCliente = new Cliente();
        newCliente.setRagioneSociale(payload.ragioneSociale());
        newCliente.setPartitaIva(payload.partitaIva());
        newCliente.setEmail(payload.email());
        newCliente.setDataInserimento(payload.dataInserimento());
        newCliente.setDataUltimoContatto(payload.dataUltimoContatto());
        newCliente.setFatturatoAnnuale(payload.fatturatoAnnuale());
        newCliente.setPec(payload.pec());
        newCliente.setTelefono(payload.telefono());
        newCliente.setEmailContatto(payload.emailContatto());
        newCliente.setNomeContatto(payload.nomeContatto());
        newCliente.setCognomeContatto(payload.cognomeContatto());
        newCliente.setTelefonoContatto(payload.telefonoContatto());
        newCliente.setLogoAziendale(payload.logoAziendale());
        newCliente.setTipologiaCliente(payload.tipologiaCliente());

        return clienteRepository.save(newCliente);
    }

    public Page<Cliente> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.clienteRepository.findAll(pageable);
    }

    public Cliente findById(long Id) {
        return this.clienteRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long clienteId) {
        Cliente found = this.findById(clienteId);
        this.clienteRepository.delete(found);
    }

    public Cliente findByIdAndUpdate(long clienteId, NewClienteDTO payload) {
        Cliente found = this.findById(clienteId);

        found.setRagioneSociale(payload.ragioneSociale());
        found.setPartitaIva(payload.partitaIva());
        found.setEmail(payload.email());
        found.setDataInserimento(payload.dataInserimento());
        found.setDataUltimoContatto(payload.dataUltimoContatto());
        found.setFatturatoAnnuale(payload.fatturatoAnnuale());
        found.setPec(payload.pec());
        found.setTelefono(payload.telefono());
        found.setEmailContatto(payload.emailContatto());
        found.setNomeContatto(payload.nomeContatto());
        found.setCognomeContatto(payload.cognomeContatto());
        found.setTelefonoContatto(payload.telefonoContatto());
        found.setLogoAziendale(payload.logoAziendale());
        found.setTipologiaCliente(payload.tipologiaCliente());

        return clienteRepository.save(found);
    }

    public List<Cliente> filterCliente(
           Integer fatturato,
           LocalDate dataInserimento,
            LocalDate dataUltimoContatto,
            String nome,
           String provincia,
           String tipoSede
    ) {
        Specification<Cliente> spec =
                ClienteSpecification.fatturatoAnnualeMinoreUguale(fatturato)
                        .and(ClienteSpecification.dataInserimentoPrimaDi(dataInserimento))
                        .and(ClienteSpecification.dataUltimoContattoPrimaDi(dataUltimoContatto))
                        .and(ClienteSpecification.nomeContattoContiene(nome))
                        .and(ClienteSpecification.hasProvinciaAndTipoSede(provincia, tipoSede));

        return clienteRepository.findAll(spec);
    }


}