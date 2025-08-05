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

    public void importComuniFrmoCsv(String filePath) throws IOException {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            bufferedReader.readLine(); // salta la prima riga

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineValues = line.split(",");

                String nomeProvinciaDaCercare = lineValues[3];
                Provincia p = this.provinciaService.findProv(nomeProvinciaDaCercare);

                if(p != null){
                    Comune newComune = new Comune();
                    newComune.setNomeComune(lineValues[3]);
                    newComune.setCodiceProvincia(Integer.parseInt(lineValues[0]));
                    newComune.setProgressivoDelComune(Integer.parseInt(lineValues[1]));
                    newComune.setDenominazioneInItaliano(lineValues[2]);
                    newComune.set
            }
        }
    }



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
