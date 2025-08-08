package Team3_BW.energy_services.repositories;
import Team3_BW.energy_services.entities.Cliente;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

    @Query("""
    select c from Cliente c
    join c.sedi s
    join s.indirizzo i
    join i.nome_comune com
    join com.provinciaRef p
    where s.tipoSede = 'LEGALE'
    order by p.provincia asc, s.tipoSede asc
""")
    List<Cliente> findAllOrderByProvinciaAndTipoSede();

}


