package com.grupod.activosfijos.proyecto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProyectoRepository extends JpaRepository<ProyectoEntity, Integer> {
    Optional<ProyectoEntity> findByCodigoProyecto(String codigoProyecto);
}
