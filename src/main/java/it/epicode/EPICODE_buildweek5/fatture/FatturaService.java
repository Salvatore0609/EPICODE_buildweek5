package it.epicode.EPICODE_buildweek5.fatture;

import it.epicode.EPICODE_buildweek5.clienti.Cliente;
import it.epicode.EPICODE_buildweek5.clienti.ClienteRepository;
import it.epicode.EPICODE_buildweek5.clienti.ClienteRequest;
import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import it.epicode.EPICODE_buildweek5.exceptions.NotFoundException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FatturaService {

    private final FatturaRepository fRepo;
    private final ClienteRepository clienteRepo;
    private final StatoFatturaRepository statoFatturaRepo;

    public CommonResponse createFattura(FatturaRequest request) throws MessagingException {
        Fattura fattura = new Fattura();
        BeanUtils.copyProperties(request, fattura);
        Cliente cliente = clienteRepo.findById(request.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        StatoFattura statoFattura = statoFatturaRepo.findById(request.getStatoFatturaId())
                .orElseThrow(() -> new NotFoundException("Stato Fattura non trovato"));
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);

        fattura = fRepo.save(fattura);
        return new CommonResponse(fattura.getId());
    }

    public Fattura getFatturaById(Long id) {
        return fRepo.findById(id).orElseThrow(() -> new NotFoundException("Fattura non trovata"));
    }

    public void deleteFattura(Long id) {
        if (!fRepo.existsById(id)) {
            throw new NotFoundException("Fattura non trovata");
        }
        fRepo.deleteById(id);
    }

    //ruolo da mettere nel controller
    public Fattura updateFattura(Long id, FatturaRequest request) {
        Fattura fattura= fRepo.findById(id).orElseThrow(() -> new NotFoundException("Fattura non trovata"));
        BeanUtils.copyProperties(request, fattura);
        return fRepo.save(fattura);
    }

    public Page<Fattura> findAll(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return fRepo.findAll(pageable);
    }
    public Page<Fattura> findFattureByClienteId(Long clienteId, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return fRepo.findByClienteId(clienteId, pageable);
    }


    public Page<Fattura> findFattureByStatoFatturaId(Long statoFatturaId, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return fRepo.findByStatoFatturaId(statoFatturaId, pageable);
    }

    public Page<Fattura> findFattureByData(LocalDate data, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return fRepo.findByData(data, pageable);
    }

    public Page<Fattura> findFattureByAnno(LocalDate anno, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return fRepo.findByData(anno, pageable);
    }

    public Page<Fattura> findFattureByRangeDiImporti(Integer rangeDiImporti, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return fRepo.findByRangeDiImporti(rangeDiImporti, pageable);
    }


}