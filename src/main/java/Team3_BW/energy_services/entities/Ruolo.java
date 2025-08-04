package Team3_BW.energy_services.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ruoli")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "ruoli")
    private Set<Utente> utenti = new HashSet<>();

    // Getter e Setter
    // ...
}

