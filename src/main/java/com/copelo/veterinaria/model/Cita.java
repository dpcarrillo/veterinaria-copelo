package com.copelo.veterinaria.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(nullable = false)
    private String tipo; // CONSULTA, VACUNACIÓN, CIRUGÍA, URGENCIA, ESTÉTICA
    
    @Column(length = 1000)
    private String motivo;
    
    private String diagnostico;
    
    private String tratamiento;
    
    @Column(nullable = false)
    private String estado; // PROGRAMADA, COMPLETADA, CANCELADA
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;
    
    // Constructores
    public Cita() {}
    
    public Cita(LocalDateTime fechaHora, String tipo, String motivo, String estado, Mascota mascota) {
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.motivo = motivo;
        this.estado = estado;
        this.mascota = mascota;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}