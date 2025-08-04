package Team3_BW.energy_services.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stato_fattura")
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fattura_id")
    private Fattura fattura;
    @Column(nullable = false)
    private String tipologiaFatture;

    public StatoFattura() {
    }

    public StatoFattura(String tipologiaFatture) {
        this.tipologiaFatture = tipologiaFatture;
    }

    public Long getId() {
        return id;
    }

    public String getTipologiaFatture() {
        return tipologiaFatture;
    }

    public void setTipologiaFatture(String tipologiaFatture) {
        this.tipologiaFatture = tipologiaFatture;
    }

    @Override
    public String toString() {
        return "StatoFattura{" +
                "id=" + id +
                ", fattura=" + fattura +
                ", tipologiaFatture='" + tipologiaFatture + '\'' +
                '}';
    }
}

