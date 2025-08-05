package Team3_BW.energy_services.repositories;

import Team3_BW.energy_services.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SedeRepository extends JpaRepository<Sede,Long> {
    Optional<Sede> findById(long id);
}
