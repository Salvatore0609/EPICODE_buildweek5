package it.epicode.EPICODE_buildweek5.indirizzi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "indirizzoLegale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndirizzoLegale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String via;
    private String civico;
    private String cap;
    private String localita;

    @ManyToOne
    private Comune comune;
}