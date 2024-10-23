package com.grupod.activosfijos.aula;

import com.grupod.activosfijos.bloque.BloqueEntity;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "aula")
public class AulaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Integer idAula;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @JoinColumn(name = "bloque_id", referencedColumnName = "id_bloque")
    @ManyToOne(optional = false)
    private BloqueEntity bloqueEntity;

    // Constructor vacío
    public AulaEntity() {}

    // Constructor completo
    public AulaEntity(Integer idAula, String nombre, BloqueEntity bloqueEntity) {
        this.idAula = idAula;
        this.nombre = nombre;
        this.bloqueEntity = bloqueEntity;
    }

    // Getters y Setters
    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BloqueEntity getBloqueEntity() {
        return bloqueEntity;
    }

    public void setBloqueEntity(BloqueEntity bloqueEntity) {
        this.bloqueEntity = bloqueEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAula != null ? idAula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AulaEntity)) {
            return false;
        }
        AulaEntity other = (AulaEntity) object;
        if ((this.idAula == null && other.idAula != null) || (this.idAula != null && !this.idAula.equals(other.idAula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.aula.AulaEntity[ idAula=" + idAula + " ]";
    }
}
