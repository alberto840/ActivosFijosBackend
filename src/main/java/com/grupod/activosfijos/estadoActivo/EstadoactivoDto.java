package com.grupod.activosfijos.estadoActivo;

import java.io.Serializable;

public class EstadoactivoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idEstado;
    private String nombre;
    private String descripcion;

    public EstadoactivoDto() {
    }

    public EstadoactivoDto(Integer idEstado, String nombre, String descripcion) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstadoactivoDto{" +
                "idEstado=" + idEstado +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
