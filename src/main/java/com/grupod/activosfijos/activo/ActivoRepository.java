package com.grupod.activosfijos.activo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoRepository extends JpaRepository<ActivoEntity, Integer>{
    List<ActivoEntity> findByProyectoEntity_IdProyecto(Integer proyectoId);
}
