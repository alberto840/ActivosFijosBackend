package com.grupod.activosfijos.modelo;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/modelo")
public class ModeloController {

    private final ModeloService modeloService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(ModeloController.class);

    @Autowired
    public ModeloController(ModeloService modeloService, JwtConfig jwtConfig) {
        this.modeloService = modeloService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<ModeloDto>> crearModelo(
            @RequestBody ModeloDto modeloDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear modelo");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear modelo: {}", username);
        return modeloService.crearModelo(modeloDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ModeloDto>>> obtenerTodosLosModelos(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener modelos");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener todos los modelos: {}", username);
        return modeloService.obtenerTodosLosModelos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ModeloDto>> obtenerModeloPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener modelo con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener modelo con ID: {}", id);
        return modeloService.obtenerModeloPorId(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<ModeloDto>> actualizarModelo(
            @PathVariable Integer id,
            @RequestBody ModeloDto modeloDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar modelo con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar modelo con ID: {}", id);
        return modeloService.actualizarModelo(id, modeloDto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarModelo(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar modelo con ID: {}", id);
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar modelo con ID: {}", id);
        return modeloService.eliminarModelo(id);
    }
}
