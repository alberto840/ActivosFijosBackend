package com.grupod.activosfijos.proyecto;

import com.grupod.activosfijos.area.AreaEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "proyecto")
public class ProyectoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo_proyecto") // Nuevo campo
    private String codigoProyecto;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "area_id", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private AreaEntity areaEntityId;

    public ProyectoEntity() {
    }

    public ProyectoEntity(Integer idProyecto, String nombre, String codigoProyecto, Date fechaInicio, Date fechaFin, AreaEntity areaEntityId) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.codigoProyecto = codigoProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.areaEntityId = areaEntityId;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public AreaEntity getAreaEntityId() {
        return areaEntityId;
    }

    public void setAreaEntityId(AreaEntity areaEntityId) {
        this.areaEntityId = areaEntityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProyectoEntity)) {
            return false;
        }
        ProyectoEntity other = (ProyectoEntity) object;
        return (this.idProyecto != null || other.idProyecto == null) && (this.idProyecto == null || this.idProyecto.equals(other.idProyecto));
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.proyecto.ProyectoEntity[ idProyecto=" + idProyecto + " ]";
    }
}
