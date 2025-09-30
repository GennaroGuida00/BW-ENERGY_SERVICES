package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Ruolo;
import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.exceptions.BadRequestException;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.payloads.UtenteDTO;
import Team3_BW.energy_services.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private RuoloService ruoloService;

    public Utente findByIdAndUpdateRuolo(Long utenteId) {
        Utente u = findById(utenteId);
        Ruolo adminFromDb = ruoloService.findById(Long.parseLong("2"));
        u.getRuoli().add(adminFromDb);
        utenteRepository.save(u);
        return u;
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente save(UtenteDTO dto) {
        this.utenteRepository.findByEmail(dto.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });
        List<Ruolo> ruoloList = new ArrayList<>();
        Ruolo rFromDb = ruoloService.findById(Long.parseLong("1"));
        ruoloList.add(rFromDb);
//        Ruolo adminFromDb = ruoloService.findById(Long.parseLong("2"));
//        ruoloList.add(adminFromDb);

        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setAvatar(dto.avatar());
        utente.setPassword(bcrypt.encode(dto.password()));
        utente.setRuoli(ruoloList);
        return utenteRepository.save(utente);
    }

    public Utente findByIdAndUpdate(long id, UtenteDTO dto) {
        Utente found = utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (!found.getEmail().equals(dto.email()))
            this.utenteRepository.findByEmail(dto.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            });

        found.setUsername(dto.username());
        found.setEmail(dto.email());
        found.setNome(dto.nome());
        found.setCognome(dto.cognome());
        found.setAvatar(dto.avatar());
        found.setPassword(bcrypt.encode(dto.password()));
        return utenteRepository.save(found);

    }

    public void findByIdAndDelete(Long id) {
        Utente utente = findById(id);
        utenteRepository.delete(utente);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }
}
