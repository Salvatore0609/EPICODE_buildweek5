package it.epicode.EPICODE_buildweek5.fatture;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.EPICODE_buildweek5.clienti.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private LocalDate data;
    @Column
    private Double importo;
    @Column
    private Integer numero;

    //stati fattura
    @ManyToOne
    private StatoFattura statoFattura;

    //relazioni
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

}