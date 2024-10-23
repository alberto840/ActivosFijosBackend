package com.grupod.activosfijos.divisa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DivisasDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idDivisa;
    private String nombre;
    private BigDecimal valor;
    private Date fecha;
    private String abreviacion;  // Nuevo atributo

    public DivisasDto() {
    }

    public DivisasDto(Integer idDivisa, String nombre, BigDecimal valor, Date fecha, String abreviacion) {
        this.idDivisa = idDivisa;
        this.nombre = nombre;
        this.valor = valor;
        this.fecha = fecha;
        this.abreviacion = abreviacion;  // Asignar valor del nuevo atributo
    }

    public Integer getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(Integer idDivisa) {
        this.idDivisa = idDivisa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAbreviacion() {  // Getter del nuevo atributo
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {  // Setter del nuevo atributo
        this.abreviacion = abreviacion;
    }

    @Override
    public String toString() {
        return "DivisasDto{" +
                "idDivisa=" + idDivisa +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", fecha=" + fecha +
                ", abreviacion='" + abreviacion + '\'' +  // Incluir el nuevo atributo en el `toString`
                '}';
    }
}
