package com.grupod.activosfijos.bloque;

import com.grupod.activosfijos.direccion.DireccionEntity;
import com.grupod.activosfijos.sucursal.SucursalEntity;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bloque")
public class BloqueEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bloque")
    private Integer idBloque;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    // Relación ManyToOne con DireccionEntity
    @ManyToOne(optional = false)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id_direccion")
    private DireccionEntity direccionEntity;

    // Relación ManyToOne con SucursalEntity
    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id_sucursal")
    private SucursalEntity sucursalEntity;

    // Constructor vacío
    public BloqueEntity() {}

    // Constructor completo
    public BloqueEntity(Integer idBloque, String nombre, DireccionEntity direccionEntity, SucursalEntity sucursalEntity) {
        this.idBloque = idBloque;
        this.nombre = nombre;
        this.direccionEntity = direccionEntity;
        this.sucursalEntity = sucursalEntity;
    }

    // Getters y Setters
    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DireccionEntity getDireccionEntity() {
        return direccionEntity;
    }

    public void setDireccionEntity(DireccionEntity direccionEntity) {
        this.direccionEntity = direccionEntity;
    }

    public SucursalEntity getSucursalEntity() {
        return sucursalEntity;
    }

    public void setSucursalEntity(SucursalEntity sucursalEntity) {
        this.sucursalEntity = sucursalEntity;
    }
}
