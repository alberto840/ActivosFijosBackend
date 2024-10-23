package com.grupod.activosfijos.historialActivos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HistorialActivosDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idHistorial;
    private String accion;
    private BigDecimal valorActual;
    private Date fechaModificacion;
    private String comprobante;
    private String detalle;
    private boolean estado;
    private String estadoUso;
    private Integer idActivo;
    private Integer idAula;
    private Integer idCustodio;
    private Integer idProyecto;
    private Integer idUsuario;

    public HistorialActivosDto() {
    }

    public HistorialActivosDto(Integer idHistorial, String accion, BigDecimal valorActual, Date fechaModificacion, String comprobante, String detalle, boolean estado, String estadoUso, Integer idActivo, Integer idAula, Integer idCustodio, Integer idProyecto, Integer idUsuario) {
        this.idHistorial = idHistorial;
        this.accion = accion;
        this.valorActual = valorActual;
        this.fechaModificacion = fechaModificacion;
        this.comprobante = comprobante;
        this.detalle = detalle;
        this.estado = estado;
        this.estadoUso = estadoUso;
        this.idActivo = idActivo;
        this.idAula = idAula;
        this.idCustodio = idCustodio;
        this.idProyecto = idProyecto;
        this.idUsuario = idUsuario;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEstadoUso() {
        return estadoUso;
    }

    public void setEstadoUso(String estadoUso) {
        this.estadoUso = estadoUso;
    }

    public Integer getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public Integer getIdCustodio() {
        return idCustodio;
    }

    public void setIdCustodio(Integer idCustodio) {
        this.idCustodio = idCustodio;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "HistorialActivosDto{" +
                "idHistorial=" + idHistorial +
                ", accion='" + accion + '\'' +
                ", valorActual=" + valorActual +
                ", fechaModificacion=" + fechaModificacion +
                ", comprobante='" + comprobante + '\'' +
                ", detalle='" + detalle + '\'' +
                ", estado=" + estado +
                ", estadoUso='" + estadoUso + '\'' +
                ", idActivo=" + idActivo +
                ", idAula=" + idAula +
                ", idCustodio=" + idCustodio +
                ", idProyecto=" + idProyecto +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
