package com.grupod.activosfijos.area;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/area")
public class AreaController {

    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
    private final AreaService areaService;
    private final JwtConfig jwtConfig;

    @Autowired
    public AreaController(AreaService areaService, JwtConfig jwtConfig) {
        this.areaService = areaService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<AreaDto>> crearArea(
            @RequestBody AreaDto areaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear área");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear área: {}", username);
        AreaDto nuevaArea = areaService.crearArea(areaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Área creada exitosamente", nuevaArea));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<AreaDto>>> obtenerTodasLasAreas(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener áreas");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener áreas: {}", username);
        List<AreaDto> areas = areaService.obtenerTodasLasAreas();
        return ResponseEntity.ok(new ResponseDto<>(true, "Áreas obtenidas exitosamente", areas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<AreaDto>> obtenerAreaPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener área");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener área con ID: {}", id);
        AreaDto area = areaService.obtenerAreaPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Área obtenida exitosamente", area));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<AreaDto>> actualizarArea(
            @PathVariable Integer id,
            @RequestBody AreaDto areaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar área");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar área con ID: {}", id);
        AreaDto areaActualizada = areaService.actualizarArea(id, areaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Área actualizada exitosamente", areaActualizada));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarArea(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar área");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar área con ID: {}", id);
        areaService.eliminarArea(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Área eliminada exitosamente", null));
    }
}
