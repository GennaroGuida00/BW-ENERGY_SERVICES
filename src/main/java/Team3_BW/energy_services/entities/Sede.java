package Team3_BW.energy_services.entities;

import Team3_BW.energy_services.enums.TipoSede;
import jakarta.persistence.*;

@Entity
@Table(name = "sedi")
public class Sede {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private TipoSede type;
    //CLIENTE
    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Sede() {
    }

    public long getId() {
        return id;
    }

    public TipoSede getType() {
        return type;
    }

    public void setType(TipoSede type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", type=" + type +
                ", address=" + address +
                '}';
    }
}
