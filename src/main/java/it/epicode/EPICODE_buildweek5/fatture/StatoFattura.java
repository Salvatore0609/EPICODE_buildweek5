package it.epicode.EPICODE_buildweek5.fatture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column
    private String nome;

 @OneToMany
 private List<Fattura> fattura;
}