package Team3_BW.energy_services.repositories;
import Team3_BW.energy_services.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale<=:fatturatoAnnuale")
    List<Cliente> filterToFatturatoAnnuale(@Param("fatturatoAnnuale") int fatturatoAnnuale);

    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento<=:dataInserimento")
    List<Cliente> filterToDataInserimento(@Param("dataInserimento")LocalDate dataInserimento);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto<=:dataUltimoContatto")
    List<Cliente> filterToDataUltimoContatto(@Param("dataUltimoContatto")LocalDate dataUltimoContatto);

    @Query("Select c FROM Cliente c Where c.nomeContatto LIKE%:nomeContatto%")
    List<Cliente> filterToNomeContatto(@Param("NomeContatto") String NomeContatto);
}


