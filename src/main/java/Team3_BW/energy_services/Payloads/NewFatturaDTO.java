package Team3_BW.energy_services.Payloads;

import Team3_BW.energy_services.entities.StatoFattura;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewFatturaDTO(

        @NotEmpty(message = "l'ID dell'evento è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il nome deve essere di lunghezza compresa tra 2 e 40")
        LocalDate data,
        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere di lunghezza compresa tra 2 e 40")
        double importo)
{}