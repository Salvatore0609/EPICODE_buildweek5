package it.epicode.EPICODE_buildweek5.clienti;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/clienti")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse createCliente(@RequestBody ClienteRequest request) throws MessagingException {
        return clienteService.createCliente(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @GetMapping("/all")

    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "nomeContatto") String sortBy,
                                       @RequestParam(defaultValue = "asc") String direction,
                                       @RequestParam(required = false) Double fatturatoMin,
                                       @RequestParam(required = false) Double fatturatoMax,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInserimento,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataUltimoContatto,
                                       @RequestParam(required = false) String nomeContatto)
                                        {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        if (nomeContatto != null && !nomeContatto.isEmpty()) {
            return clienteService.searchByNomeContatto(nomeContatto, pageable);
        } else if (fatturatoMin != null && fatturatoMax != null) {
            return clienteService.findByFatturatoAnnualeBetween(fatturatoMin, fatturatoMax, pageable);
        } else if (fatturatoMin != null) {
            return clienteService.findByFatturatoAnnualeGreaterThanEqual(fatturatoMin, pageable);
        } else if (fatturatoMax != null) {
            return clienteService.findByFatturatoAnnualeLessThanEqual(fatturatoMax, pageable);
        } else if (dataInserimento != null) {
                                            return clienteService.findClientiByDataInserimento(dataInserimento, page, size, sortBy, direction);
                                        }
        else if (dataUltimoContatto != null) {
            return clienteService.findClientiByDataUltimoContatto(dataInserimento, page, size, sortBy, direction);
        }
        else {
            return clienteService.findAll(pageable);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody ClienteRequest request) {
        return clienteService.updateCliente(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }


}