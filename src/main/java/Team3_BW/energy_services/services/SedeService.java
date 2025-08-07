package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Sede;
import Team3_BW.energy_services.enums.TipoSede;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.payloads.NewSedeDTO;
import Team3_BW.energy_services.repositories.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeService {

    @Autowired
    SedeRepository sedeRepository;

    public Sede findById(long id){
        return sedeRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public List<Sede> findAll(){
        return sedeRepository.findAll();
    }

    public void deleteById(long id){
        sedeRepository.deleteById(id);
    }

    public Sede add(NewSedeDTO sedeDTO){
        Sede sede=new Sede();
        sede.setType(TipoSede.valueOf(sedeDTO.tipoSede()));
       return sedeRepository.save(sede);

    }
}
