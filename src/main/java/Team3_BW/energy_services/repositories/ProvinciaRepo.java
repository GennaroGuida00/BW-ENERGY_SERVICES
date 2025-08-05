package Team3_BW.energy_services.repositories;

import Team3_BW.energy_services.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepo extends JpaRepository<Provincia, Long> {
    Optional<Provincia> findByProvincia(String nome);
}
