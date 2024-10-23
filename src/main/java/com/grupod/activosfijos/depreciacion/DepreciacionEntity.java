package com.grupod.activosfijos.depreciacion;

import java.io.Serializable;
import java.util.Date;

import com.grupod.activosfijos.divisa.DivisasEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "depreciacion")
public class DepreciacionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_depreciacion")
    private Integer idDepreciacion;

    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "metodo")
    private String metodo;

    @Basic(optional = false)
    @Column(name = "detalle")
    private String detalle;

    @JoinColumn(name = "divisas_id", referencedColumnName = "id_divisa")
    @ManyToOne(optional = false)
    private DivisasEntity divisasEntity;

    // Constructor vacío
    public DepreciacionEntity() {
    }

    // Constructor completo
    public DepreciacionEntity(Integer idDepreciacion, Date fecha, String metodo, String detalle) {
        this.idDepreciacion = idDepreciacion;
        this.fecha = fecha;
        this.metodo = metodo;
        this.detalle = detalle;
    }

    // Getters y Setters
    public Integer getIdDepreciacion() {
        return idDepreciacion;
    }

    public void setIdDepreciacion(Integer idDepreciacion) {
        this.idDepreciacion = idDepreciacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public DivisasEntity getDivisasEntity() {
        return divisasEntity;
    }

    public void setDivisasEntity(DivisasEntity divisasEntity) {
        this.divisasEntity = divisasEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepreciacion != null ? idDepreciacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DepreciacionEntity)) {
            return false;
        }
        DepreciacionEntity other = (DepreciacionEntity) object;
        if ((this.idDepreciacion == null && other.idDepreciacion != null) || (this.idDepreciacion != null && !this.idDepreciacion.equals(other.idDepreciacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.depreciacion.DepreciacionEntity[ idDepreciacion=" + idDepreciacion + " ]";
    }
}
