package it.epicode.EPICODE_buildweek5.indirizzi;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Comune getComuneById(Long id) {
        return comuneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comune non trovato"));
    }

    public Page<Comune> getAllComuni(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return comuneRepository.findAll(pageable);}

    public CommonResponse createComune(ComuneRequest request) {
        Comune comune = new Comune();
        BeanUtils.copyProperties(request, comune);
        Provincia provincia = provinciaRepository.findById(request.getProvinciaId())
                .orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
        comune.setProvincia(provincia);
        comune = comuneRepository.save(comune);
        return new CommonResponse(comune.getId());
    }

    public Comune updateComune(Long id, ComuneRequest request) {
        Comune comune = getComuneById(id);
        BeanUtils.copyProperties(request, comune);
        return comuneRepository.save(comune);
    }

    public void deleteComune(Long id) {
        Comune comune = getComuneById(id);
        comuneRepository.delete(comune);
    }
}
