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

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    public Provincia getProvinciaById(Long id) {
        return provinciaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
    }

    public Page<Provincia> getAllProvince(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return provinciaRepository.findAll(pageable);
    }
    public CommonResponse createProvincia(ProvinciaRequest request) {
        Provincia provincia = new Provincia();
        BeanUtils.copyProperties(request, provincia);

        Comune comune = comuneRepository.findById(request.getComuneId())
                .orElseThrow(() -> new EntityNotFoundException("Comune non trovato"));
        comune.setProvincia(provincia);
        provincia.setComuni(new ArrayList<>(List.of(comune)));

        provincia = provinciaRepository.save(provincia);
        return new CommonResponse(provincia.getId());
    }
    public Provincia updateProvincia(Long id, ProvinciaRequest request) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
        BeanUtils.copyProperties(request, provincia);

        return provinciaRepository.save(provincia);
    }

    public void deleteProvincia(Long id) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Provincia non trovata"));
        provinciaRepository.delete(provincia);
    }
}
