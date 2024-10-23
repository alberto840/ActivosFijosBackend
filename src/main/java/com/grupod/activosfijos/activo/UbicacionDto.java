package com.grupod.activosfijos.activo;

import java.io.Serializable;

public class UbicacionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreAula;
    private String nombreBloque;
    private String calleDireccion;
    private String detalleDireccion;
    private String zonaDireccion;
    private String nombreMunicipio;
    private String nombreProvincia;
    private String nombreDepartamento;
    private String nombrePais;
    private String nombreSucursal;

    // Constructor vacío
    public UbicacionDto() {}

    // Constructor completo
    public UbicacionDto(String nombreAula, String nombreBloque, String calleDireccion, String detalleDireccion, String zonaDireccion, String nombreMunicipio, String nombreProvincia, String nombreDepartamento, String nombrePais, String nombreSucursal) {
        this.nombreAula = nombreAula;
        this.nombreBloque = nombreBloque;
        this.calleDireccion = calleDireccion;
        this.detalleDireccion = detalleDireccion;
        this.zonaDireccion = zonaDireccion;
        this.nombreMunicipio = nombreMunicipio;
        this.nombreProvincia = nombreProvincia;
        this.nombreDepartamento = nombreDepartamento;
        this.nombrePais = nombrePais;
        this.nombreSucursal = nombreSucursal;
    }

    // Getters y Setters
    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getNombreBloque() {
        return nombreBloque;
    }

    public void setNombreBloque(String nombreBloque) {
        this.nombreBloque = nombreBloque;
    }

    public String getCalleDireccion() {
        return calleDireccion;
    }

    public void setCalleDireccion(String calleDireccion) {
        this.calleDireccion = calleDireccion;
    }

    public String getDetalleDireccion() {
        return detalleDireccion;
    }

    public void setDetalleDireccion(String detalleDireccion) {
        this.detalleDireccion = detalleDireccion;
    }

    public String getZonaDireccion() {
        return zonaDireccion;
    }

    public void setZonaDireccion(String zonaDireccion) {
        this.zonaDireccion = zonaDireccion;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    @Override
    public String toString() {
        return "UbicacionDto{" +
                "nombreAula='" + nombreAula + '\'' +
                ", nombreBloque='" + nombreBloque + '\'' +
                ", calleDireccion='" + calleDireccion + '\'' +
                ", detalleDireccion='" + detalleDireccion + '\'' +
                ", zonaDireccion='" + zonaDireccion + '\'' +
                ", nombreMunicipio='" + nombreMunicipio + '\'' +
                ", nombreProvincia='" + nombreProvincia + '\'' +
                ", nombreDepartamento='" + nombreDepartamento + '\'' +
                ", nombrePais='" + nombrePais + '\'' +
                ", nombreSucursal='" + nombreSucursal + '\'' +
                '}';
    }
}
