package com.prueba.backendFrutas.repository;

import com.prueba.backendFrutas.domain.Fruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface FrutaRepository extends JpaRepository<Fruta, UUID> {
    Boolean existsByNombre(String nombre);
    Optional<Fruta> findByNombre(String nombre);
    Optional<Fruta> findById(UUID id);
}
