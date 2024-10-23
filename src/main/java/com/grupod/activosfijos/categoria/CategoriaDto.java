package com.grupod.activosfijos.categoria;

import java.io.Serializable;

public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCategoria;
    private String nombre;


    public CategoriaDto() {
    }

    public CategoriaDto(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CategoriaDto{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
