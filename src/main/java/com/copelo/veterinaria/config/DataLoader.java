package com.copelo.veterinaria.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("✅ Aplicación iniciada correctamente");
        logger.info("🐾 Veterinaria Copelo - Sistema de autenticación");
        logger.info("📊 Base de datos: PostgreSQL");
    }
}