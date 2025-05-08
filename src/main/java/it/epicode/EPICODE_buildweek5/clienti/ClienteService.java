package it.epicode.EPICODE_buildweek5.clienti;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import it.epicode.EPICODE_buildweek5.exceptions.NotFoundException;
import jakarta.mail.MessagingException;

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

    public Page<Cliente> findAll(Pageable pageable){

        return cRepo.findAll(pageable);
    }

    public Page<Cliente> findByFatturatoAnnualeBetween(double fatturatoMin, double fatturatoMax, Pageable pageable) {
        return cRepo.findByFatturatoAnnualeBetween(fatturatoMin, fatturatoMax, pageable);
    }
    public Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(double fatturatoMin, Pageable pageable) {
        return cRepo.findByFatturatoAnnualeGreaterThanEqual(fatturatoMin, pageable);
    }
    public Page<Cliente> findByFatturatoAnnualeLessThanEqual(double fatturatoMax, Pageable pageable) {
        return cRepo.findByFatturatoAnnualeLessThanEqual(fatturatoMax, pageable);
    }

    public Page<Cliente> searchByNomeContatto(String nomeContatto, Pageable pageable) {
        return cRepo.searchByNomeContatto(nomeContatto, pageable);
    }
}