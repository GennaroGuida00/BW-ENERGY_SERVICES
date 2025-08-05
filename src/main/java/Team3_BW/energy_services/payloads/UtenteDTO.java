package Team3_BW.energy_services.payloads;

import java.util.Set;

public record UtenteDTO(Long id, String username, String email, String nome, String cognome, String avatar,
                        Set<String> ruoli) {
}
