package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.payloads.UtenteDTO;
import Team3_BW.energy_services.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> findById(Long id) {
        return utenteRepository.findById(id);
    }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findByIdOrThrow(Long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Utente save(UtenteDTO dto) {
        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setAvatar(dto.avatar());
        return utenteRepository.save(utente);
    }


    public Utente findByIdAndUpdate(long id, UtenteDTO dto) {
        Utente found = utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        found.setUsername(dto.username());
        found.setEmail(dto.email());
        found.setNome(dto.nome());
        found.setCognome(dto.cognome());
        found.setAvatar(dto.avatar());
        return utenteRepository.save(found);
    }

    public void findByIdAndDelete(Long id) {
        Utente utente = findByIdOrThrow(id);
        utenteRepository.delete(utente);
    }

    public void deleteById(Long id) {
        utenteRepository.deleteById(id);
    }
}
