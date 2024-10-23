package com.grupod.activosfijos.modelo;

import com.grupod.activosfijos.marca.MarcaEntity;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "modelo")
public class ModeloEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Integer idModelo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "marca_id", referencedColumnName = "id_marca", nullable = false)
    private MarcaEntity marca;

    public ModeloEntity() {
    }

    public ModeloEntity(Integer idModelo, String nombre, String descripcion, Boolean estado, MarcaEntity marca) {
        this.idModelo = idModelo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.marca = marca;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public MarcaEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "ModeloEntity{" +
                "idModelo=" + idModelo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", marca=" + marca +
                '}';
    }
}
