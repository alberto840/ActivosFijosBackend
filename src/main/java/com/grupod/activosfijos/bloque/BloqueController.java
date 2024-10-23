package com.grupod.activosfijos.bloque;

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
@RequestMapping("/api/v1/bloque")
public class BloqueController {

    private static final Logger logger = LoggerFactory.getLogger(BloqueController.class);
    private final BloqueService bloqueService;
    private final JwtConfig jwtConfig;

    @Autowired
    public BloqueController(BloqueService bloqueService, JwtConfig jwtConfig) {
        this.bloqueService = bloqueService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<BloqueDto>> crearBloque(
            @RequestBody BloqueDto bloqueDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear bloque");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear bloque: {}", username);
        BloqueDto nuevoBloque = bloqueService.crearBloque(bloqueDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Bloque creado exitosamente", nuevoBloque));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<BloqueDto>>> obtenerTodosLosBloques(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener bloques");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener bloques: {}", username);
        List<BloqueDto> bloques = bloqueService.obtenerTodosLosBloques();
        return ResponseEntity.ok(new ResponseDto<>(true, "Bloques obtenidos exitosamente", bloques));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<BloqueDto>> obtenerBloquePorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener bloque");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener bloque con ID: {}", id);
        BloqueDto bloque = bloqueService.obtenerBloquePorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Bloque obtenido exitosamente", bloque));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<BloqueDto>> actualizarBloque(
            @PathVariable Integer id,
            @RequestBody BloqueDto bloqueDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar bloque");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar bloque con ID: {}", id);
        BloqueDto bloqueActualizado = bloqueService.actualizarBloque(id, bloqueDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Bloque actualizado exitosamente", bloqueActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarBloque(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar bloque");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar bloque con ID: {}", id);
        bloqueService.eliminarBloque(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Bloque eliminado exitosamente", null));
    }
}
