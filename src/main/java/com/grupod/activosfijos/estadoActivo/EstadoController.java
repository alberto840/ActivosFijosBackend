package com.grupod.activosfijos.estadoActivo;

import com.grupod.activosfijos.utils.ResponseDto;
import com.grupod.activosfijos.config.JwtConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/estadoActivo")
public class EstadoController {

    private final Logger logger = LoggerFactory.getLogger(EstadoController.class);
    private final EstadoService estadoService;
    private final JwtConfig jwtConfig;

    @Autowired
    public EstadoController(EstadoService estadoService, JwtConfig jwtConfig) {
        this.estadoService = estadoService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<EstadoactivoDto>> crearEstado(
            @RequestBody EstadoactivoDto estadoactivoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear estado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear estado: {}", username);
        EstadoactivoDto nuevoEstado = estadoService.crearEstado(estadoactivoDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Estado creado exitosamente", nuevoEstado));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<EstadoactivoDto>>> obtenerTodosLosEstados(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener estados");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener todos los estados: {}", username);
        List<EstadoactivoDto> estados = estadoService.obtenerTodosLosEstados();
        return ResponseEntity.ok(new ResponseDto<>(true, "Estados obtenidos exitosamente", estados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<EstadoactivoDto>> obtenerEstadoPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener estado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener estado con ID: {}", id);
        EstadoactivoDto estado = estadoService.obtenerEstadoPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Estado obtenido exitosamente", estado));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<EstadoactivoDto>> actualizarEstado(
            @PathVariable Integer id,
            @RequestBody EstadoactivoDto estadoactivoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar estado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar estado con ID: {}", id);
        EstadoactivoDto estadoActualizado = estadoService.actualizarEstado(id, estadoactivoDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Estado actualizado exitosamente", estadoActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarEstado(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar estado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar estado con ID: {}", id);
        estadoService.eliminarEstado(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Estado eliminado exitosamente", null));
    }
}
