package it.epicode.EPICODE_buildweek5.fatture;

import it.epicode.EPICODE_buildweek5.clienti.Cliente;
import it.epicode.EPICODE_buildweek5.clienti.ClienteRequest;
import it.epicode.EPICODE_buildweek5.clienti.ClienteService;
import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import it.epicode.EPICODE_buildweek5.utenti.Utente;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
@RequiredArgsConstructor
public class FatturaController {
    private final FatturaService fatturaService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Fattura getFatturaById(@PathVariable Long id) {
        return fatturaService.getFatturaById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public Page<Fattura> getAllFatture(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return fatturaService.findAll(page, size, sortBy);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current-fattura")

    public Fattura getCurrentFattura(@AuthenticationPrincipal Fattura fattura) {
        return fattura;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createFattura(@RequestBody FatturaRequest request) throws MessagingException {
        return fatturaService.createFattura(request);
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Fattura updateFattura(@PathVariable Long id, @RequestBody FatturaRequest request) {
        return fatturaService.updateFattura(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable Long id) {
        fatturaService.deleteFattura(id);
    }


}