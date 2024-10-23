package com.grupod.activosfijos.depreciacion;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/depreciacion")
public class DepreciacionController {

    private static final Logger logger = LoggerFactory.getLogger(DepreciacionController.class);
    private final DepreciacionService depreciacionService;
    private final JwtConfig jwtConfig;

    @Autowired
    public DepreciacionController(DepreciacionService depreciacionService, JwtConfig jwtConfig) {
        this.depreciacionService = depreciacionService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<DepreciacionDto>> crearDepreciacion(
            @RequestBody DepreciacionDto depreciacionDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear depreciación");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear depreciación: {}", username);
        DepreciacionDto nuevaDepreciacion = depreciacionService.crearDepreciacion(depreciacionDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Depreciación creada exitosamente", nuevaDepreciacion));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<DepreciacionDto>>> obtenerTodasLasDepreciaciones(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener depreciaciones");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener depreciaciones: {}", username);
        List<DepreciacionDto> depreciaciones = depreciacionService.obtenerTodasLasDepreciaciones();
        return ResponseEntity.ok(new ResponseDto<>(true, "Depreciaciones obtenidas exitosamente", depreciaciones));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<DepreciacionDto>> obtenerDepreciacionPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener depreciación");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener depreciación con ID: {}", id);
        DepreciacionDto depreciacion = depreciacionService.obtenerDepreciacionPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Depreciación obtenida exitosamente", depreciacion));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<DepreciacionDto>> actualizarDepreciacion(
            @PathVariable Integer id,
            @RequestBody DepreciacionDto depreciacionDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar depreciación");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar depreciación con ID: {}", id);
        DepreciacionDto depreciacionActualizada = depreciacionService.actualizarDepreciacion(id, depreciacionDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Depreciación actualizada exitosamente", depreciacionActualizada));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarDepreciacion(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar depreciación");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar depreciación con ID: {}", id);
        depreciacionService.eliminarDepreciacion(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Depreciación eliminada exitosamente", null));
    }
}
