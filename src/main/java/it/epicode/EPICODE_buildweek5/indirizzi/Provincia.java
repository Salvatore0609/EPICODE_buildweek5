package it.epicode.EPICODE_buildweek5.indirizzi;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "province")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CsvBindByName(column = "Sigla")
    @Column
    private String sigla;

    @CsvBindByName(column = "Provincia")
    @Column
    private String provincia;

    @CsvBindByName(column = "Regione")
    @Column
    private String regione;

    //classe comuni
    @OneToMany
    private List<Comune> comuni;
}
