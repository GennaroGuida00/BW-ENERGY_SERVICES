package entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "comuni")
@NoArgsConstructor
@Getter
@Setter
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private long id;
    private int codiceProvincia;
    private int progressivoDelComune;
    private String denominazioneInItaliano;
    private String comune;

    @OneToMany
    @JoinColumn(name = "provincia_id")
    private List<Provincia> provincia;

    public Comune(List<Provincia> provincia, String comune, String denominazioneInItaliano, int progressivoDelComune, int codiceProvincia) {
        this.provincia = provincia;
        this.comune = comune;
        this.denominazioneInItaliano = denominazioneInItaliano;
        this.progressivoDelComune = progressivoDelComune;
        this.codiceProvincia = codiceProvincia;
    }
}