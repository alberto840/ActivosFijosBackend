package com.grupod.activosfijos.marca;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "marca")
public class MarcaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Integer idMarca;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "pais_origen", length = 100)
    private String paisOrigen;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    public MarcaEntity() {
    }

    public MarcaEntity(Integer idMarca, String nombre, String paisOrigen, String descripcion, Boolean estado) {
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
        return "MarcaEntity{" +
                "idMarca=" + idMarca +
                ", nombre='" + nombre + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
