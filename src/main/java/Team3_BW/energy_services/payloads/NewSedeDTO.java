package Team3_BW.energy_services.payloads;

import Team3_BW.energy_services.enums.TipoSede;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewSedeDTO(
        @NotNull(message = "La sede deve essere obbligatoria.Scegliere tra: LEGALE,OPERATIVA")
        TipoSede tipoSede,
        @NotNull(message = "L'indirizzo è obbligatorio")
        long idIndirizzo,
        @NotNull(message = "il cliente è obbligatorio")
        long idCliente
) {
}
