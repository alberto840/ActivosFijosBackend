package com.grupod.activosfijos.municipio;

import java.io.Serializable;

public class MunicipioDto implements Serializable {
    private Integer idMunicipio;
    private String nombre;
    private Integer provinciaId;

    public MunicipioDto() {
    }

    public MunicipioDto(Integer idMunicipio, String nombre, Integer provinciaId) {
        this.idMunicipio = idMunicipio;
        this.nombre = nombre;
        this.provinciaId = provinciaId;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Integer provinciaId) {
        this.provinciaId = provinciaId;
    }
}
