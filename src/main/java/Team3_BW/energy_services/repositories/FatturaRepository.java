package Team3_BW.energy_services.repositories;
import Team3_BW.energy_services.entities.Cliente;
import Team3_BW.energy_services.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    @Query("SELECT f FROM Fattura f WHERE f.cliente=:cliente")
    List<Fattura> filterToClient (@Param("cliente")Cliente cliente);

    @Query("SELECT f FROM Fattura f WHERE f.statoFattura.id=:id")
    List<Fattura> filterToStato (@Param("id") long id);

    @Query("SELECT f from Fattura f WHERE f.data<=:data")
    List<Fattura> filterToDate(@Param("data")LocalDate data);
}