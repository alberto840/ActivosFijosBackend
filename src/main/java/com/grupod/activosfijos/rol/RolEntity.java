package com.grupod.activosfijos.rol;

import java.io.Serializable;
import java.util.List;
import com.grupod.activosfijos.usuario.UsuarioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class RolEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private List<UsuarioEntity> usuarioList;

    // Constructor, getters y setters

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<UsuarioEntity> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<UsuarioEntity> usuarioList) {
        this.usuarioList = usuarioList;
    }
}
