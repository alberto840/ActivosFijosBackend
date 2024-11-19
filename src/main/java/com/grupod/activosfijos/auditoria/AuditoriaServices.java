package com.grupod.activosfijos.auditoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaServices {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public AuditoriaEntity crearRegistro(AuditoriaDto auditoriaDto) {
        AuditoriaEntity auditoria = new AuditoriaEntity();
        auditoria.setIdUsuario(auditoriaDto.getIdUsuario());
        auditoria.setAccion(auditoriaDto.getAccion());
        auditoria.setFecha(LocalDateTime.now());
        auditoria.setDetalles(auditoriaDto.getDetalles());
        return auditoriaRepository.save(auditoria);
    }

    public List<AuditoriaEntity> obtenerPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return auditoriaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    public List<AuditoriaEntity> obtenerPorUsuario(Long idUsuario) {
        return auditoriaRepository.findByIdUsuario(idUsuario);
    }

    public List<AuditoriaEntity> obtenerPorAccion(String accion) {
        return auditoriaRepository.findByAccion(accion);
    }

    public List<AuditoriaEntity> obtenerTodos() {
        return auditoriaRepository.findAll();
    }

}
