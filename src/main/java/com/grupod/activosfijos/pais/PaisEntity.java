package com.grupod.activosfijos.pais;
import java.io.Serializable;
import java.util.List;

import com.grupod.activosfijos.departamento.DepartamentoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "pais")
public class PaisEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pais")
    private Integer idPais;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisEntity")  // Cambia 'paisIdPais' a 'paisEntity'
    private List<DepartamentoEntity> departamentoEntityList;

    public PaisEntity() {
    }

    public PaisEntity(Integer idPais) {
        this.idPais = idPais;
    }

    public PaisEntity(Integer idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DepartamentoEntity> getDepartamentoList() {
        return departamentoEntityList;
    }

    public void setDepartamentoList(List<DepartamentoEntity> departamentoEntityList) {
        this.departamentoEntityList = departamentoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaisEntity)) {
            return false;
        }
        PaisEntity other = (PaisEntity) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.pais.Pais[ idPais=" + idPais + " ]";
    }
    
}
