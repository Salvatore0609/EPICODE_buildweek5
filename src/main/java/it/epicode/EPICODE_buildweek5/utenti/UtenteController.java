package it.epicode.EPICODE_buildweek5.utenti;

import it.epicode.EPICODE_buildweek5.auth.AuthResponse;
import it.epicode.EPICODE_buildweek5.auth.LoginRequest;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public List<Utente> getAllUtenti() {return utenteService.getAllUtenti();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Utente findById(@PathVariable Long id) {
        return utenteService.getUtenteById(id);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current-user")

    public Utente getCurrentUser(@AuthenticationPrincipal Utente utente) {
        return utente;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUtente(@RequestBody UtenteAuthRequest request) throws MessagingException {
        utenteService.registerUtente(request);
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request:");
        String token = utenteService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(new AuthResponse(token));
    }


    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public Utente updateUtente(@PathVariable Long id, @RequestBody UtenteRequest request, @AuthenticationPrincipal Utente utente) {
        return utenteService.updateUtente(id, request, utente);
    }

    //aggiunte
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUtente(@PathVariable Long id) {
        utenteService.deleteUtente(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/exists/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void existsByUsername(@PathVariable String username) {
        utenteService.existsByUsername(username);
    }
}
