/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupod.activosfijos.departamento;

import java.io.Serializable;
import java.util.List;


import com.grupod.activosfijos.pais.PaisEntity;

import com.grupod.activosfijos.provincia.ProvinciaEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "departamento")
public class DepartamentoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_departamento")
    private Integer idDepartamento;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentoId")  // Cambié departamentoEntityId a departamentoId para que coincida con el nombre de la propiedad en ProvinciaEntity
    private List<ProvinciaEntity> provinciaList;

    @ManyToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "id_pais")
    private PaisEntity paisEntity;

    public DepartamentoEntity() {
    }

    public DepartamentoEntity(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public DepartamentoEntity(Integer idDepartamento, String nombre, List<ProvinciaEntity> provinciaList, PaisEntity paisEntity) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.provinciaList = provinciaList;
        this.paisEntity = paisEntity;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProvinciaEntity> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<ProvinciaEntity> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public PaisEntity getPaisEntity() {
        return paisEntity;
    }

    public void setPaisEntity(PaisEntity paisEntity) {
        this.paisEntity = paisEntity;
    }
}
