package it.epicode.EPICODE_buildweek5.indirizzi;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/province")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<Provincia> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {

        return provinciaService.getAllProvince(page, size, sortBy);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Provincia getProvinciaById(@PathVariable Long id) {return provinciaService.getProvinciaById(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createProvincia(@RequestBody ProvinciaRequest request) {return provinciaService.createProvincia(request);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Provincia updateProvincia(@PathVariable Long id,@RequestBody ProvinciaRequest request) {return provinciaService.updateProvincia(id, request);}

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProvincia(@PathVariable Long id) {provinciaService.deleteProvincia(id);}
}
