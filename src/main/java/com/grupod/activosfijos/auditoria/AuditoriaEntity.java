package com.grupod.activosfijos.auditoria;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
public class AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "accion", nullable = false)
    private String accion;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "detalles", length = 500)
    private String detalles;

    public AuditoriaEntity() {
    }

    public AuditoriaEntity(Long id) {
        this.id = id;
    }

    public AuditoriaEntity(Long id, Long idUsuario, String accion, LocalDateTime fecha, String detalles) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.fecha = fecha;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
