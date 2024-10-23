package com.grupod.activosfijos.divisa;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("api/v1/monedas")
public class DivisasController {

    private final DivisasService divisasService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(DivisasController.class);

    @Autowired
    public DivisasController(DivisasService divisasService, JwtConfig jwtConfig) {
        this.divisasService = divisasService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<DivisasDto>> crearDivisa(
            @RequestBody DivisasDto divisasDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear divisa");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear divisa: {}", username);
        return divisasService.crearDivisa(divisasDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<DivisasDto>>> obtenerTodasLasDivisas(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener todas las divisas");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener todas las divisas: {}", username);
        return divisasService.obtenerTodasLasDivisas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<DivisasDto>> obtenerDivisaPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener divisa por ID");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener divisa por ID: {}", username);
        return divisasService.obtenerDivisaPorId(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<DivisasDto>> actualizarDivisa(
            @PathVariable Integer id,
            @RequestBody DivisasDto divisasDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar divisa");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar divisa: {}", username);
        return divisasService.actualizarDivisa(id, divisasDto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarDivisa(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar divisa");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar divisa: {}", username);
        return divisasService.eliminarDivisa(id);
    }
}
