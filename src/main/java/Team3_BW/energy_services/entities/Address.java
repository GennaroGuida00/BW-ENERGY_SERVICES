package Team3_BW.energy_services.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String via;
    private String civico;
    private String località;
    private String cap;
   //COMUNE


    public Address(String via, String civico, String località, String cap) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.cap = cap;
    }

    public Address() {
    }

    public long getId() {
        return id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getLocalità() {
        return località;
    }

    public void setLocalità(String località) {
        this.località = località;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }


}
