package Team3_BW.energy_services.entities;

import Team3_BW.energy_services.enums.TipoSede;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "sedi")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoSede tipoSede;

    @OneToOne
    @JoinColumn(name = "id_address")
    @JsonIgnore
    private Indirizzo indirizzo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private Cliente cliente;

    public Sede() {
    }

    public Sede(TipoSede tipoSede, Indirizzo indirizzo, Cliente cliente) {
        this.tipoSede = tipoSede;
        this.indirizzo = indirizzo;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public TipoSede getType() {
        return tipoSede;
    }

    public void setType(TipoSede tipoSede) {
        this.tipoSede = tipoSede;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", type=" + tipoSede +
                ", indirizzo=" + indirizzo +
                ", cliente=" + cliente +
                '}';
    }
}

