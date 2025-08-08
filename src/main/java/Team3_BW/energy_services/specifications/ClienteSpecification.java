package Team3_BW.energy_services.specifications;

import Team3_BW.energy_services.entities.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


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

    public static Specification<Cliente> hasProvinciaAndTipoSede() {
        return (root, query, cb) -> {

            Join<Cliente, Sede> sedeJoin = root.join("sedi", JoinType.INNER);
            Join<Sede, Indirizzo> indirizzoJoin = sedeJoin.join("indirizzo", JoinType.INNER);
            Join<Indirizzo, Comune> comuneJoin = indirizzoJoin.join("nome_comune", JoinType.INNER);
            Join<Comune, Provincia> provinciaJoin = comuneJoin.join("nomeComune", JoinType.INNER);

            query.orderBy(
                    cb.asc(provinciaJoin.get("nome")),
                    cb.asc(sedeJoin.get("tipoSede"))
            );

            return cb.conjunction();
        };
    }
}
