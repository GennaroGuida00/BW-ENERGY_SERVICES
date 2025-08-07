package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Indirizzo;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.payloads.NewIndirizzoDTO;
import Team3_BW.energy_services.repositories.ComuneRepo;
import Team3_BW.energy_services.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoService {

    @Autowired
    IndirizzoRepository indirizzoRepository;

    @Autowired
    ComuneRepo comuneRepo;

    public Indirizzo findById(long id){
        return indirizzoRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public List<Indirizzo> findAll(){
        return indirizzoRepository.findAll();
    }

    public void deleteById(long id){
        indirizzoRepository.deleteById(id);
    }

    public Indirizzo add(NewIndirizzoDTO indirizzoDTO){
        Indirizzo indirizzo=new Indirizzo();
        indirizzo.setVia(indirizzoDTO.via());
        indirizzo.setCivico(indirizzoDTO.civico());
        indirizzo.setCap(indirizzoDTO.cap());
        indirizzo.setLocalità(indirizzoDTO.località());
        indirizzo.setNome_comune(comuneRepo.findById(indirizzoDTO.id_comune()).orElseThrow(()->new NotFoundException(indirizzoDTO.id_comune())));
         return indirizzoRepository.save(indirizzo);
    }


}
