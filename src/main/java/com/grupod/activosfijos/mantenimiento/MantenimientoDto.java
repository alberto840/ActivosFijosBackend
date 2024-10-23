package com.grupod.activosfijos.mantenimiento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MantenimientoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idMantenimiento;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal costo;

    public MantenimientoDto() {
    }

    public MantenimientoDto(Integer idMantenimiento, Date fechaInicio, Date fechaFin, BigDecimal costo) {
        this.idMantenimiento = idMantenimiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "MantenimientoDto{" +
                "idMantenimiento=" + idMantenimiento +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", costo=" + costo +
                '}';
    }
}
