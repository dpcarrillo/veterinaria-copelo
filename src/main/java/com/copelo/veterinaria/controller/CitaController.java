package com.copelo.veterinaria.controller;

import com.copelo.veterinaria.model.Cita;
import com.copelo.veterinaria.model.Mascota;
import com.copelo.veterinaria.repository.CitaRepository;
import com.copelo.veterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/citas")
public class CitaController {
    
    private static final Logger logger = LoggerFactory.getLogger(CitaController.class);
    
    @Autowired
    private CitaRepository citaRepository;
    
    @Autowired
    private MascotaRepository mascotaRepository;
    
    @GetMapping
    public String listarCitas(Model model) {
        List<Cita> citas = citaRepository.findAll();
        model.addAttribute("citas", citas);
        return "citas/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "citas/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Cita cita, @RequestParam Long mascotaId) {
        try {
            logger.info("Intentando guardar cita: {}", cita);
            logger.info("Mascota ID recibido: {}", mascotaId);
            
            Optional<Mascota> optionalMascota = mascotaRepository.findById(mascotaId);
            if (optionalMascota.isPresent()) {
                Mascota mascota = optionalMascota.get();
                logger.info("Mascota encontrada: {} - {}", mascota.getId(), mascota.getNombre());
                
                cita.setMascota(mascota);
                
                // Asegurarnos de que el estado no sea nulo
                if (cita.getEstado() == null) {
                    cita.setEstado("PROGRAMADA");
                }
                
                Cita citaGuardada = citaRepository.save(cita);
                logger.info("Cita guardada exitosamente con ID: {}", citaGuardada.getId());
                
            } else {
                logger.error("No se encontró mascota con ID: {}", mascotaId);
                return "redirect:/citas?error=Mascota+no+encontrada";
            }
            
        } catch (Exception e) {
            logger.error("Error al guardar cita: {}", e.getMessage(), e);
            return "redirect:/citas?error=Error+al+guardar+cita";
        }
        
        return "redirect:/citas";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        try {
            Optional<Cita> optionalCita = citaRepository.findById(id);
            if (optionalCita.isPresent()) {
                Cita cita = optionalCita.get();
                model.addAttribute("cita", cita);
                model.addAttribute("mascotas", mascotaRepository.findAll());
                return "citas/formulario";
            } else {
                logger.warn("Cita no encontrada con ID: {}", id);
                return "redirect:/citas?error=Cita+no+encontrada";
            }
        } catch (Exception e) {
            logger.error("Error al editar cita: {}", e.getMessage(), e);
            return "redirect:/citas?error=Error+al+cargar+cita";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        try {
            if (citaRepository.existsById(id)) {
                citaRepository.deleteById(id);
                logger.info("Cita eliminada con ID: {}", id);
            }
        } catch (Exception e) {
            logger.error("Error al eliminar cita: {}", e.getMessage(), e);
        }
        return "redirect:/citas";
    }
}