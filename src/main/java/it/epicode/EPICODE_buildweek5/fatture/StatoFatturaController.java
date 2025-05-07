package it.epicode.EPICODE_buildweek5.fatture;

import it.epicode.EPICODE_buildweek5.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/stato-fatture")
public class StatoFatturaController {
    @Autowired
    private StatoFatturaService statoFatturaService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public Page<StatoFattura> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {

        return statoFatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public StatoFattura findById(@PathVariable Long id) {
        return statoFatturaService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse save(@RequestBody StatoFatturaRequest request) {
        return statoFatturaService.createStatoFattura(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StatoFattura updateStatoFattura(@PathVariable Long id, @RequestBody StatoFatturaRequest request) {
        return statoFatturaService.updateStatoFattura(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteStatoFattura(@PathVariable Long id) {
        statoFatturaService.deleteStatoFattura(id);
    }
}
