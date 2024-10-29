package com.grupod.activosfijos.auditoria;

import java.time.LocalDateTime;

public class AuditoriaDto {
    private Long id;
    private Long idUsuario;
    private String accion;
    private LocalDateTime fecha;
    private String detalles;

    public AuditoriaDto() {
    }

    public AuditoriaDto(Long id, Long idUsuario, String accion, LocalDateTime fecha, String detalles) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.fecha = fecha;
        this.detalles = detalles;
    }

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
