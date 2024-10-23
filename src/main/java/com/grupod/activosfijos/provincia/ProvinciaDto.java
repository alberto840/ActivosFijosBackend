package com.grupod.activosfijos.provincia;

import java.io.Serializable;

public class ProvinciaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idProvincia;
    private String nombre;
    private Integer idDepartamento;

    public ProvinciaDto() {
    }

    public ProvinciaDto(Integer idProvincia, String nombre, Integer idDepartamento) {
        this.idProvincia = idProvincia;
        this.nombre = nombre;
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public String toString() {
        return "ProvinciaDto{" +
                "idProvincia=" + idProvincia +
                ", nombre='" + nombre + '\'' +
                ", idDepartamento=" + idDepartamento +
                '}';
    }
}
