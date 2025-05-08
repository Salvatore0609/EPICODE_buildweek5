package it.epicode.EPICODE_buildweek5.fatture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    Page<Fattura> findByClienteId(Long clienteId, Pageable pageable);
}