package it.epicode.EPICODE_buildweek5.fatture;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;
    @Autowired
    private FatturaRepository fatturaRepository;

    public CommonResponse createStatoFattura(StatoFatturaRequest request) {
        StatoFattura statoFattura = new StatoFattura();
        statoFattura.setNome(request.getNome());
        Fattura fattura = fatturaRepository.findById(request.getFatturaId())
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));
        fattura.setStatoFattura(statoFattura);
        fatturaRepository.save(fattura);

        statoFatturaRepository.save(statoFattura);
        return new CommonResponse(statoFattura.getId());
    }
    public Page<StatoFattura> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return statoFatturaRepository.findAll(pageable);
    }

    public StatoFattura findById(Long id) {
        return statoFatturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stato fattura non trovato"));
    }

    public StatoFattura updateStatoFattura(Long id, StatoFatturaRequest request) {
        StatoFattura statoFattura = statoFatturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stato fattura non trovato"));
        statoFattura.setNome(request.getNome());
        return statoFatturaRepository.save(statoFattura);
    }

    public void deleteStatoFattura(Long id) {
        StatoFattura statoFattura = statoFatturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stato fattura non trovato"));
        statoFatturaRepository.delete(statoFattura);
    }
}
