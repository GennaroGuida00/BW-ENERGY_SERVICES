package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Ruolo;
import Team3_BW.energy_services.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloService {
    @Autowired
    private RuoloRepository ruoloRepository;

    public Ruolo save(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }

}
