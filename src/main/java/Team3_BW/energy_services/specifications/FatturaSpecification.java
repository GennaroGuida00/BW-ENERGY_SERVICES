package Team3_BW.energy_services.specifications;

import Team3_BW.energy_services.entities.Fattura;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FatturaSpecification {

    public static Specification<Fattura> hasCliente(Long clienteId) {
        return (root, query, cb) ->
                clienteId == null ? null : cb.equal(root.get("cliente").get("id"), clienteId);
    }

    public static Specification<Fattura> hasStatoFattura(Long statoId) {
        return (root, query, cb) ->
                statoId == null ? null : cb.equal(root.get("statoFattura").get("id"), statoId);
    }

    public static Specification<Fattura> hasAnno(int anno) {
        return (root, query, cb) -> cb.equal(
                cb.function("date_part", Integer.class, cb.literal("year"), root.get("data")),
                anno
        );
    }


    public static Specification<Fattura> hasData(LocalDate data) {
        return (root, query, cb) ->
                data == null ? null : cb.equal(root.get("data"), data);
    }

    public static Specification<Fattura> hasImportoBetween(Double min, Double max) {
        return (root, query, cb) -> {
            if (min != null && max != null) return cb.between(root.get("importo"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("importo"), min);
            if (max != null) return cb.lessThanOrEqualTo(root.get("importo"), max);
            return null;
        };
    }
}

