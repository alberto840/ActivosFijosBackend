package com.grupod.activosfijos.proyecto;

import java.io.Serializable;
import java.util.Date;

public class ProyectoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idProyecto;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer idArea; // Solo el ID del área

    // Constructor vacío
    public ProyectoDto() {}

    // Constructor completo
    public ProyectoDto(Integer idProyecto, String nombre, Date fechaInicio, Date fechaFin, Integer idArea) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idArea = idArea;
    }

    // Getters y Setters
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    @Override
    public String toString() {
        return "ProyectoDto{" +
                "idProyecto=" + idProyecto +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", idArea=" + idArea +
                '}';
    }
}
