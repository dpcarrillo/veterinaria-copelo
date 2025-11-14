package com.copelo.veterinaria.controller;

import com.copelo.veterinaria.model.Mascota;
import com.copelo.veterinaria.model.Dueno;
import com.copelo.veterinaria.repository.MascotaRepository;
import com.copelo.veterinaria.repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {
    
    @Autowired
    private MascotaRepository mascotaRepository;
    
    @Autowired
    private DuenoRepository duenoRepository;
    
    @GetMapping
    public String listarMascotas(Model model) {
        List<Mascota> mascotas = mascotaRepository.findAll();
        model.addAttribute("mascotas", mascotas);
        return "mascotas/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("duenos", duenoRepository.findAll());
        return "mascotas/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota, @RequestParam Long duenoId) {
        Optional<Dueno> optionalDueno = duenoRepository.findById(duenoId);
        if (optionalDueno.isPresent()) {
            Dueno dueno = optionalDueno.get();
            mascota.setDueno(dueno);
            mascotaRepository.save(mascota);
        }
        return "redirect:/mascotas";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id);
        if (optionalMascota.isPresent()) {
            Mascota mascota = optionalMascota.get();
            model.addAttribute("mascota", mascota);
            model.addAttribute("duenos", duenoRepository.findAll());
            return "mascotas/formulario";
        } else {
            return "redirect:/mascotas";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
        }
        return "redirect:/mascotas";
    }
}