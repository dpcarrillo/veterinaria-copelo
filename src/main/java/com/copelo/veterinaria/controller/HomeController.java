package com.copelo.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        // Redirigir directamente a la página de login
        return "redirect:/auth/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        // Aquí irá el dashboard después del login
        return "dashboard";
    }
}