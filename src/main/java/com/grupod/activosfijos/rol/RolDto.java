package com.grupod.activosfijos.rol;

import java.io.Serializable;

public class RolDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idRol;
    private String nombre;

    public RolDto() {
    }

    public RolDto(Integer idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "RolDto{" +
                "idRol=" + idRol +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
