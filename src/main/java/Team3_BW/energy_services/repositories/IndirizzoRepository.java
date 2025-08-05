package Team3_BW.energy_services.repositories;

import Team3_BW.energy_services.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo,Long> {
    Optional<Indirizzo> findById(long id);
}
