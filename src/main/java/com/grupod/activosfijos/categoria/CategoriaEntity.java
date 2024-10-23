package com.grupod.activosfijos.categoria;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class CategoriaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    // Constructor vacío
    public CategoriaEntity() {}

    // Constructor con todos los parámetros
    public CategoriaEntity(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CategoriaEntity)) {
            return false;
        }
        CategoriaEntity other = (CategoriaEntity) object;
        return (this.idCategoria != null || other.idCategoria == null) &&
                (this.idCategoria == null || this.idCategoria.equals(other.idCategoria));
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.categoria.CategoriaEntity[ idCategoria=" + idCategoria + " ]";
    }
}
