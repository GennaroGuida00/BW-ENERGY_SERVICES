package Team3_BW.energy_services.Payloads;

import Team3_BW.energy_services.entities.StatoFattura;

import java.time.LocalDate;

public record NewFatturaRespDTO (
    long id,
    LocalDate data,
    Double importo
){}
