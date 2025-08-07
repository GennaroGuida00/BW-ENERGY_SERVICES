package Team3_BW.energy_services.payloads;

import Team3_BW.energy_services.entities.Comune;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewIndirizzoDTO (
        @NotEmpty(message = "La via deve essere obbligatoria!")
        String via,
        @NotEmpty(message = "il civico deve essere obbligatorio!")
        String civico,
        @NotEmpty(message = "La località deve essere obbligatoria!")
        String località,
        @NotEmpty(message = "il cap deve essere obbligatorio!")
        String cap,
        @NotNull(message = "Il comune è obbligatorio")
        long id_comune

){
}
