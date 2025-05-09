package it.epicode.EPICODE_buildweek5.fatture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    Page<Fattura> findByClienteId(Long clienteId, Pageable pageable);

    Page<Fattura> findByStatoFatturaId(Long statoFatturaId, Pageable pageable);

    Page<Fattura> findByData(LocalDate data, Pageable pageable);

    Page<Fattura> findByAnno(Integer anno, Pageable pageable);

    Page<Fattura> findByRangeDiImporti(Integer rangeDiImporti, Pageable pageable);
}