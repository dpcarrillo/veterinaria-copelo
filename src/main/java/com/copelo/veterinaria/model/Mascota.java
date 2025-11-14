package com.copelo.veterinaria.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String especie;
    
    private String raza;
    
    private LocalDate fechaNacimiento;
    
    @Column(nullable = false)
    private String sexo; // MACHO, HEMBRA
    
    private String color;
    
    private Double peso;
    
    @Column(length = 1000)
    private String observaciones;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dueno_id", nullable = false)
    private Dueno dueno;
    
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas = new ArrayList<>();
    
    // Constructores
    public Mascota() {}
    
    public Mascota(String nombre, String especie, String raza, LocalDate fechaNacimiento, 
                  String sexo, String color, Double peso, Dueno dueno) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.color = color;
        this.peso = peso;
        this.dueno = dueno;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public Dueno getDueno() { return dueno; }
    public void setDueno(Dueno dueno) { this.dueno = dueno; }
    
    public List<Cita> getCitas() { return citas; }
    public void setCitas(List<Cita> citas) { this.citas = citas; }
    
    // Método auxiliar para calcular edad
    public Integer getEdad() {
        if (fechaNacimiento == null) return null;
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }
}