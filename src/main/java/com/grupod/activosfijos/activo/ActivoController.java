package com.grupod.activosfijos.activo;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/activo")
public class ActivoController {

    private static final Logger logger = LoggerFactory.getLogger(ActivoController.class);
    private final ActivoService activoService;
    private final JwtConfig jwtConfig;

    @Autowired
    public ActivoController(ActivoService activoService, JwtConfig jwtConfig) {
        this.activoService = activoService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<ActivoDto>> crearActivo(
            @RequestBody ActivoDto activoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear activo");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear activo: {}", username);
        ActivoDto nuevoActivo = activoService.crearActivo(activoDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Activo creado exitosamente", nuevoActivo));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ActivoDto>>> obtenerTodosLosActivos(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener activos");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener activos: {}", username);
        List<ActivoDto> activos = activoService.obtenerTodosLosActivos();
        return ResponseEntity.ok(new ResponseDto<>(true, "Activos obtenidos exitosamente", activos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ActivoDto>> obtenerActivoPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener activo");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener activo con ID: {}", id);
        ActivoDto activo = activoService.obtenerActivoPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Activo obtenido exitosamente", activo));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<ActivoDto>> actualizarActivo(
            @PathVariable Integer id,
            @RequestBody ActivoDto activoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar activo");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar activo con ID: {}", id);
        ActivoDto activoActualizado = activoService.actualizarActivo(id, activoDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Activo actualizado exitosamente", activoActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarActivo(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar activo");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar activo con ID: {}", id);
        activoService.eliminarActivo(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Activo eliminado exitosamente", null));
    }

    @GetMapping("/{id}/ubicacion")
    public ResponseEntity<ResponseDto<UbicacionDto>> obtenerUbicacionCompletaPorIdActivo(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener la ubicación del activo");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener la ubicación del activo con ID: {}", id);

        // Obtener la ubicación completa del activo
        UbicacionDto ubicacion = activoService.obtenerUbicacionCompletaPorIdActivo(id);

        if (ubicacion == null) {
            logger.warn("No se encontró la ubicación del activo con ID: {}", id);
            return ResponseEntity.status(404)
                    .body(new ResponseDto<>(false, "Ubicación del activo no encontrada", null));
        }

        return ResponseEntity.ok(new ResponseDto<>(true, "Ubicación del activo obtenida exitosamente", ubicacion));
    }

    @GetMapping("/ubicaciones")
    public ResponseEntity<ResponseDto<List<UbicacionDto>>> obtenerUbicacionesDeTodosLosActivos() {
        List<UbicacionDto> ubicaciones = activoService.obtenerUbicacionesDeTodosLosActivos();
        return ResponseEntity.ok(new ResponseDto<>(true, "Ubicaciones obtenidas exitosamente", ubicaciones));
    }

}
