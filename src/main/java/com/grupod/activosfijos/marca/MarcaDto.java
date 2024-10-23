package com.grupod.activosfijos.marca;

import java.io.Serializable;

public class MarcaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idMarca;
    private String nombre;
    private String paisOrigen;
    private String descripcion;
    private Boolean estado;

    public MarcaDto() {
    }

    public MarcaDto(Integer idMarca, String nombre, String paisOrigen, String descripcion, Boolean estado) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "MarcaDto{" +
                "idMarca=" + idMarca +
                ", nombre='" + nombre + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
