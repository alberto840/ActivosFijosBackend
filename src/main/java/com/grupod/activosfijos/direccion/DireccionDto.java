package com.grupod.activosfijos.direccion;

import java.io.Serializable;

public class DireccionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDireccion;
    private String calle;
    private String detalle;
    private String zona;

    public DireccionDto() {
    }

    public DireccionDto(Integer idDireccion, String calle, String detalle, String zona) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.detalle = detalle;
        this.zona = zona;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "DireccionDto{" +
                "idDireccion=" + idDireccion +
                ", calle='" + calle + '\'' +
                ", detalle='" + detalle + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}
