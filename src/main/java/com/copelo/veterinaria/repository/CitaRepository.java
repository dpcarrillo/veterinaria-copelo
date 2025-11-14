package com.copelo.veterinaria.repository;

import com.copelo.veterinaria.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Cita> findByEstado(String estado);
    List<Cita> findByMascotaId(Long mascotaId);
    List<Cita> findAll();
    Optional<Cita> findById(Long id);
}