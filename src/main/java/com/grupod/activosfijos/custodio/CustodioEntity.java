package com.grupod.activosfijos.custodio;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "custodio")
public class CustodioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_custodio")
    private Integer idCustodio;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Basic(optional = false)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;

    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    @Basic(optional = false)
    @Column(name = "ci")
    private String ci;

    public CustodioEntity() {
    }

    public CustodioEntity(Integer idCustodio, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, String ci) {
        this.idCustodio = idCustodio;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.ci = ci;
    }

    public Integer getIdCustodio() {
        return idCustodio;
    }

    public void setIdCustodio(Integer idCustodio) {
        this.idCustodio = idCustodio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) { // Setter del nuevo atributo
        this.ci = ci;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCustodio != null ? idCustodio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CustodioEntity)) {
            return false;
        }
        CustodioEntity other = (CustodioEntity) object;
        if ((this.idCustodio == null && other.idCustodio != null) || (this.idCustodio != null && !this.idCustodio.equals(other.idCustodio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.custodio.CustodioEntity[ idCustodio=" + idCustodio + " ]";
    }
}
