package Team3_BW.energy_services.services;


import Team3_BW.energy_services.Payloads.NewFatturaDTO;
import Team3_BW.energy_services.entities.Fattura;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.repositories.FatturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    @Slf4j
    public class FatturaService {

        @Autowired
        private FatturaRepository fatturaRepository;
        @Autowired
        private PasswordEncoder bcrypt;

        public Fattura save(NewFatturaDTO payload) {

            Fattura newFattura = new Fattura();
            newFattura.setImporto(payload.importo());
            newFattura.setData(payload.data());

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

            Fattura modifiedFattura = this.fatturaRepository.save(found);

            log.info("La fattura con id " + found.getId() + " è stato modificato!");

            return modifiedFattura;
        }
    }

