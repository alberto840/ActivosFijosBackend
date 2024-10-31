package com.grupod.activosfijos.proyecto;

import java.io.Serializable;
import java.util.Date;

public class ProyectoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idProyecto;
    private String nombre;
    private String codigoProyecto; // Nuevo campo
    private Date fechaInicio;
    private Date fechaFin;
    private Integer idArea;

    public ProyectoDto() {}

    public ProyectoDto(Integer idProyecto, String nombre, String codigoProyecto, Date fechaInicio, Date fechaFin, Integer idArea) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.codigoProyecto = codigoProyecto;
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

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
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
                ", codigoProyecto='" + codigoProyecto + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", idArea=" + idArea +
                '}';
    }
}
