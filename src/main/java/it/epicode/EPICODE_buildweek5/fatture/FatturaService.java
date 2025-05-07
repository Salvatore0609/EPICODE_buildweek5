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

@Service
@RequiredArgsConstructor
public class FatturaService {

    private final FatturaRepository fRepo;

    public CommonResponse createFattura(FatturaRequest request) throws MessagingException {
        Fattura fattura = new Fattura();
        BeanUtils.copyProperties(request, fattura);
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

    public Page<Fattura> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return fRepo.findAll(pageable);
    }
}