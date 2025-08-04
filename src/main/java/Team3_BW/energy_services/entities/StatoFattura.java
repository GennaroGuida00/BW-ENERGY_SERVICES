package Team3_BW.energy_services.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stato_fattura")
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipologiaFatture;

    // Getter e Setter
    // ...
}

