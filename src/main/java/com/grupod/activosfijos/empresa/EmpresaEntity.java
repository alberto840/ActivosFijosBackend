package com.grupod.activosfijos.empresa;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "empresa")
public class EmpresaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;

    @Basic(optional = false)
    @Column(name = "nit")
    private String nit;

    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    // Constructor vacío
    public EmpresaEntity() {}

    // Constructor completo
    public EmpresaEntity(Integer idEmpresa, String nombre, String direccion, String nit, String telefono) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "EmpresaEntity[ idEmpresa=" + idEmpresa + " ]";
    }
}
