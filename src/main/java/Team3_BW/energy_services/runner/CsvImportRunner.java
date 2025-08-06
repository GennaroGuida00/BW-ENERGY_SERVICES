package Team3_BW.energy_services.runner;


import Team3_BW.energy_services.services.ComuneService;
import Team3_BW.energy_services.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvImportRunner implements CommandLineRunner {

    @Autowired
    private ComuneService comuneService;
    @Autowired
    private ProvinciaService provinciaService;

    @Override
    public void run(String... args) throws Exception {
        String provPath = "src/main/resources/comuni&province/province-italiane.csv";
        String comuPath = "src/main/resources/comuni&province/comuni-italiani.csv";

        try {
            // this.provinciaService.importProvinciaFromCsv(provPath);
            // System.out.println("province imprtate");
            this.comuneService.importComuneFromCsv(comuPath);
            System.out.println("done");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
