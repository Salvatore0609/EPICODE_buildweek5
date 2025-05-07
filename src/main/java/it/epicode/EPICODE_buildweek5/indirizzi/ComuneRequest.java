package it.epicode.EPICODE_buildweek5.indirizzi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComuneRequest {
    @NotBlank(message = "Il codice provincia non può essere vuoto")
    private String codiceProvincia;
    @NotBlank(message = "Il progressivo del comune non può essere vuoto")
    private String progressivoComune;
    @NotBlank(message = "La denominazione in italiano non può essere vuota")
    private String denominazioneInItaliano;
    @NotBlank(message = "La denominazione in inglese non può essere vuota")
    private String nomeProvincia;

    @NotNull(message = "L'id della provincia non può essere nullo")
    private Long provinciaId;



}
