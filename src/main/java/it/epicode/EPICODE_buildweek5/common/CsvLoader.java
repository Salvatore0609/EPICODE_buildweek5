package it.epicode.EPICODE_buildweek5.common;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.epicode.EPICODE_buildweek5.indirizzi.Comune;
import it.epicode.EPICODE_buildweek5.indirizzi.ComuneRepository;
import it.epicode.EPICODE_buildweek5.indirizzi.Provincia;
import it.epicode.EPICODE_buildweek5.indirizzi.ProvinciaRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class CsvLoader {
    @Value("classpath:province-italiane.csv")
    private Resource provinceResource;
    @Value("classpath:comuni-italiani.csv")
    private Resource comuniResource;

    private final ProvinciaRepository provinciaRepository;
    private final ComuneRepository comuneRepository;

    //classi
    private List<Provincia> province;
    private List<Comune> comuni;



    @PostConstruct
    public void init() {
        System.out.println(">>> CsvLoader.init() START");
        List<Provincia> province = parseProvince();
        province.forEach(p -> System.out.println(">> Provincia caricata: " + p.getProvincia()));
        provinciaRepository.saveAll(province);

        List<Comune> comuni = parseComuni();
        // assegna a ciascun Comune la Provincia persistita
        comuni.forEach(c -> {
            Provincia p = provinciaRepository.findByProvincia(c.getNomeProvincia().trim())
                    .orElseThrow(() -> new RuntimeException("Provincia non trovata: " + c.getNomeProvincia()));
            c.setProvincia(p);
        });
        comuneRepository.saveAll(comuni);
    }

    private List<Provincia> parseProvince() {
        try (Reader reader = new BufferedReader(
                new InputStreamReader(provinceResource.getInputStream()))) {
            return new CsvToBeanBuilder<Provincia>(reader)
                    .withType(Provincia.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .withSkipLines(1)
                    .build()
                    .parse();

        } catch (Exception e) {
            throw new RuntimeException("Errore caricamento province.csv", e);
        }
    }

    private List<Comune> parseComuni() {
        try (Reader reader = new BufferedReader(
                new InputStreamReader(comuniResource.getInputStream()))) {
            return new CsvToBeanBuilder<Comune>(reader)
                    .withType(Comune.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .withSkipLines(1)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Errore caricamento comuni.csv", e);
        }
    }

}

