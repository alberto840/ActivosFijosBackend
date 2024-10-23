package com.grupod.activosfijos.marca;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/marca")
public class MarcaController {

    private final MarcaService marcaService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    public MarcaController(MarcaService marcaService, JwtConfig jwtConfig) {
        this.marcaService = marcaService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<MarcaDto>> crearMarca(
            @RequestBody MarcaDto marcaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear marca");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear marca: {}", username);
        return marcaService.crearMarca(marcaDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<MarcaDto>>> obtenerTodasLasMarcas(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener marcas");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener todas las marcas: {}", username);
        return marcaService.obtenerTodasLasMarcas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<MarcaDto>> obtenerMarcaPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener marca con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener marca con ID: {}", id);
        return marcaService.obtenerMarcaPorId(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<MarcaDto>> actualizarMarca(
            @PathVariable Integer id,
            @RequestBody MarcaDto marcaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar marca con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar marca con ID: {}", id);
        return marcaService.actualizarMarca(id, marcaDto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarMarca(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar marca con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar marca con ID: {}", id);
        return marcaService.eliminarMarca(id);
    }
}
