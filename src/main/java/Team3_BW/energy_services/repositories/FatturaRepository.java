package Team3_BW.energy_services.repositories;
import Team3_BW.energy_services.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}