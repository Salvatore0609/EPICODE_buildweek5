package it.epicode.EPICODE_buildweek5.indirizzi;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
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

    @CsvBindByPosition(position = 0)
    @Column
    private String sigla;

    @CsvBindByPosition(position = 1)
    @Column
    private String provincia;

    @CsvBindByPosition(position = 2)
    @Column
    private String regione;

    //classe comuni
    @OneToMany
    private List<Comune> comuni;
}
