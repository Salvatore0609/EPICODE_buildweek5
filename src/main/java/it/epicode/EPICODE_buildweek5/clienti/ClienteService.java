package it.epicode.EPICODE_buildweek5.clienti;
import it.epicode.EPICODE_buildweek5.auth.Role;
import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import it.epicode.EPICODE_buildweek5.exceptions.NotFoundException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ClienteService {
    @Autowired
    private ClienteRepository cRepo;

    public CommonResponse createCliente(ClienteRequest request) throws MessagingException {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(request, cliente);
        /*if (cRepo.existsByNomeECognome(cliente.getNomeContatto(), cliente.getCognomeContatto())) {
            throw new IllegalArgumentException("Cliente esistente");
        }
        if (cRepo.existsByPec(cliente.getPec())) {
            throw new IllegalArgumentException("Cliente esistente");
        }
        if (cRepo.existsByPartitaIva(cliente.getPartitaIva())) {
            throw new IllegalArgumentException("Cliente esistente");
        }*/
        cliente = cRepo.save(cliente);
        return new CommonResponse(cliente.getId());
    }

    public Cliente getClienteById(Long id) {
        return cRepo.findById(id).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
    }

    public void deleteCliente(Long id) {
        if (!cRepo.existsById(id)) {
            throw new NotFoundException("Cliente non trovato");
        }
        cRepo.deleteById(id);
    }

    //ruolo da mettere nel controller
    public Cliente updateCliente(Long id, ClienteRequest request) {
        Cliente cliente = cRepo.findById(id).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        BeanUtils.copyProperties(request, cliente);
        return cRepo.save(cliente);
    }

    public Page<Cliente> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return cRepo.findAll(pageable);
    }
}