package it.epicode.EPICODE_buildweek5.clienti;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
   /* boolean existsByNomeECognome(String nomeContatto, String cognomeContatto);

    boolean existsByPartitaIva(String partitaIva);

    boolean existsByPec(String pec);*/
}