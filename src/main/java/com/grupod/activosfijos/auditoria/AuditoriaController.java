package com.grupod.activosfijos.auditoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaServices auditoriaService;

    @PostMapping("/crear")
    public ResponseEntity<AuditoriaEntity> crearRegistro(@RequestBody AuditoriaDto auditoriaDto) {
        AuditoriaEntity nuevoRegistro = auditoriaService.crearRegistro(auditoriaDto);
        return ResponseEntity.ok(nuevoRegistro);
    }

    @GetMapping("/por-fecha")
    public ResponseEntity<List<AuditoriaEntity>> obtenerPorFecha(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        List<AuditoriaEntity> registros = auditoriaService.obtenerPorFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/por-usuario/{idUsuario}")
    public ResponseEntity<List<AuditoriaEntity>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        List<AuditoriaEntity> registros = auditoriaService.obtenerPorUsuario(idUsuario);
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/por-accion")
    public ResponseEntity<List<AuditoriaEntity>> obtenerPorAccion(@RequestParam String accion) {
        List<AuditoriaEntity> registros = auditoriaService.obtenerPorAccion(accion);
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<AuditoriaEntity>> obtenerTodos() {
        List<AuditoriaEntity> registros = auditoriaService.obtenerTodos();
        return ResponseEntity.ok(registros);
    }

}
