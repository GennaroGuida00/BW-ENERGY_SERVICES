package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Comune;
import Team3_BW.energy_services.entities.Provincia;
import Team3_BW.energy_services.repositories.ComuneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepo comuneRepo;

    @Autowired
    private ProvinciaService provinciaService;

    public void importComuniFromCsv(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); //salta la prima riga
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String nomeProvinciaDaCercare = values[3];
                System.out.println(nomeProvinciaDaCercare);

                Provincia provinciaLink = this.provinciaService.findProv(nomeProvinciaDaCercare);

                if (provinciaLink != null) {
                    Comune comune = new Comune();
                    comune.setNomeComune(values[3]);
                    comune.setCodiceProvincia(Integer.parseInt(values[0]));
                    comune.setProgressivoDelComune(Integer.parseInt(values[1]));
                    comune.setDenominazioneInItaliano(values[2]);
                    comune.setProvincia(provinciaLink);
                    this.comuneRepo.save(comune);
                } else {
                    System.out.println("provincia non trovata" + values[3]);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("error in import");
        }
    }

    //test ok
//    public void testagain() {
//        Provincia p = this.provinciaService.findProv("Torino");
//        System.out.println(p.getSigla());
//    }


}
