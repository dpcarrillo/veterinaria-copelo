package com.copelo.veterinaria.controller;

import com.copelo.veterinaria.model.Usuario;
import com.copelo.veterinaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email, 
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Email o contraseña incorrectos");
            return "redirect:/auth/login";
        }
    }
    
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }
    
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario,
                                  RedirectAttributes redirectAttributes) {
        try {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                redirectAttributes.addFlashAttribute("error", "El email ya está registrado");
                return "redirect:/auth/registro";
            }
            
            usuarioRepository.save(usuario);
            redirectAttributes.addFlashAttribute("success", "Registro exitoso. Ahora puedes iniciar sesión.");
            return "redirect:/auth/login";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error en el registro");
            return "redirect:/auth/registro";
        }
    }
}