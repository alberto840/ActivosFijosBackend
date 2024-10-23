package com.grupod.activosfijos.aula;

import java.io.Serializable;

public class AulaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idAula;
    private String nombre;
    private Integer idBloque;  // Solo el ID del bloque

    // Constructor vacío
    public AulaDto() {}

    // Constructor completo
    public AulaDto(Integer idAula, String nombre, Integer idBloque) {
        this.idAula = idAula;
        this.nombre = nombre;
        this.idBloque = idBloque;
    }

    // Getters y Setters
    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    @Override
    public String toString() {
        return "AulaDto{" +
                "idAula=" + idAula +
                ", nombre='" + nombre + '\'' +
                ", idBloque=" + idBloque +
                '}';
    }
}
