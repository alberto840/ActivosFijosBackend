package com.grupod.activosfijos.identificador;

import java.io.Serializable;

public class IdentificadorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idIdentificador;
    private String codigoQr;
    private String codigoBarra;
    private Integer idActivo; // ID del Activo relacionado

    public IdentificadorDto() {
    }

    public IdentificadorDto(Integer idIdentificador, String codigoQr, String codigoBarra, Integer idActivo) {
        this.idIdentificador = idIdentificador;
        this.codigoQr = codigoQr;
        this.codigoBarra = codigoBarra;
        this.idActivo = idActivo;
    }

    // Getters y Setters
    public Integer getIdIdentificador() {
        return idIdentificador;
    }

    public void setIdIdentificador(Integer idIdentificador) {
        this.idIdentificador = idIdentificador;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Integer getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    @Override
    public String toString() {
        return "IdentificadorDto{" +
                "idIdentificador=" + idIdentificador +
                ", codigoQr='" + codigoQr + '\'' +
                ", codigoBarra='" + codigoBarra + '\'' +
                ", idActivo=" + idActivo +
                '}';
    }
}
