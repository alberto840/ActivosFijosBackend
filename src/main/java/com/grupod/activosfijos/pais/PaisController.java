package com.grupod.activosfijos.pais;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pais")
public class PaisController {

    private final PaisService paisService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(PaisController.class);

    @Autowired
    public PaisController(PaisService paisService, JwtConfig jwtConfig) {
        this.paisService = paisService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<PaisDto>> crearPais(
            @RequestBody PaisDto paisDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear país");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear país: {}", username);
        PaisDto createdPais = paisService.crearPais(paisDto);
        return ResponseEntity.status(201)
                .body(new ResponseDto<>(true, "País creado exitosamente", createdPais));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<PaisDto>>> obtenerTodosLosPaises(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener países");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener países: {}", username);
        List<PaisDto> paises = paisService.obtenerTodosLosPaises();
        return ResponseEntity.ok(new ResponseDto<>(true, "Países obtenidos exitosamente", paises));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PaisDto>> obtenerPaisPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener país");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener país con ID: {}", id);
        PaisDto pais = paisService.obtenerPaisPorId(id);
        return pais != null ?
                ResponseEntity.ok(new ResponseDto<>(true, "País obtenido exitosamente", pais)) :
                ResponseEntity.status(404).body(new ResponseDto<>(false, "País no encontrado", null));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<PaisDto>> actualizarPais(
            @PathVariable Integer id,
            @RequestBody PaisDto paisDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar país");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar país con ID: {}", id);
        PaisDto updatedPais = paisService.actualizarPais(id, paisDto);
        return updatedPais != null ?
                ResponseEntity.ok(new ResponseDto<>(true, "País actualizado exitosamente", updatedPais)) :
                ResponseEntity.status(404).body(new ResponseDto<>(false, "País no encontrado", null));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarPais(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar país");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar país con ID: {}", id);
        paisService.eliminarPais(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "País eliminado exitosamente", null));
    }
}
