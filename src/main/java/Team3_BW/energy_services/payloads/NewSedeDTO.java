package Team3_BW.energy_services.payloads;

import Team3_BW.energy_services.enums.TipoSede;
import jakarta.validation.constraints.NotEmpty;

public record NewSedeDTO(
        @NotEmpty(message = "La sede deve essere obbligatoria.Scegliere tra: LEGALE,OPERATIVA")
        TipoSede tipoSede,
        @NotEmpty(message = "L'indirizzo è obbligatorio")
        long idIndirizzo,
        @NotEmpty(message = "il cliente è obbligatorio")
        long idCliente
) {
}
