/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupod.activosfijos.provincia;

import java.io.Serializable;
import java.util.List;

import com.grupod.activosfijos.departamento.DepartamentoEntity;
import com.grupod.activosfijos.municipio.MunicipioEntity;
import jakarta.persistence.*;



@Entity
@Table(name = "provincia")
public class ProvinciaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_provincia")
    private Integer idProvincia;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinciaId")
    private List<MunicipioEntity> municipioEntityList;

    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private DepartamentoEntity departamentoId;

    public ProvinciaEntity() {
    }

    public ProvinciaEntity(Integer idProvincia, String nombre, List<MunicipioEntity> municipioEntityList, DepartamentoEntity departamentoId) {
        this.idProvincia = idProvincia;
        this.nombre = nombre;
        this.municipioEntityList = municipioEntityList;
        this.departamentoId = departamentoId;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MunicipioEntity> getMunicipioEntityList() {
        return municipioEntityList;
    }

    public void setMunicipioEntityList(List<MunicipioEntity> municipioEntityList) {
        this.municipioEntityList = municipioEntityList;
    }

    public DepartamentoEntity getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(DepartamentoEntity departamentoId) {
        this.departamentoId = departamentoId;
    }
}
