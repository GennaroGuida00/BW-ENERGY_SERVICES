package Team3_BW.energy_services.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provincie")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String sigla;
    private String provincia;
    private String regione;

    public Provincia(String sigla, String provincia, String regione) {
        this.sigla = sigla;
        this.provincia = provincia;
        this.regione = regione;
    }
}
