package com.grupod.activosfijos.identificador;

import com.grupod.activosfijos.activo.ActivoEntity; // Importación de la entidad ActivoEntity
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "identificador")
public class IdentificadorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_identificador")
    private Integer idIdentificador;

    @Basic(optional = false)
    @Column(name = "codigo_qr")
    private String codigoQr;

    @Basic(optional = false)
    @Column(name = "codigo_barra")
    private String codigoBarra;

    // Relación ManyToOne con ActivoEntity
    @ManyToOne
    @JoinColumn(name = "activo_id", referencedColumnName = "id_activo")
    private ActivoEntity activoEntity;

    // Constructor vacío
    public IdentificadorEntity() {}

    // Constructor completo
    public IdentificadorEntity(Integer idIdentificador, String codigoQr, String codigoBarra, ActivoEntity activoEntity) {
        this.idIdentificador = idIdentificador;
        this.codigoQr = codigoQr;
        this.codigoBarra = codigoBarra;
        this.activoEntity = activoEntity;
    }

    // Getters y Setters
    public Integer getIdIdentificador() {
        return idIdentificador;
    }

    public void setIdIdentificador(Integer idIdentificador) {
        this.idIdentificador = idIdentificador;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public ActivoEntity getActivoEntity() {
        return activoEntity;
    }

    public void setActivoEntity(ActivoEntity activoEntity) {
        this.activoEntity = activoEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdentificador != null ? idIdentificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IdentificadorEntity)) {
            return false;
        }
        IdentificadorEntity other = (IdentificadorEntity) object;
        return (this.idIdentificador != null || other.idIdentificador == null) && (this.idIdentificador == null || this.idIdentificador.equals(other.idIdentificador));
    }

    @Override
    public String toString() {
        return "IdentificadorEntity[ idIdentificador=" + idIdentificador + ", codigoQr=" + codigoQr + ", codigoBarra=" + codigoBarra + " ]";
    }
}
