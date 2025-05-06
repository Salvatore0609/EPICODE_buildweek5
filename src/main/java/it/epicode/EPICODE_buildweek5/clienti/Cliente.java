package it.epicode.EPICODE_buildweek5.clienti;

import it.epicode.EPICODE_buildweek5.fatture.Fattura;
import it.epicode.EPICODE_buildweek5.indirizzi.IndirizzoLegale;
import it.epicode.EPICODE_buildweek5.indirizzi.IndirizzoOperativa;
import it.epicode.EPICODE_buildweek5.utenti.Utente;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clienti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String ragioneSociale;
    @Column
    private String partitaIva;
    @Column
    private String email;
    @Column
    private LocalDate dataInserimento;
    @Column
    private LocalDate dataUltimoContatto;
    @Column
    private Double fatturatoAnnuale;
    @Column
    private String pec;
    @Column
    private String telefono;
    @Column
    private String emailContatto;
    @Column
    private String nomeContatto;
    @Column
    private String cognomeContatto;
    @Column
    private String telefonoContatto;
    @Column
    private String logoAziendale;

    //
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    //relazioni con utente
    /*@ManyToOne
    private Utente utente;*/

    @OneToOne
    private IndirizzoLegale indirizzoLegale;
    @OneToOne
    private IndirizzoOperativa indirizzoOperativa;

    @OneToMany
    private List<Fattura> fattura;

}