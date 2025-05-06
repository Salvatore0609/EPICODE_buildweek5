package it.epicode.EPICODE_buildweek5.clienti;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clienti")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createCliente(@RequestBody ClienteRequest request) throws MessagingException {
        return clienteService.createCliente(request);
    }

    @GetMapping
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @GetMapping("/all")
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
         return clienteService.findAll(page, size, sortBy);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente updateCliente(@PathVariable Long id, @RequestBody ClienteRequest request) {
        return clienteService.updateCliente(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }


}