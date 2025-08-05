package Team3_BW.energy_services.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private String nomeComune;

    @OneToMany
    @JoinColumn(name = "provincia_id")
    private List<Provincia> provinciaList = new ArrayList<>();


    public Comune(String comune, String denominazioneInItaliano, int progressivoDelComune, int codiceProvincia) {
        this.nomeComune = comune;
        this.denominazioneInItaliano = denominazioneInItaliano;
        this.progressivoDelComune = progressivoDelComune;
        this.codiceProvincia = codiceProvincia;
    }

    @Override
    public String toString() {
        return "Comune{" +
                "id=" + id +
                ", codiceProvincia=" + codiceProvincia +
                ", progressivoDelComune=" + progressivoDelComune +
                ", denominazioneInItaliano='" + denominazioneInItaliano + '\'' +
                ", nomeComune='" + nomeComune + '\'' +
                ", provinciaList=" + provinciaList +
                '}';
    }
}