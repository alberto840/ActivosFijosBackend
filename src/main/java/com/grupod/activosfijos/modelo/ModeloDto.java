package com.grupod.activosfijos.modelo;

import java.io.Serializable;

public class ModeloDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idModelo;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private Integer marcaId;

    public ModeloDto() {
    }

    public ModeloDto(Integer idModelo, String nombre, String descripcion, Boolean estado, Integer marcaId) {
        this.idModelo = idModelo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.marcaId = marcaId;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    @Override
    public String toString() {
        return "ModeloDto{" +
                "idModelo=" + idModelo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", marcaId=" + marcaId +
                '}';
    }
}
