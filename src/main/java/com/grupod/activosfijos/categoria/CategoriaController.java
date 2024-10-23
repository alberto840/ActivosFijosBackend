package com.grupod.activosfijos.categoria;

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
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);
    private final CategoriaService categoriaService;
    private final JwtConfig jwtConfig;

    @Autowired
    public CategoriaController(CategoriaService categoriaService, JwtConfig jwtConfig) {
        this.categoriaService = categoriaService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<CategoriaDto>> crearCategoria(@RequestBody CategoriaDto categoriaDto,
                                                                    @RequestHeader("Authorization") String token) {
        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado: {} - Creando categoría", username);
        CategoriaDto nuevaCategoria = categoriaService.crearCategoria(categoriaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Categoría creada exitosamente", nuevaCategoria));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CategoriaDto>>> obtenerTodasLasCategorias(@RequestHeader("Authorization") String token) {
        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado: {} - Obteniendo todas las categorías", username);
        List<CategoriaDto> categorias = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.ok(new ResponseDto<>(true, "Categorías obtenidas exitosamente", categorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<CategoriaDto>> obtenerCategoriaPorId(@PathVariable Integer id,
                                                                           @RequestHeader("Authorization") String token) {
        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado: {} - Obteniendo categoría con ID {}", username, id);
        CategoriaDto categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Categoría obtenida exitosamente", categoria));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<CategoriaDto>> actualizarCategoria(@PathVariable Integer id,
                                                                         @RequestBody CategoriaDto categoriaDto,
                                                                         @RequestHeader("Authorization") String token) {
        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado: {} - Actualizando categoría con ID {}", username, id);
        CategoriaDto categoriaActualizada = categoriaService.actualizarCategoria(id, categoriaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Categoría actualizada exitosamente", categoriaActualizada));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarCategoria(@PathVariable Integer id,
                                                               @RequestHeader("Authorization") String token) {
        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado: {} - Eliminando categoría con ID {}", username, id);
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Categoría eliminada exitosamente", null));
    }
}
