package Team3_BW.energy_services.Payloads;


import Team3_BW.energy_services.enums.TipologiaCliente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewClienteDTO(

        @NotEmpty(message = "obbligatorio!")
        @Size(min = 2, max = 40, message = "Il nome deve essere di lunghezza compresa tra 2 e 40")
        String ragioneSociale,
        @NotEmpty(message = "obbligatoria!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere di lunghezza compresa tra 2 e 40")
        String partitaIva,
        @NotEmpty(message = "obbligatoria!")
        @Size(min = 4)
        String email,
        @NotEmpty(message = "obbligatoria!")
        @Size(min = 4)
        LocalDate dataInserimento,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        LocalDate dataUltimoContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        double fatturatoAnnuale,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String pec,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        int telefono,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String emailContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String nomeContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String cognomeContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        int telefonoContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String logoAziendale,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        TipologiaCliente tipologiaCliente)
{}
