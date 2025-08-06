package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Provincia;
import Team3_BW.energy_services.repositories.ComuneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepo comuneRepo;

    @Autowired
    private ProvinciaService provinciaService;

    public void importComuneFromCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); //salta prima riga
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String nomeProvinciaDaCercare = values[3];

                try {
                    Provincia provinciaLink = this.provinciaService.findProv(nomeProvinciaDaCercare);
                    System.out.println(provinciaLink);
                } catch (RuntimeException ex) {
                    System.out.println(nomeProvinciaDaCercare);
                }


//                if (provinciaLink != null) {
//                    Comune comune = new Comune();
//                    comune.setNomeComune(values[3]);
//                    comune.setCodiceProvincia(Integer.parseInt(values[0]));
//                    comune.setProgressivoDelComune(Integer.parseInt(values[1]));
//                    comune.setDenominazioneInItaliano(values[2]);
//                    comune.setProvinciaRef(provinciaLink);
//                    this.comuneRepo.save(comune);
//                } else {
//                    System.out.println("provincia non trovata" + values[3]);
//                }
            }

        } catch (Exception e) {
            throw new RuntimeException("errore nella lettura del file");
        }
    }

}
