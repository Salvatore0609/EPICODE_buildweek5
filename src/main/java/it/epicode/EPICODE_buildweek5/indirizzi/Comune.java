package it.epicode.EPICODE_buildweek5.indirizzi;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "comuni")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CsvBindByName(column = "Codice Provincia")
    @CsvBindByPosition(position = 0)
    @Column(nullable = false)
    private String codiceProvincia;

    @CsvBindByName(column = "Progressivo del Comune")
    @CsvBindByPosition(position = 1)
    @Column(nullable = false)
    private String progressivoComune;

    @CsvBindByName(column = "Denominazione in italiano")
    @CsvBindByPosition(position = 2)
    @Column(nullable = false)
    private String denominazioneInItaliano;

    //classe provincia
    @ManyToOne
    private Provincia provincia;
    //indirizzi legali
    @OneToMany
    private List<IndirizzoLegale> indirizzoLegale;
    //indirizzi operative
    @OneToMany
    private List<IndirizzoOperativa> indirizzoOperativa;
}
