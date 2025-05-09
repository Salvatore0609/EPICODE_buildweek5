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
public class IndirizzoOperativaService {
    @Autowired
    private IndirizzoOperativaRepository indirizzoOperativaRepository;

    public CommonResponse createIndirizzoOperativa(IndirizzoOperativaRequest request) {
        IndirizzoOperativa indirizzoOperativa = new IndirizzoOperativa();
        BeanUtils.copyProperties(request, indirizzoOperativa);
        indirizzoOperativa = indirizzoOperativaRepository.save(indirizzoOperativa);
        return new CommonResponse(indirizzoOperativa.getId());
    }

    public IndirizzoOperativa getIndirizzoOperativoaById(Long id) {
        return indirizzoOperativaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Indirizzo operativo non trovato"));
    }

    public List<IndirizzoOperativa> getAllIndirizziOperativi() {return indirizzoOperativaRepository.findAll();}

    public IndirizzoOperativa updateIndirizzoOperativa(Long id, IndirizzoOperativaRequest request) {
        IndirizzoOperativa indirizzoOperativa = getIndirizzoOperativoaById(id);
        BeanUtils.copyProperties(request, indirizzoOperativa);
        return indirizzoOperativaRepository.save(indirizzoOperativa);
    }

    public void deleteIndirizzoOperativa(Long id) {
        IndirizzoOperativa indirizzoOperativa = getIndirizzoOperativoaById(id);
        indirizzoOperativaRepository.delete(indirizzoOperativa);
    }


}
