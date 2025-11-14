package com.copelo.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "redirect:/auth/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}