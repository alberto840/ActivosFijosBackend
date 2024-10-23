package com.grupod.activosfijos.historialActivos;

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
@RequestMapping(path = "api/v1/historialActivos")
public class HistorialActivosController {

    private static final Logger logger = LoggerFactory.getLogger(HistorialActivosController.class);
    private final HistorialACtivosService historialService;
    private final JwtConfig jwtConfig;

    @Autowired
    public HistorialActivosController(HistorialACtivosService historialService, JwtConfig jwtConfig) {
        this.historialService = historialService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<HistorialActivosDto>> crearHistorial(
            @RequestBody HistorialActivosDto historialDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear historial");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear historial: {}", username);
        HistorialActivosDto nuevoHistorial = historialService.crearHistorial(historialDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Historial creado exitosamente", nuevoHistorial));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<HistorialActivosDto>>> obtenerTodosLosHistoriales(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener historiales");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener historiales: {}", username);
        List<HistorialActivosDto> historiales = historialService.obtenerTodosLosHistoriales();
        return ResponseEntity.ok(new ResponseDto<>(true, "Historiales obtenidos exitosamente", historiales));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<HistorialActivosDto>> obtenerHistorialPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener historial");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener historial con ID: {}", id);
        HistorialActivosDto historial = historialService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Historial obtenido exitosamente", historial));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<HistorialActivosDto>> actualizarHistorial(
            @PathVariable Integer id,
            @RequestBody HistorialActivosDto historialDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar historial");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar historial con ID: {}", id);
        HistorialActivosDto historialActualizado = historialService.actualizarHistorial(id, historialDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Historial actualizado exitosamente", historialActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarHistorial(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar historial");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar historial con ID: {}", id);
        historialService.eliminarHistorial(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Historial eliminado exitosamente", null));
    }
}
