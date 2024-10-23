package com.grupod.activosfijos.sucursal;

import java.io.Serializable;

public class SucursalDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idSucursal;
    private String nombre;
    private Integer municipioId;

    public SucursalDto() {
    }

    public SucursalDto(Integer idSucursal, String nombre, Integer municipioId) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.municipioId = municipioId;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Integer municipioId) {
        this.municipioId = municipioId;
    }

    @Override
    public String toString() {
        return "SucursalDto{" +
                "idSucursal=" + idSucursal +
                ", nombre='" + nombre + '\'' +
                ", municipioId=" + municipioId +
                '}';
    }
}
