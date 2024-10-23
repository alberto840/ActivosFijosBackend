package com.grupod.activosfijos.estadoActivo;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "estadoactivo")
public class EstadoactivoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    public EstadoactivoEntity() {
    }

    public EstadoactivoEntity(Integer idEstado, String nombre, String descripcion) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstadoactivoEntity{" +
                "idEstado=" + idEstado +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
