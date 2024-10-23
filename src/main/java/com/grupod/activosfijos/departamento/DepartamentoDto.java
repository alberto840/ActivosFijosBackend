package com.grupod.activosfijos.departamento;

import java.io.Serializable;

public class DepartamentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDepartamento;
    private String nombre;
    private Integer idPais;

    public DepartamentoDto() {
    }

    public DepartamentoDto(Integer idDepartamento, String nombre, Integer idPais) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.idPais = idPais;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    @Override
    public String toString() {
        return "DepartamentoDto{" +
                "idDepartamento=" + idDepartamento +
                ", nombre='" + nombre + '\'' +
                ", idPais=" + idPais +
                '}';
    }
}
