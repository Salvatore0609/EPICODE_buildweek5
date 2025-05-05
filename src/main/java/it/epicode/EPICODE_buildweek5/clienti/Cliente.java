package it.epicode.EPICODE_buildweek5.clienti;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private String pec;
    private String telefono;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String emailContatto;
}