package com.grupod.activosfijos.activo;

import java.math.BigDecimal;
import java.util.Date;

public class ActivoDto {

    private Integer idActivo;
    private String nombre;
    private BigDecimal valorActual;
    private BigDecimal valorInicial;
    private Date fechaRegistro;
    private String detalle;
    private boolean estado;
    private BigDecimal precio;
    private String comprobanteCompra;
    private String estadoActivo;
    private Integer aulaId;
    private Integer categoriaId;
    private Integer custodioId;
    private Integer proyectoId;
    private Integer idModelo;
    private String codigoUbicacion;
    private String ciCustodio;
    private String codigoProyecto;

    // Constructor vacío (agregar este)
    public ActivoDto() {}

    // Constructor completo
    public ActivoDto(Integer idActivo, String nombre, BigDecimal valorActual, BigDecimal valorInicial, Date fechaRegistro,
                     String detalle, boolean estado, BigDecimal precio, String comprobanteCompra, String estadoActivo,
                     Integer aulaId, Integer categoriaId, Integer custodioId, Integer proyectoId, Integer idModelo,
                     String codigoUbicacion, String ciCustodio, String codigoProyecto) {
        this.idActivo = idActivo;
        this.nombre = nombre;
        this.valorActual = valorActual;
        this.valorInicial = valorInicial;
        this.fechaRegistro = fechaRegistro;
        this.detalle = detalle;
        this.estado = estado;
        this.precio = precio;
        this.comprobanteCompra = comprobanteCompra;
        this.estadoActivo = estadoActivo;
        this.aulaId = aulaId;
        this.categoriaId = categoriaId;
        this.custodioId = custodioId;
        this.proyectoId = proyectoId;
        this.idModelo = idModelo;
        this.codigoUbicacion = codigoUbicacion;
        this.ciCustodio = ciCustodio;
        this.codigoProyecto = codigoProyecto;
    }

    public Integer getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getComprobanteCompra() {
        return comprobanteCompra;
    }

    public void setComprobanteCompra(String comprobanteCompra) {
        this.comprobanteCompra = comprobanteCompra;
    }

    public String getEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(String estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
        this.aulaId = aulaId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getCustodioId() {
        return custodioId;
    }

    public void setCustodioId(Integer custodioId) {
        this.custodioId = custodioId;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public String getCiCustodio() {
        return ciCustodio;
    }

    public void setCiCustodio(String ciCustodio) {
        this.ciCustodio = ciCustodio;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    @Override
    public String toString() {
        return "ActivoDto{" +
                "idActivo=" + idActivo +
                ", nombre='" + nombre + '\'' +
                ", valorActual=" + valorActual +
                ", valorInicial=" + valorInicial +
                ", fechaRegistro=" + fechaRegistro +
                ", detalle='" + detalle + '\'' +
                ", estado=" + estado +
                ", precio=" + precio +
                ", comprobanteCompra='" + comprobanteCompra + '\'' +
                ", estadoActivo='" + estadoActivo + '\'' +
                ", aulaId=" + aulaId +
                ", categoriaId=" + categoriaId +
                ", custodioId=" + custodioId +
                ", proyectoId=" + proyectoId +
                ", idModelo=" + idModelo +
                ", codigoUbicacion='" + codigoUbicacion + '\'' +
                ", ciCustodio='" + ciCustodio + '\'' +
                ", codigoProyecto='" + codigoProyecto + '\'' +
                '}';
    }
}
