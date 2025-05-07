package it.epicode.EPICODE_buildweek5.indirizzi;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @CsvBindByPosition(position = 0)
    @Column
    private String codiceProvincia;


    @CsvBindByPosition(position = 1)
    @Column
    private String progressivoComune;


    @CsvBindByPosition(position = 2)
    @Column
    private String denominazioneInItaliano;

    @CsvBindByPosition(position = 3)
    private String nomeProvincia;


    //classe provincia
    @ManyToOne
    private Provincia provincia;
    //indirizzi legali
    @OneToMany(mappedBy = "comune")
    @JsonIgnore
    private List<IndirizzoLegale> indirizzoLegale;
    //indirizzi operative
    @OneToMany(mappedBy = "comune")
    @JsonIgnore
    private List<IndirizzoOperativa> indirizzoOperativa;
}
