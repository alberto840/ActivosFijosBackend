package com.grupod.activosfijos.pais;

import java.io.Serializable;

public class PaisDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idPais;
    private String nombre;

    public PaisDto() {
    }

    public PaisDto(Integer idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PaisDto{" +
                "idPais=" + idPais +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
