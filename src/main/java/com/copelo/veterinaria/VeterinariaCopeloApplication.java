package com.copelo.veterinaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeterinariaCopeloApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(VeterinariaCopeloApplication.class, args);
        System.out.println("\n✅ Aplicación iniciada en: http://localhost:8081\n");
    }
}