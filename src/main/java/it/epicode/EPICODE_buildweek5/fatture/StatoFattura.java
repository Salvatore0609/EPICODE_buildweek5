package it.epicode.EPICODE_buildweek5.fatture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "statiFatture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatoFattura {
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE)
 private Long id;
 public static final List<String> statiFattura = Arrays.asList("ANNULLATO", "PAGATO", "NON PAGATO", "DA PAGARE", "DA ANNULLARE", "PAGATO PARZIALMENTE", "DA PAGARE PARZIALMENTE", "ANNULLATO PARZIALMENTE", "DA ANNULLARE PARZIALMENTE");
 @Column
 private String nome; // GLI STATI FATTURA POSSONO ESSERE, ANNULLATO, PAGATO, NON PAGATO, DA PAGARE, DA ANNULLARE, PAGATO PARZIALMENTE, DA PAGARE PARZIALMENTE, ANNULLATO PARZIALMENTE, DA ANNULLARE PARZIALMENTE

}