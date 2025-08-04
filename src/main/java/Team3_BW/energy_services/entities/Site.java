package Team3_BW.energy_services.entities;

import Team3_BW.energy_services.enums.TipoSede;
import jakarta.persistence.*;

@Entity
@Table(name = "sites")
public class Site {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private TipoSede type;
    //CLIENTE
    @OneToOne
    @JoinColumn(name ="id_address")
    private Address address;

    public Site() {
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
}
