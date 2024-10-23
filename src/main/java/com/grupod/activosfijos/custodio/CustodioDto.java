package com.grupod.activosfijos.custodio;

import java.io.Serializable;

public class CustodioDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCustodio;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private String ci;

    public CustodioDto() {
    }

    public CustodioDto(Integer idCustodio, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, String ci) {
        this.idCustodio = idCustodio;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.ci = ci;
    }

    public Integer getIdCustodio() {
        return idCustodio;
    }

    public void setIdCustodio(Integer idCustodio) {
        this.idCustodio = idCustodio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    @Override
    public String toString() {
        return "CustodioDto{" +
                "idCustodio=" + idCustodio +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", ci='" + ci + '\'' +
                '}';
    }
}
