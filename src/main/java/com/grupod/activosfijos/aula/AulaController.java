package com.grupod.activosfijos.aula;

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
@RequestMapping(path = "api/v1/aula")
public class AulaController {
    private static final Logger logger = LoggerFactory.getLogger(AulaController.class);
    private final AulaService aulaService;
    private final JwtConfig jwtConfig;

    @Autowired
    public AulaController(AulaService aulaService, JwtConfig jwtConfig) {
        this.aulaService = aulaService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<AulaDto>> crearAula(
            @RequestBody AulaDto aulaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear aula");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear aula: {}", username);
        AulaDto nuevaAula = aulaService.crearAula(aulaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Aula creada exitosamente", nuevaAula));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<AulaDto>>> obtenerTodasLasAulas(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener aulas");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener aulas: {}", username);
        List<AulaDto> aulas = aulaService.obtenerTodasLasAulas();
        return ResponseEntity.ok(new ResponseDto<>(true, "Aulas obtenidas exitosamente", aulas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<AulaDto>> obtenerAulaPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener aula");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener aula con ID: {}", id);
        AulaDto aula = aulaService.obtenerAulaPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Aula obtenida exitosamente", aula));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<AulaDto>> actualizarAula(
            @PathVariable Integer id,
            @RequestBody AulaDto aulaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar aula");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar aula con ID: {}", id);
        AulaDto aulaActualizada = aulaService.actualizarAula(id, aulaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Aula actualizada exitosamente", aulaActualizada));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarAula(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar aula");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar aula con ID: {}", id);
        aulaService.eliminarAula(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Aula eliminada exitosamente", null));
    }
}
