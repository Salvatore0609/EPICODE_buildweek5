package it.epicode.EPICODE_buildweek5.indirizzi;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndirizzoLegaleRequest {
    @NotBlank(message = "la via non può essere vuota")
    private String via;
    @NotBlank(message = "il civico non può essere vuoto")
    private String civico;
    @NotBlank(message = "la cap non può essere vuota")
    private String cap;
    @NotBlank(message = "la località non può essere vuota")
    private String localita;
    @NotBlank(message = "il comune non può essere vuoto")
    private String comune;
}
