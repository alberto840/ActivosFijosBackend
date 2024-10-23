package com.grupod.activosfijos.historialActivos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.grupod.activosfijos.activo.ActivoEntity;
import com.grupod.activosfijos.aula.AulaEntity;
import com.grupod.activosfijos.custodio.CustodioEntity;
import com.grupod.activosfijos.proyecto.ProyectoEntity;
import com.grupod.activosfijos.usuario.UsuarioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "historialactivos")
public class HistorialActivosEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Basic(optional = false)
    @Column(name = "accion")
    private String accion;

    @Basic(optional = false)
    @Column(name = "valor_actual")
    private BigDecimal valorActual;

    @Basic(optional = false)
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @Basic(optional = false)
    @Column(name = "comprobante")
    private String comprobante;

    @Basic(optional = false)
    @Column(name = "detalle")
    private String detalle;

    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;

    @Basic(optional = false)
    @Column(name = "estado_uso")
    private String estadoUso;

    @ManyToOne
    @JoinColumn(name = "activo_id", referencedColumnName = "id_activo")
    private ActivoEntity activoEntity;

    @ManyToOne
    @JoinColumn(name = "aula_id", referencedColumnName = "id_aula")
    private AulaEntity aulaEntity;

    @ManyToOne
    @JoinColumn(name = "custodio_id", referencedColumnName = "id_custodio")
    private CustodioEntity custodioEntity;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id_proyecto")
    private ProyectoEntity proyectoEntity;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private UsuarioEntity usuarioEntity;

    // Getters y Setters

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEstadoUso() {
        return estadoUso;
    }

    public void setEstadoUso(String estadoUso) {
        this.estadoUso = estadoUso;
    }

    public ActivoEntity getActivoEntity() {
        return activoEntity;
    }

    public void setActivoEntity(ActivoEntity activoEntity) {
        this.activoEntity = activoEntity;
    }

    public AulaEntity getAulaEntity() {
        return aulaEntity;
    }

    public void setAulaEntity(AulaEntity aulaEntity) {
        this.aulaEntity = aulaEntity;
    }

    public CustodioEntity getCustodioEntity() {
        return custodioEntity;
    }

    public void setCustodioEntity(CustodioEntity custodioEntity) {
        this.custodioEntity = custodioEntity;
    }

    public ProyectoEntity getProyectoEntity() {
        return proyectoEntity;
    }

    public void setProyectoEntity(ProyectoEntity proyectoEntity) {
        this.proyectoEntity = proyectoEntity;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
}
