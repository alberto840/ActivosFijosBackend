package com.grupod.activosfijos.direccion;

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
@RequestMapping("/api/v1/direccion")
public class DireccionController {

    private static final Logger logger = LoggerFactory.getLogger(DireccionController.class);
    private final DireccionService direccionService;
    private final JwtConfig jwtConfig;

    @Autowired
    public DireccionController(DireccionService direccionService, JwtConfig jwtConfig) {
        this.direccionService = direccionService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<DireccionDto>> crearDireccion(
            @RequestBody DireccionDto direccionDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear dirección");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear dirección: {}", username);
        DireccionDto nuevaDireccion = direccionService.crearDireccion(direccionDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Dirección creada exitosamente", nuevaDireccion));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<DireccionDto>>> obtenerTodasLasDirecciones(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener direcciones");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener direcciones: {}", username);
        List<DireccionDto> direcciones = direccionService.obtenerTodasLasDirecciones();
        return ResponseEntity.ok(new ResponseDto<>(true, "Direcciones obtenidas exitosamente", direcciones));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<DireccionDto>> obtenerDireccionPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener dirección");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener dirección con ID: {}", id);
        DireccionDto direccion = direccionService.obtenerDireccionPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Dirección obtenida exitosamente", direccion));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<DireccionDto>> actualizarDireccion(
            @PathVariable Integer id,
            @RequestBody DireccionDto direccionDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar dirección");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar dirección con ID: {}", id);
        DireccionDto direccionActualizada = direccionService.actualizarDireccion(id, direccionDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Dirección actualizada exitosamente", direccionActualizada));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarDireccion(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar dirección");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar dirección con ID: {}", id);
        direccionService.eliminarDireccion(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Dirección eliminada exitosamente", null));
    }
}
