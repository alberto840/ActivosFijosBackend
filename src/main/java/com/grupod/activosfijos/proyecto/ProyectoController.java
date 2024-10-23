package com.grupod.activosfijos.proyecto;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proyecto")
public class ProyectoController {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoController.class);
    private final ProyectoService proyectoService;
    private final JwtConfig jwtConfig;

    @Autowired
    public ProyectoController(ProyectoService proyectoService, JwtConfig jwtConfig) {
        this.proyectoService = proyectoService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<ProyectoDto>> crearProyecto(
            @RequestBody ProyectoDto proyectoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear proyecto");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear proyecto: {}", username);
        ProyectoDto nuevoProyecto = proyectoService.crearProyecto(proyectoDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Proyecto creado exitosamente", nuevoProyecto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProyectoDto>>> obtenerTodosLosProyectos(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener proyectos");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener proyectos: {}", username);
        List<ProyectoDto> proyectos = proyectoService.obtenerTodosLosProyectos();
        return ResponseEntity.ok(new ResponseDto<>(true, "Proyectos obtenidos exitosamente", proyectos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProyectoDto>> obtenerProyectoPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener proyecto");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener proyecto con ID: {}", id);
        ProyectoDto proyecto = proyectoService.obtenerProyectoPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Proyecto obtenido exitosamente", proyecto));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<ProyectoDto>> actualizarProyecto(
            @PathVariable Integer id,
            @RequestBody ProyectoDto proyectoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar proyecto");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar proyecto con ID: {}", id);
        ProyectoDto proyectoActualizado = proyectoService.actualizarProyecto(id, proyectoDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Proyecto actualizado exitosamente", proyectoActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarProyecto(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar proyecto");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar proyecto con ID: {}", id);
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Proyecto eliminado exitosamente", null));
    }
}
