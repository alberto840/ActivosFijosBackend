package com.grupod.activosfijos.sucursal;

import java.io.Serializable;
import java.util.List;

import com.grupod.activosfijos.bloque.BloqueEntity;
import com.grupod.activosfijos.municipio.MunicipioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "sucursales")
public class SucursalEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private Integer idSucursal;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id_municipio")
    private MunicipioEntity municipioEntity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalEntity")
    private List<BloqueEntity> bloqueEntityList;


    public SucursalEntity() {
    }

    public SucursalEntity(Integer idSucursal, String nombre, MunicipioEntity municipioEntity, List<BloqueEntity> bloqueEntityList) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.municipioEntity = municipioEntity;
        this.bloqueEntityList = bloqueEntityList;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MunicipioEntity getMunicipioEntity() {
        return municipioEntity;
    }

    public void setMunicipioEntity(MunicipioEntity municipioEntity) {
        this.municipioEntity = municipioEntity;
    }

    public List<BloqueEntity> getBloqueEntityList() {
        return bloqueEntityList;
    }

    public void setBloqueEntityList(List<BloqueEntity> bloqueEntityList) {
        this.bloqueEntityList = bloqueEntityList;
    }
}
