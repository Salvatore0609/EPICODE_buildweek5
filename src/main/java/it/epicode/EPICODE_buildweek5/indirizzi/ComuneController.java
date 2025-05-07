package it.epicode.EPICODE_buildweek5.indirizzi;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comuni")
public class ComuneController {
    @Autowired
    private ComuneService comuneService;

    @GetMapping("/all")
    public Page<Comune> getAllComuni(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return comuneService.getAllComuni(page, size, sortBy);
    }
    @GetMapping("/get/{id}")
    public Comune getComuneById(@PathVariable Long id) {return comuneService.getComuneById(id);}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse createComune(@RequestBody ComuneRequest request) {return comuneService.createComune(request);}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comune updateComune(@PathVariable Long id,@RequestBody ComuneRequest request) {return comuneService.updateComune(id, request);}
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComune(@PathVariable Long id) {comuneService.deleteComune(id);}
}
