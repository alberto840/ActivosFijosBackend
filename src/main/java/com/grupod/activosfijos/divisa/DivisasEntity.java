package com.grupod.activosfijos.divisa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.grupod.activosfijos.depreciacion.DepreciacionEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "divisas")
public class DivisasEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_divisa")
    private Integer idDivisa;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;

    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "abreviacion")  // Nueva columna para la abreviación de la divisa
    private String abreviacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divisasEntity")
    private List<DepreciacionEntity> depreciacionEntityList;

    public DivisasEntity() {
    }

    public DivisasEntity(Integer idDivisa) {
        this.idDivisa = idDivisa;
    }

    public DivisasEntity(Integer idDivisa, String nombre, BigDecimal valor, Date fecha, String abreviacion) {
        this.idDivisa = idDivisa;
        this.nombre = nombre;
        this.valor = valor;
        this.fecha = fecha;
        this.abreviacion = abreviacion;  // Asignar valor del nuevo atributo
    }

    public Integer getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(Integer idDivisa) {
        this.idDivisa = idDivisa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAbreviacion() {  // Getter del nuevo atributo
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {  // Setter del nuevo atributo
        this.abreviacion = abreviacion;
    }

    public List<DepreciacionEntity> getDepreciacionEntityList() {
        return depreciacionEntityList;
    }

    public void setDepreciacionEntityList(List<DepreciacionEntity> depreciacionEntityList) {
        this.depreciacionEntityList = depreciacionEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDivisa != null ? idDivisa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DivisasEntity)) {
            return false;
        }
        DivisasEntity other = (DivisasEntity) object;
        if ((this.idDivisa == null && other.idDivisa != null) || (this.idDivisa != null && !this.idDivisa.equals(other.idDivisa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grupod.activosfijos.divisa.DivisasEntity[ idDivisa=" + idDivisa + " ]";
    }
}
