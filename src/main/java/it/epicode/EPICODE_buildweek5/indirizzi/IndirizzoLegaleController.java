package it.epicode.EPICODE_buildweek5.indirizzi;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/indirizziLegali")
public class IndirizzoLegaleController {
    @Autowired
    private IndirizzoLegaleService indirizzoLegaleService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<IndirizzoLegale> getAllIndirizziLegali() {return indirizzoLegaleService.getAllIndirizziLegali();}

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IndirizzoLegale getIndirizzoLegaleById(@PathVariable Long id) {return indirizzoLegaleService.getIndirizzoLegaleById(id);}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createIndirizzoLegale(IndirizzoLegaleRequest request) {return indirizzoLegaleService.createIndirizzoLegale(request);}

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIndirizzoLegale(Long id) {indirizzoLegaleService.deleteIndirizzoLegale(id);}
}


