package com.copelo.veterinaria.controller;

import com.copelo.veterinaria.model.Dueno;
import com.copelo.veterinaria.repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/duenos")
public class DuenoController {
    
    @Autowired
    private DuenoRepository duenoRepository;
    
    @GetMapping
    public String listarDuenos(Model model) {
        List<Dueno> duenos = duenoRepository.findAll();
        model.addAttribute("duenos", duenos);
        return "duenos/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("dueno", new Dueno());
        return "duenos/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarDueno(@ModelAttribute Dueno dueno) {
        duenoRepository.save(dueno);
        return "redirect:/duenos";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Opción B: Manejo manual con isPresent()
        Optional<Dueno> optionalDueno = duenoRepository.findById(id);
        if (optionalDueno.isPresent()) {
            Dueno dueno = optionalDueno.get();
            model.addAttribute("dueno", dueno);
            return "duenos/formulario";
        } else {
            // Redirigir si no se encuentra el dueño
            return "redirect:/duenos";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarDueno(@PathVariable Long id) {
        if (duenoRepository.existsById(id)) {
            duenoRepository.deleteById(id);
        }
        return "redirect:/duenos";
    }
}