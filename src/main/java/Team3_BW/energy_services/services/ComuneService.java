package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Comune;
import Team3_BW.energy_services.entities.Provincia;
import Team3_BW.energy_services.repositories.ComuneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepo comuneRepo;

    @Autowired
    private ProvinciaService provinciaService;

    private Map<String, String> provAlias = new HashMap<>();


    public void importComuneFromCsv(String filePath) {
        provAlias.put("Verbano-Cusio-Ossola", "Verbania");
        provAlias.put("Valle d'Aosta/Vallée d'Aoste", "Aosta");
        provAlias.put("Bolzano/Bozen", "Bolzano");
        provAlias.put("La Spezia", "La-Spezia");
        provAlias.put("Reggio nell'Emilia", "Reggio-Emilia");
        provAlias.put("Forlì-Cesena", "Forli-Cesena");
        provAlias.put("Pesaro e Urbino", "Pesaro-Urbino");
        provAlias.put("Ascoli Piceno", "Ascoli-Piceno");
        provAlias.put("Reggio Calabria", "Reggio-Calabria");
        provAlias.put("Vibo Valentia", "Vibo-Valentia");
        provAlias.put("Sud Sardegna", "Medio Campidano");
        provAlias.put("Sud Sardegna", "Carbonia Iglesias");
        provAlias.put("Monza e della Brianza", "Monza-Brianza");


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); //salta prima riga
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String nomeProvinciaDaCercare = values[3];

                try {
                    Provincia provinciaLink = this.provinciaService.findProv(nomeProvinciaDaCercare);
                    Comune comune = new Comune();
                    comune.setNomeComune(values[3]);
                    comune.setDenominazioneInItaliano(values[2]);
                    comune.setProvinciaRef(provinciaLink);
                    this.comuneRepo.save(comune);
                } catch (RuntimeException ex) {
                    System.out.println(nomeProvinciaDaCercare + " eccezione");
                    String provinciaGiusta = provAlias.get(nomeProvinciaDaCercare);
                    Provincia p = this.provinciaService.findProv(provinciaGiusta);
                    Comune comune = new Comune();
                    comune.setNomeComune(values[3]);
                    comune.setDenominazioneInItaliano(values[2]);
                    comune.setProvinciaRef(p);
                    this.comuneRepo.save(comune);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("errore nella lettura del file");
        }
    }

}
