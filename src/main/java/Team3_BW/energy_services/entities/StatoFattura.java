package Team3_BW.energy_services.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stato_fattura")
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;


    public StatoFattura() {
    }

    public StatoFattura(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "StatoFattura{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
