package com.grupod.activosfijos.bloque;

import java.io.Serializable;

public class BloqueDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idBloque;
    private String nombre;
    private Integer idDireccion;
    private Integer idSucursal;

    // Constructor vacío
    public BloqueDto() {}

    // Constructor completo
    public BloqueDto(Integer idBloque, String nombre, Integer idDireccion, Integer idSucursal) {
        this.idBloque = idBloque;
        this.nombre = nombre;
        this.idDireccion = idDireccion;
        this.idSucursal = idSucursal;
    }

    // Getters y Setters
    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public String toString() {
        return "BloqueDto{" +
                "idBloque=" + idBloque +
                ", nombre='" + nombre + '\'' +
                ", idDireccion=" + idDireccion +
                ", idSucursal=" + idSucursal +
                '}';
    }
}
