package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.StatoFattura;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.payloads.StatoFatturaDTO;
import Team3_BW.energy_services.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public List<StatoFattura> findAll() {
        return statoFatturaRepository.findAll();
    }

    public Page<StatoFattura> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return statoFatturaRepository.findAll(pageable);
    }

    public Optional<StatoFattura> findById(Long id) {
        return statoFatturaRepository.findById(id);
    }

    public StatoFattura findByIdOrThrow(Long id) {
        return statoFatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Stato fattura non trovato"));
    }

    public StatoFattura save(StatoFattura statoFattura) {
        return statoFatturaRepository.save(statoFattura);
    }

    public StatoFattura findByIdAndUpdate(Long id, StatoFatturaDTO dto) {
        StatoFattura stato = findByIdOrThrow(id);
        stato.setNome(dto.nome());
        return statoFatturaRepository.save(stato);
    }

    public void findByIdAndDelete(Long id) {
        StatoFattura stato = findByIdOrThrow(id);
        statoFatturaRepository.delete(stato);
    }

    public void deleteById(Long id) {
        statoFatturaRepository.deleteById(id);
    }
}
