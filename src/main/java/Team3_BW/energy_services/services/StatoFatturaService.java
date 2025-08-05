package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.StatoFattura;
import Team3_BW.energy_services.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<StatoFattura> findById(Long id) {
        return statoFatturaRepository.findById(id);
    }

    public StatoFattura save(StatoFattura statoFattura) {
        return statoFatturaRepository.save(statoFattura);
    }

    public void deleteById(Long id) {
        statoFatturaRepository.deleteById(id);
    }
}

