package it.epicode.EPICODE_buildweek5.clienti;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Page<Cliente> findByFatturatoAnnualeBetween(double fatturatoMin, double fatturatoMax, Pageable pageable);

    public Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(double fatturatoMin, Pageable pageable);

    public Page<Cliente> findByFatturatoAnnualeLessThanEqual(double fatturatoMax, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nomeContatto) LIKE LOWER(CONCAT('%', :nomeContatto, '%'))")
    Page<Cliente> searchByNomeContatto(@Param("nomeContatto") String nomeContatto, Pageable pageable);
}