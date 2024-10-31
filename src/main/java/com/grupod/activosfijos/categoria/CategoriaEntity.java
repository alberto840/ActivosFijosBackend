package com.grupod.activosfijos.categoria;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class CategoriaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tiempo_de_vida")
    private Integer tiempoDeVida;

    @Column(name = "coeficiente_anual")
    private Double coeficienteAnual;

    public CategoriaEntity() {}

    public CategoriaEntity(Integer idCategoria, String nombre, Integer tiempoDeVida, Double coeficienteAnual) {
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
        return "CategoriaEntity{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", tiempoDeVida=" + tiempoDeVida +
                ", coeficienteAnual=" + coeficienteAnual +
                '}';
    }
}
