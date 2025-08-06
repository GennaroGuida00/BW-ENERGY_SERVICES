package Team3_BW.energy_services.payloads;

import java.time.LocalDate;

public record NewFatturaRespDTO(
        long id,
        LocalDate data,
        Double importo
) {
}
