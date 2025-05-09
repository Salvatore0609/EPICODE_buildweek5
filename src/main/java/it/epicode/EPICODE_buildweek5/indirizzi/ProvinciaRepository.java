package it.epicode.EPICODE_buildweek5.indirizzi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    Optional<Provincia> findByProvincia(String provincia);
}