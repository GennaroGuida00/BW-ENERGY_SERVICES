package Team3_BW.energy_services.entities;

import Team3_BW.energy_services.enums.TipologiaCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Fattura> fatture;
    @Enumerated(EnumType.STRING)
    private TipologiaCliente tipologiaCliente;
    @OneToMany(mappedBy = "cliente")
    private List<Sede> sedi;

    public Cliente(String ragioneSociale, String partitaIva, String email, LocalDate dataInserimento, Double fatturatoAnnuale, LocalDate dataUltimoContatto, String pec, String telefono, String nomeContatto, String emailContatto, String cognomeContatto, String telefonoContatto, String logoAziendale, List<Fattura> fatture, TipologiaCliente tipologiaCliente, List<Sede> sedi) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.dataUltimoContatto = dataUltimoContatto;
        this.pec = pec;
        this.telefono = telefono;
        this.nomeContatto = nomeContatto;
        this.emailContatto = emailContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
        this.fatture = fatture;
        this.tipologiaCliente = tipologiaCliente;
        this.sedi = sedi;
    }

    public Cliente() {
    }

    public long getId() {
        return id;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Double getFatturatoAnnuale() {
        return fatturatoAnnuale;
    }

    public void setFatturatoAnnuale(Double fatturatoAnnuale) {
        this.fatturatoAnnuale = fatturatoAnnuale;
    }

    public LocalDate getDataUltimoContatto() {
        return dataUltimoContatto;
    }

    public void setDataUltimoContatto(LocalDate dataUltimoContatto) {
        this.dataUltimoContatto = dataUltimoContatto;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailContatto() {
        return emailContatto;
    }

    public void setEmailContatto(String emailContatto) {
        this.emailContatto = emailContatto;
    }

    public String getNomeContatto() {
        return nomeContatto;
    }

    public void setNomeContatto(String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }

    public String getCognomeContatto() {
        return cognomeContatto;
    }

    public void setCognomeContatto(String cognomeContatto) {
        this.cognomeContatto = cognomeContatto;
    }

    public String getTelefonoContatto() {
        return telefonoContatto;
    }

    public void setTelefonoContatto(String telefonoContatto) {
        this.telefonoContatto = telefonoContatto;
    }

    public String getLogoAziendale() {
        return logoAziendale;
    }

    public void setLogoAziendale(String logoAziendale) {
        this.logoAziendale = logoAziendale;
    }

    public List<Fattura> getFatture() {
        return fatture;
    }

    public void setFatture(List<Fattura> fatture) {
        this.fatture = fatture;
    }

    public TipologiaCliente getTipologiaCliente() {
        return tipologiaCliente;
    }

    public void setTipologiaCliente(TipologiaCliente tipologiaCliente) {
        this.tipologiaCliente = tipologiaCliente;
    }

    public List<Sede> getSedi() {
        return sedi;
    }

    public void setSedi(List<Sede> sedi) {
        this.sedi = sedi;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", email='" + email + '\'' +
                ", dataInserimento=" + dataInserimento +
                ", dataUltimoContatto=" + dataUltimoContatto +
                ", fatturatoAnnuale=" + fatturatoAnnuale +
                ", pec='" + pec + '\'' +
                ", telefono=" + telefono +
                ", emailContatto='" + emailContatto + '\'' +
                ", nomeContatto='" + nomeContatto + '\'' +
                ", cognomeContatto='" + cognomeContatto + '\'' +
                ", telefonoContatto=" + telefonoContatto +
                ", logoAziendale='" + logoAziendale + '\'' +
                ", fatture=" + fatture +
                ", tipologiaCliente=" + tipologiaCliente +
                '}';
    }
}

