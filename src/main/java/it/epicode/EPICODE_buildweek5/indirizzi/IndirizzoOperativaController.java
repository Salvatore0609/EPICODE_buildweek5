package it.epicode.EPICODE_buildweek5.indirizzi;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/indirizziOperativi")
public class IndirizzoOperativaController {
    @Autowired
    private IndirizzoOperativaService indirizzoOperativoaService;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IndirizzoOperativa findById(@PathVariable Long id) {return indirizzoOperativoaService.getIndirizzoOperativoaById(id);}
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<IndirizzoOperativa> getAllIndirizziOperativi() {return indirizzoOperativoaService.getAllIndirizziOperativi();}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createIndirizzoOperativo( @RequestBody IndirizzoOperativaRequest request) {return indirizzoOperativoaService.createIndirizzoOperativa(request);}

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIndirizzoOperativo( @PathVariable Long id) {indirizzoOperativoaService.deleteIndirizzoOperativa(id);}

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IndirizzoOperativa updateIndirizzoOperativo(@PathVariable Long id, @RequestBody IndirizzoOperativaRequest request) {return indirizzoOperativoaService.updateIndirizzoOperativa(id, request);}
}
