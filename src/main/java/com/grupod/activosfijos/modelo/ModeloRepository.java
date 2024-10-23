package com.grupod.activosfijos.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {
    // Se pueden agregar métodos adicionales personalizados si es necesario.
}
