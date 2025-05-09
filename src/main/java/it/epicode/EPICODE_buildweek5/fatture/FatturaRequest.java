package it.epicode.EPICODE_buildweek5.fatture;

import it.epicode.EPICODE_buildweek5.clienti.Cliente;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaRequest {
    @NotBlank(message = "Inserisci l id del cliente")
    private Long clienteId;
    @NotBlank(message = "Inserisci il numero di fattura")
    private Integer numero;
    @NotBlank(message = "Inserisci l'importo")
    private Double importo;
    @NotBlank(message = "Inserisci lo stato della fattura")
    private Long statoFatturaId;
    @NotBlank(message = "Inserisci la data della fattura")
    private LocalDate data;
    @NotBlank(message = "Inserisci l'anno della fattura")
    private Integer anno;
    @NotBlank(message = "Inserisci il range di importi")
    private Double rangeDiImporti;
}