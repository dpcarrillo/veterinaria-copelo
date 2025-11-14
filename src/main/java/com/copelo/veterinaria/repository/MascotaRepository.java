package com.copelo.veterinaria.repository;

import com.copelo.veterinaria.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    List<Mascota> findByEspecie(String especie);
    List<Mascota> findByDuenoId(Long duenoId);
    List<Mascota> findAll();
    Optional<Mascota> findById(Long id);
    void deleteById(Long id);
}