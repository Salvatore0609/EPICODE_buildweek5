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
public class ProvinciaRequest {
    @NotBlank(message = "La sigla non può essere vuota")
    private String sigla;
    @NotBlank(message = "La provincia non può essere vuota")
    private String provincia;
    @NotBlank(message = "La regione non può essere vuota")
    private String regione;
    @NotNull(message = "L'id del comune non può essere vuoto")
    private Long comuneId;
}
