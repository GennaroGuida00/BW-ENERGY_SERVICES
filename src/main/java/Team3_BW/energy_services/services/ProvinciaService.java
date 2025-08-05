package Team3_BW.energy_services.services;

import Team3_BW.energy_services.entities.Provincia;
import Team3_BW.energy_services.repositories.ProvinciaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepo provinciaRepo;

    public void importProvinciaFromCsv(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); //salta prima riga
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                Provincia newPro = new Provincia();
                newPro.setSigla(values[0]);
                newPro.setProvincia(values[1]);
                newPro.setRegione(values[2]);
                this.provinciaRepo.save(newPro);
            }
        }
    }

    public Provincia findProv(String nome) {
        return this.provinciaRepo.findByProvincia(nome)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
