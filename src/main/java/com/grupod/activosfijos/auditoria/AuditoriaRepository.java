package com.grupod.activosfijos.auditoria;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaRepository extends JpaRepository<AuditoriaEntity, Long> {

    List<AuditoriaEntity> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<AuditoriaEntity> findByIdUsuario(Long idUsuario);

    List<AuditoriaEntity> findByAccion(String accion);
}
