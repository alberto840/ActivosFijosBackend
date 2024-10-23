package com.grupod.activosfijos.provincia;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provincias")
@CrossOrigin
public class ProvinciaController {

    private final ProvinciaService provinciaService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);

    @Autowired
    public ProvinciaController(ProvinciaService provinciaService, JwtConfig jwtConfig) {
        this.provinciaService = provinciaService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<ProvinciaDto>> crearProvincia(
            @RequestBody ProvinciaDto provinciaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear provincia");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está creando una provincia", username);
        ProvinciaDto createdProvincia = provinciaService.crearProvincia(provinciaDto);
        return ResponseEntity.status(201)
                .body(new ResponseDto<>(true, "Provincia creada exitosamente", createdProvincia));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProvinciaDto>>> obtenerTodasLasProvincias(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener provincias");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está obteniendo todas las provincias", username);
        List<ProvinciaDto> provincias = provinciaService.obtenerTodasLasProvincias();
        return ResponseEntity.ok(new ResponseDto<>(true, "Provincias obtenidas exitosamente", provincias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProvinciaDto>> obtenerProvinciaPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener provincia con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está obteniendo provincia con ID: {}", username, id);
        try {
            ProvinciaDto provincia = provinciaService.obtenerProvinciaPorId(id);
            return ResponseEntity.ok(new ResponseDto<>(true, "Provincia obtenida exitosamente", provincia));
        } catch (RuntimeException e) {
            logger.error("Error al obtener provincia con ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(404)
                    .body(new ResponseDto<>(false, "Provincia no encontrada", null));
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<ProvinciaDto>> actualizarProvincia(
            @PathVariable Integer id,
            @RequestBody ProvinciaDto provinciaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar provincia con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está actualizando provincia con ID: {}", username, id);
        try {
            ProvinciaDto updatedProvincia = provinciaService.actualizarProvincia(id, provinciaDto);
            return ResponseEntity.ok(new ResponseDto<>(true, "Provincia actualizada exitosamente", updatedProvincia));
        } catch (RuntimeException e) {
            logger.error("Error al actualizar provincia con ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(404)
                    .body(new ResponseDto<>(false, "Provincia no encontrada", null));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarProvincia(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar provincia con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está eliminando provincia con ID: {}", username, id);
        try {
            provinciaService.eliminarProvincia(id);
            return ResponseEntity.ok(new ResponseDto<>(true, "Provincia eliminada exitosamente", null));
        } catch (RuntimeException e) {
            logger.error("Error al eliminar provincia con ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(404)
                    .body(new ResponseDto<>(false, "Provincia no encontrada", null));
        }
    }
}
