package com.grupod.activosfijos.area;

import com.grupod.activosfijos.empresa.EmpresaEntity;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "area")
public class AreaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer idArea;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id_empresa")
    private EmpresaEntity empresa;

    // Constructor vacío
    public AreaEntity() {}

    // Constructor completo
    public AreaEntity(Integer idArea, String nombre, EmpresaEntity empresa) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.empresa = empresa;
    }

    // Getters y Setters
    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }
}
