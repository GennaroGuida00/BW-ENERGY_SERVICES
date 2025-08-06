package Team3_BW.energy_services.payloads;

import Team3_BW.energy_services.entities.Cliente;
import Team3_BW.energy_services.entities.StatoFattura;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewFatturaDTO(

        @PastOrPresent(message = "data non può essere futura")
        LocalDate data,
        @NotEmpty(message = "obbligatorio!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere di lunghezza compresa tra 2 e 40")
        double importo,
        Cliente cliente
        )
{}