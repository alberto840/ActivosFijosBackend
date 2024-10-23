package com.grupod.activosfijos.activo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idActivo;
    private String nombre;
    private BigDecimal valorActual;
    private BigDecimal valorInicial;
    private Date fechaRegistro;
    private String detalle;
    private boolean estado;
    private BigDecimal precio;
    private String comprobanteCompra;

    // IDs de las entidades relacionadas
    private Integer idAula;
    private Integer idBloque;
    private Integer idCategoria;
    private Integer idCustodio;
    private Integer idDepreciacion;
    private Integer idEstadoactivo;
    private Integer idProyecto;
    private Integer idModelo;

    public ActivoDto() {
    }

    public ActivoDto(Integer idActivo, String nombre, BigDecimal valorActual, BigDecimal valorInicial, Date fechaRegistro, String detalle, boolean estado, BigDecimal precio, String comprobanteCompra, Integer idAula, Integer idBloque, Integer idCategoria, Integer idCustodio, Integer idDepreciacion, Integer idEstadoactivo, Integer idProyecto, Integer idModelo) {
        this.idActivo = idActivo;
        this.nombre = nombre;
        this.valorActual = valorActual;
        this.valorInicial = valorInicial;
        this.fechaRegistro = fechaRegistro;
        this.detalle = detalle;
        this.estado = estado;
        this.precio = precio;
        this.comprobanteCompra = comprobanteCompra;
        this.idAula = idAula;
        this.idBloque = idBloque;
        this.idCategoria = idCategoria;
        this.idCustodio = idCustodio;
        this.idDepreciacion = idDepreciacion;
        this.idEstadoactivo = idEstadoactivo;
        this.idProyecto = idProyecto;
        this.idModelo = idModelo;
    }

    // Getters y setters
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

    public boolean getEstado() {
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

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCustodio() {
        return idCustodio;
    }

    public void setIdCustodio(Integer idCustodio) {
        this.idCustodio = idCustodio;
    }

    public Integer getIdDepreciacion() {
        return idDepreciacion;
    }

    public void setIdDepreciacion(Integer idDepreciacion) {
        this.idDepreciacion = idDepreciacion;
    }

    public Integer getIdEstadoactivo() {
        return idEstadoactivo;
    }

    public void setIdEstadoactivo(Integer idEstadoactivo) {
        this.idEstadoactivo = idEstadoactivo;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
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
                ", idAula=" + idAula +
                ", idBloque=" + idBloque +
                ", idCategoria=" + idCategoria +
                ", idCustodio=" + idCustodio +
                ", idDepreciacion=" + idDepreciacion +
                ", idEstadoactivo=" + idEstadoactivo +
                ", idProyecto=" + idProyecto +
                ", idModelo=" + idModelo +
                '}';
    }
}
