package com.grupod.activosfijos.categoria;

import java.io.Serializable;


public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCategoria;
    private String nombre;
    private Integer tiempoDeVida;
    private Double coeficienteAnual;


    public CategoriaDto(Integer idCategoria, String nombre, Integer tiempoDeVida, Double coeficienteAnual) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.tiempoDeVida = tiempoDeVida;
        this.coeficienteAnual = coeficienteAnual;
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

    public Integer getTiempoDeVida() {
        return tiempoDeVida;
    }

    public void setTiempoDeVida(Integer tiempoDeVida) {
        this.tiempoDeVida = tiempoDeVida;
    }

    public Double getCoeficienteAnual() {
        return coeficienteAnual;
    }

    public void setCoeficienteAnual(Double coeficienteAnual) {
        this.coeficienteAnual = coeficienteAnual;
    }

    @Override
    public String toString() {
        return "CategoriaDto{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", tiempoDeVida=" + tiempoDeVida +
                ", coeficienteAnual=" + coeficienteAnual +
                '}';
    }
}
