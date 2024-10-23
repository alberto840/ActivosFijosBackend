package com.grupod.activosfijos.departamento;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departamentos")
@CrossOrigin
public class DepartamentoController {

    private final DepartamentoService departamentoService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(DepartamentoController.class);

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService, JwtConfig jwtConfig) {
        this.departamentoService = departamentoService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<DepartamentoDto>> crearDepartamento(
            @RequestBody DepartamentoDto departamentoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear departamento");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está creando un departamento", username);
        DepartamentoDto createdDepartamento = departamentoService.crearDepartamento(departamentoDto);
        return ResponseEntity.status(201)
                .body(new ResponseDto<>(true, "Departamento creado exitosamente", createdDepartamento));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<DepartamentoDto>>> obtenerTodosLosDepartamentos(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener departamentos");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está obteniendo todos los departamentos", username);
        List<DepartamentoDto> departamentos = departamentoService.obtenerTodosLosDepartamentos();
        return ResponseEntity.ok(new ResponseDto<>(true, "Departamentos obtenidos exitosamente", departamentos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<DepartamentoDto>> obtenerDepartamentoPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener departamento con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está obteniendo departamento con ID: {}", username, id);
        try {
            DepartamentoDto departamento = departamentoService.obtenerDepartamentoPorId(id);
            return ResponseEntity.ok(new ResponseDto<>(true, "Departamento obtenido exitosamente", departamento));
        } catch (RuntimeException e) {
            logger.error("Error al obtener departamento con ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(404)
                    .body(new ResponseDto<>(false, "Departamento no encontrado", null));
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<DepartamentoDto>> actualizarDepartamento(
            @PathVariable Integer id,
            @RequestBody DepartamentoDto departamentoDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar departamento con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está actualizando departamento con ID: {}", username, id);
        try {
            DepartamentoDto updatedDepartamento = departamentoService.actualizarDepartamento(id, departamentoDto);
            return ResponseEntity.ok(new ResponseDto<>(true, "Departamento actualizado exitosamente", updatedDepartamento));
        } catch (RuntimeException e) {
            logger.error("Error al actualizar departamento con ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(404)
                    .body(new ResponseDto<>(false, "Departamento no encontrado", null));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarDepartamento(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar departamento con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado '{}' está eliminando departamento con ID: {}", username, id);
        try {
            departamentoService.eliminarDepartamento(id);
            return ResponseEntity.ok(new ResponseDto<>(true, "Departamento eliminado exitosamente", null));
        } catch (RuntimeException e) {
            logger.error("Error al eliminar departamento con ID: {}: {}", id, e.getMessage());
            return ResponseEntity.status(404)
                    .body(new ResponseDto<>(false, "Departamento no encontrado", null));
        }
    }
}
