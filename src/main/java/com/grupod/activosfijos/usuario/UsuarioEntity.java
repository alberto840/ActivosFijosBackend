package com.grupod.activosfijos.usuario;

import com.grupod.activosfijos.historialActivos.HistorialActivosEntity;
import com.grupod.activosfijos.rol.RolEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellido_paterno", nullable = true)
    private String apellidoPaterno;

    @Basic(optional = false)
    @Column(name = "apellido_materno", nullable = true)
    private String apellidoMaterno;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;

    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;

    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioEntity")
    private List<HistorialActivosEntity> historialActivosEntityList;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id_rol", nullable = false)
    private RolEntity rolId;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioEntity(Integer idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String password, String correo, boolean estado, String telefono, RolEntity rolId) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.password = password;
        this.correo = correo;
        this.estado = estado;
        this.telefono = telefono;
        this.rolId = rolId;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<HistorialActivosEntity> getHistorialActivosEntityList() {
        return historialActivosEntityList;
    }

    public void setHistorialActivosEntityList(List<HistorialActivosEntity> historialActivosEntityList) {
        this.historialActivosEntityList = historialActivosEntityList;
    }

    public RolEntity getRolId() {
        return rolId;
    }

    public void setRolId(RolEntity rolId) {
        this.rolId = rolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.usuario.UsuarioEntity[ idUsuario=" + idUsuario + " ]";
    }
}
