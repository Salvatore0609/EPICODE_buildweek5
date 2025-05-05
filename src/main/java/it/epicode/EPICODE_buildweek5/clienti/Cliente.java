package it.epicode.EPICODE_buildweek5.clienti;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
}