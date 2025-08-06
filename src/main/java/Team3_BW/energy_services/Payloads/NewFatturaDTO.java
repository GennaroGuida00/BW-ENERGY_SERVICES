package Team3_BW.energy_services.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewFatturaDTO(

        @NotEmpty(message = "obbligatoria!")
        LocalDate data,
        @NotEmpty(message = "obbligatorio!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere di lunghezza compresa tra 2 e 40")
        double importo) {
}