package com.grupod.activosfijos.area;

import java.io.Serializable;

public class AreaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idArea;
    private String nombre;
    private Integer idEmpresa;  // Solo el ID de la empresa

    // Constructor vacío
    public AreaDto() {}

    // Constructor completo
    public AreaDto(Integer idArea, String nombre, Integer idEmpresa) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.idEmpresa = idEmpresa;
    }

    // Getters y Setters
    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public String toString() {
        return "AreaDto{" +
                "idArea=" + idArea +
                ", nombre='" + nombre + '\'' +
                ", idEmpresa=" + idEmpresa +
                '}';
    }
}
