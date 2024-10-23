package com.grupod.activosfijos.activo;

import com.grupod.activosfijos.aula.AulaEntity;
import com.grupod.activosfijos.bloque.BloqueEntity;
import com.grupod.activosfijos.categoria.CategoriaEntity;
import com.grupod.activosfijos.custodio.CustodioEntity;
import com.grupod.activosfijos.depreciacion.DepreciacionEntity;
import com.grupod.activosfijos.estadoActivo.EstadoactivoEntity;
import com.grupod.activosfijos.modelo.ModeloEntity; // Importación de ModeloEntity
import com.grupod.activosfijos.proyecto.ProyectoEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "activo")
public class ActivoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo")
    private Integer idActivo;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "valor_actual")
    private BigDecimal valorActual;

    @Basic(optional = false)
    @Column(name = "valor_inicial")
    private BigDecimal valorInicial;

    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Basic(optional = false)
    @Column(name = "detalle")
    private String detalle;

    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;

    @Basic(optional = false)
    @Column(name = "precio")
    private BigDecimal precio;

    @Basic(optional = false)
    @Column(name = "comprobante_compra")
    private String comprobanteCompra;

    // Relación ManyToOne con otras entidades
    @ManyToOne
    @JoinColumn(name = "aula_id", referencedColumnName = "id_aula")
    private AulaEntity aulaEntity;

    @ManyToOne
    @JoinColumn(name = "bloque_id", referencedColumnName = "id_bloque")
    private BloqueEntity bloqueEntity;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id_categoria")
    private CategoriaEntity categoriaEntity;

    @ManyToOne
    @JoinColumn(name = "custodio_id", referencedColumnName = "id_custodio")
    private CustodioEntity custodioEntity;

    @ManyToOne
    @JoinColumn(name = "depreciacion_id", referencedColumnName = "id_depreciacion")
    private DepreciacionEntity depreciacionEntity;

    @ManyToOne
    @JoinColumn(name = "estadoactivo_id", referencedColumnName = "id_estado")
    private EstadoactivoEntity estadoactivoEntity;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id_proyecto")
    private ProyectoEntity proyectoEntity;

    @ManyToOne
    @JoinColumn(name = "modelo_id", referencedColumnName = "id_modelo") // Relación con ModeloEntity
    private ModeloEntity modeloEntity;

    // Getters y Setters
    public Integer getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getComprobanteCompra() {
        return comprobanteCompra;
    }

    public void setComprobanteCompra(String comprobanteCompra) {
        this.comprobanteCompra = comprobanteCompra;
    }

    public AulaEntity getAulaEntity() {
        return aulaEntity;
    }

    public void setAulaEntity(AulaEntity aulaEntity) {
        this.aulaEntity = aulaEntity;
    }

    public BloqueEntity getBloqueEntity() {
        return bloqueEntity;
    }

    public void setBloqueEntity(BloqueEntity bloqueEntity) {
        this.bloqueEntity = bloqueEntity;
    }

    public CategoriaEntity getCategoriaEntity() {
        return categoriaEntity;
    }

    public void setCategoriaEntity(CategoriaEntity categoriaEntity) {
        this.categoriaEntity = categoriaEntity;
    }

    public CustodioEntity getCustodioEntity() {
        return custodioEntity;
    }

    public void setCustodioEntity(CustodioEntity custodioEntity) {
        this.custodioEntity = custodioEntity;
    }

    public DepreciacionEntity getDepreciacionEntity() {
        return depreciacionEntity;
    }

    public void setDepreciacionEntity(DepreciacionEntity depreciacionEntity) {
        this.depreciacionEntity = depreciacionEntity;
    }

    public EstadoactivoEntity getEstadoactivoEntity() {
        return estadoactivoEntity;
    }

    public void setEstadoactivoEntity(EstadoactivoEntity estadoactivoEntity) {
        this.estadoactivoEntity = estadoactivoEntity;
    }

    public ProyectoEntity getProyectoEntity() {
        return proyectoEntity;
    }

    public void setProyectoEntity(ProyectoEntity proyectoEntity) {
        this.proyectoEntity = proyectoEntity;
    }

    public ModeloEntity getModeloEntity() {
        return modeloEntity;
    }

    public void setModeloEntity(ModeloEntity modeloEntity) {
        this.modeloEntity = modeloEntity;
    }
}
