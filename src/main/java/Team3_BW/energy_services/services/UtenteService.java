package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Utente;
import Team3_BW.energy_services.exceptions.BadRequestException;
import Team3_BW.energy_services.exceptions.NotFoundException;
import Team3_BW.energy_services.payloads.UtenteDTO;
import Team3_BW.energy_services.repositories.UtenteRepository;
import io.jsonwebtoken.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findByIdOrThrow(Long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Utente save(UtenteDTO dto) {
        this.utenteRepository.findByEmail(dto.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });
        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setEmail(dto.email());
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setAvatar(dto.avatar());
        utente.setPassword(bcrypt.encode(dto.password()));
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
        Utente utente = findByIdOrThrow(id);
        utenteRepository.delete(utente);
    }

    public void deleteById(Long id) {
        utenteRepository.deleteById(id);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }
}
