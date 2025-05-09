package it.epicode.EPICODE_buildweek5.indirizzi;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class IndirizzoLegaleService {
    @Autowired
    private IndirizzoLegaleRepository indirizzoLegaleRepository;

    public CommonResponse createIndirizzoLegale(IndirizzoLegaleRequest request) {
        IndirizzoLegale indirizzoLegale = new IndirizzoLegale();
        BeanUtils.copyProperties(request, indirizzoLegale);
        indirizzoLegale = indirizzoLegaleRepository.save(indirizzoLegale);
        return new CommonResponse(indirizzoLegale.getId());
    }

    public IndirizzoLegale getIndirizzoLegaleById(Long id) {
        return indirizzoLegaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Indirizzo legale non trovato"));
    }

    public List<IndirizzoLegale> getAllIndirizziLegali() {return indirizzoLegaleRepository.findAll();}

    public IndirizzoLegale updateIndirizzoLegale(Long id, IndirizzoLegaleRequest request) {
        IndirizzoLegale indirizzoLegale = getIndirizzoLegaleById(id);
        BeanUtils.copyProperties(request, indirizzoLegale);
        return indirizzoLegaleRepository.save(indirizzoLegale);
    }
    public void deleteIndirizzoLegale(Long id) {
        IndirizzoLegale indirizzoLegale = getIndirizzoLegaleById(id);
        indirizzoLegaleRepository.delete(indirizzoLegale);
    }
}
