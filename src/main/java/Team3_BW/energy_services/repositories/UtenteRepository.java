package Team3_BW.energy_services.repositories;

import Team3_BW.energy_services.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findById(long id);
    Optional<Utente> findByEmail(String email);
}

