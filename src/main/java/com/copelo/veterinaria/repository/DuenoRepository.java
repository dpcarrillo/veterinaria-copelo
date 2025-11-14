package com.copelo.veterinaria.repository;

import com.copelo.veterinaria.model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {
    List<Dueno> findByNombreContainingIgnoreCase(String nombre);
    Dueno findByEmail(String email);
    List<Dueno> findAll();
    Optional<Dueno> findById(Long duenoId);
}