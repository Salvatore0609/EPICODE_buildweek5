package it.epicode.EPICODE_buildweek5.clienti;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import it.epicode.EPICODE_buildweek5.exceptions.NotFoundException;
import it.epicode.EPICODE_buildweek5.fatture.Fattura;
import it.epicode.EPICODE_buildweek5.fatture.FatturaRepository;
import it.epicode.EPICODE_buildweek5.indirizzi.IndirizzoLegale;
import it.epicode.EPICODE_buildweek5.indirizzi.IndirizzoLegaleRepository;
import it.epicode.EPICODE_buildweek5.indirizzi.IndirizzoOperativa;
import it.epicode.EPICODE_buildweek5.indirizzi.IndirizzoOperativaRepository;
import jakarta.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
public class ClienteService {
    @Autowired
    private ClienteRepository cRepo;
    @Autowired
    private IndirizzoLegaleRepository iRepo;
    @Autowired
    private IndirizzoOperativaRepository iRepo2;

    public CommonResponse createCliente(ClienteRequest request) throws MessagingException {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(request, cliente);
        IndirizzoLegale indirizzoLegale = iRepo.findById(request.getIndirizzoLegaleId()).orElseThrow(() -> new NotFoundException("Indirizzo legale non trovato"));
        IndirizzoOperativa indirizzoOperativa = iRepo2.findById(request.getIndirizzoOperativaId()).orElseThrow(() -> new NotFoundException("Indirizzo operativo non trovato"));
        cliente.setIndirizzoOperativa(indirizzoOperativa);
        cliente.setIndirizzoLegale(indirizzoLegale);


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

    public Page<Cliente> findClientiByDataInserimento(LocalDate data, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return cRepo.findByDataInserimento(data, pageable);
    }

    public Page<Cliente> findClientiByDataUltimoContatto(LocalDate data, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return cRepo.findByDataUltimoContatto(data, pageable);
    }
}