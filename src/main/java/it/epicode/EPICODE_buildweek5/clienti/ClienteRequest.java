package it.epicode.EPICODE_buildweek5.clienti;

import it.epicode.EPICODE_buildweek5.indirizzi.IndirizzoLegale;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    @NotBlank(message = "Inserisci il nome del cliente")
    private String nomeContatto;
    @NotBlank(message = "Inserisci il cognome del cliente")
    private String cognomeContatto;
    @NotBlank(message = "Inserisci il fatturato annuale")
    private Double fatturatoAnnuale;
    @NotBlank(message = "Inserisci la data di inserimento")
    private String dataInserimento;
    @NotBlank(message = "Inserisci la data dell'ultimo contatto")
    private String dataUltimoContatto;
    @NotBlank(message = "Inserisci l'indirizzo legate")
    private IndirizzoLegale indirizzoLegale;
}
