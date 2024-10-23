package com.grupod.activosfijos.depreciacion;

import java.io.Serializable;
import java.util.Date;

public class DepreciacionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDepreciacion;
    private Date fecha;
    private String metodo;
    private String detalle;
    private Integer idDivisa;  // Solo el ID de la divisa

    // Constructor vacío
    public DepreciacionDto() {
    }

    // Constructor completo
    public DepreciacionDto(Integer idDepreciacion, Date fecha, String metodo, String detalle, Integer idDivisa) {
        this.idDepreciacion = idDepreciacion;
        this.fecha = fecha;
        this.metodo = metodo;
        this.detalle = detalle;
        this.idDivisa = idDivisa;  // Solo el ID de la divisa
    }

    // Getters y Setters
    public Integer getIdDepreciacion() {
        return idDepreciacion;
    }

    public void setIdDepreciacion(Integer idDepreciacion) {
        this.idDepreciacion = idDepreciacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(Integer idDivisa) {
        this.idDivisa = idDivisa;
    }

    @Override
    public String toString() {
        return "DepreciacionDto{" +
                "idDepreciacion=" + idDepreciacion +
                ", fecha=" + fecha +
                ", metodo='" + metodo + '\'' +
                ", detalle='" + detalle + '\'' +
                ", idDivisa=" + idDivisa +
                '}';
    }
}
