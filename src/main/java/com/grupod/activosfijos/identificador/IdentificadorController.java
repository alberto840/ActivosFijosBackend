package com.grupod.activosfijos.identificador;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/identificador")
public class IdentificadorController {

    private static final Logger logger = LoggerFactory.getLogger(IdentificadorController.class);
    private final IdentificadorService identificadorService;
    private final JwtConfig jwtConfig;

    @Autowired
    public IdentificadorController(IdentificadorService identificadorService, JwtConfig jwtConfig) {
        this.identificadorService = identificadorService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<IdentificadorDto>> crearIdentificador(
            @RequestBody IdentificadorDto identificadorDto,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear identificador: {}", username);
        IdentificadorDto nuevoIdentificador = identificadorService.crearIdentificador(identificadorDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Identificador creado exitosamente", nuevoIdentificador));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<IdentificadorDto>>> obtenerTodosLosIdentificadores(
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener identificadores: {}", username);
        List<IdentificadorDto> identificadores = identificadorService.obtenerTodosLosIdentificadores();
        return ResponseEntity.ok(new ResponseDto<>(true, "Identificadores obtenidos exitosamente", identificadores));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<IdentificadorDto>> obtenerIdentificadorPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener identificador con ID: {}", id);
        IdentificadorDto identificador = identificadorService.obtenerIdentificadorPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Identificador obtenido exitosamente", identificador));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<IdentificadorDto>> actualizarIdentificador(
            @PathVariable Integer id,
            @RequestBody IdentificadorDto identificadorDto,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar identificador con ID: {}", id);
        IdentificadorDto identificadorActualizado = identificadorService.actualizarIdentificador(id, identificadorDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Identificador actualizado exitosamente", identificadorActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarIdentificador(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar identificador con ID: {}", id);
        identificadorService.eliminarIdentificador(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Identificador eliminado exitosamente", null));
    }

    private String validarToken(String token) {
        String extractedToken = token.replace("Bearer ", "");
        return jwtConfig.extractUsername(extractedToken);
    }
}
