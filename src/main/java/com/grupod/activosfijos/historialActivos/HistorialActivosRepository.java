package com.grupod.activosfijos.historialActivos;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialActivosRepository extends JpaRepository<HistorialActivosEntity, Integer> {
    List<HistorialActivosEntity> findByActivoEntityIdActivo(Integer idActivo);
}
