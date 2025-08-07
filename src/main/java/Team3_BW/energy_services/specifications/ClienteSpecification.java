package Team3_BW.energy_services.specifications;

import org.springframework.data.jpa.domain.Specification;
import Team3_BW.energy_services.entities.Cliente;


import java.time.LocalDate;

public class ClienteSpecification {

    public static Specification<Cliente> fatturatoAnnualeMinoreUguale(Integer fatturato) {
        return (root, query, cb) ->
                fatturato == null ? null : cb.lessThanOrEqualTo(root.get("fatturatoAnnuale"), fatturato);
    }

    public static Specification<Cliente> dataInserimentoPrimaDi(LocalDate dataInserimento) {
        return (root, query, cb) ->
                dataInserimento == null ? null : cb.lessThanOrEqualTo(root.get("dataInserimento"), dataInserimento);
    }

    public static Specification<Cliente> dataUltimoContattoPrimaDi(LocalDate dataUltimoContatto) {
        return (root, query, cb) ->
                dataUltimoContatto == null ? null : cb.lessThanOrEqualTo(root.get("dataUltimoContatto"), dataUltimoContatto);
    }

    public static Specification<Cliente> nomeContattoContiene(String nome) {
        return (root, query, cb) ->
                nome == null || nome.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("nomeContatto")), nome.toLowerCase() + "%");
    }
}
