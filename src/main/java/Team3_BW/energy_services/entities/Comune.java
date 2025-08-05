package Team3_BW.energy_services.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comuni")
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

    public Comune() {
    }

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
                '}';
    }

    public long getId() {
        return id;
    }

    public int getCodiceProvincia() {
        return codiceProvincia;
    }

    public void setCodiceProvincia(int codiceProvincia) {
        this.codiceProvincia = codiceProvincia;
    }

    public int getProgressivoDelComune() {
        return progressivoDelComune;
    }

    public void setProgressivoDelComune(int progressivoDelComune) {
        this.progressivoDelComune = progressivoDelComune;
    }

    public String getDenominazioneInItaliano() {
        return denominazioneInItaliano;
    }

    public void setDenominazioneInItaliano(String denominazioneInItaliano) {
        this.denominazioneInItaliano = denominazioneInItaliano;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvincia(Provincia provinciaToAdd) {
        this.provinciaList.add(provinciaToAdd);
    }
}