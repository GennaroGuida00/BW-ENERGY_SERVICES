package Team3_BW.energy_services.services;


import Team3_BW.energy_services.entities.Cliente;
import Team3_BW.energy_services.payloads.NewFatturaDTO;
import Team3_BW.energy_services.entities.Fattura;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.repositories.ClienteRepository;
import Team3_BW.energy_services.repositories.FatturaRepository;
import Team3_BW.energy_services.repositories.StatoFatturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
    @Slf4j
    public class FatturaService {

        @Autowired
        private FatturaRepository fatturaRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private StatoFatturaRepository statoFatturaRepository;


//        @Autowired
//        private PasswordEncoder bcrypt;

        public Fattura save(NewFatturaDTO payload) {

            Fattura newFattura = new Fattura();
            newFattura.setImporto(payload.importo());
            newFattura.setData(payload.data());
            newFattura.setCliente(clienteRepository.findById(payload.id_cliente()).orElseThrow(()->new NotFoundException(payload.id_cliente())));
            newFattura.setStatoFattura(statoFatturaRepository.findById(payload.id_statoFattura()).orElseThrow(()->new NotFoundException(payload.id_statoFattura())));
            return fatturaRepository.save(newFattura);

        }

        public Page<Fattura> findAll(int pageNumber, int pageSize, String sortBy) {
            if (pageSize > 50) pageSize = 50;
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
            return this.fatturaRepository.findAll(pageable);
        }

        public Fattura findById(long fatturaId) {
            return this.fatturaRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
        }

        public void findByIdAndDelete(long fatturaId) {
            Fattura found = this.findById(fatturaId);
            this.fatturaRepository.delete(found);
        }

        public Fattura findByIdAndUpdate(long fatturaId, NewFatturaDTO payload) {
            Fattura found = this.findById(fatturaId);

            found.setData(payload.data());
            found.setImporto(payload.importo());

            return  fatturaRepository.save(found);
        }

        public List<Fattura> filterToCliente(long id){
            Cliente cliente=clienteRepository.findById(id).orElseThrow(()->new NotFoundException(id));
            return fatturaRepository.filterToClient(cliente);
        }

        public List<Fattura> filterToStato(long id){
            return fatturaRepository.filterToStato(id);
        }

    public List<Fattura> filterToDate(LocalDate data){
        return fatturaRepository.filterToDate(data);
    }

    public List<Fattura> filterToYear(int year){
        return fatturaRepository.findByAnno(year);
    }

    public List<Fattura> filterToRangeImport(double importoA,double importoB){
        return fatturaRepository.findByRangeImport(importoA,importoB);
    }


    }

