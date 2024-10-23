package com.grupod.activosfijos.sucursal;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);
    private final SucursalService sucursalService;
    private final JwtConfig jwtConfig;

    @Autowired
    public SucursalController(SucursalService sucursalService, JwtConfig jwtConfig) {
        this.sucursalService = sucursalService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<SucursalDto>> crearSucursal(
            @RequestBody SucursalDto sucursalDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear sucursal");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear sucursal: {}", username);
        SucursalDto nuevaSucursal = sucursalService.crearSucursal(sucursalDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Sucursal creada exitosamente", nuevaSucursal));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<SucursalDto>>> obtenerTodasLasSucursales(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener sucursales");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener sucursales: {}", username);
        List<SucursalDto> sucursales = sucursalService.obtenerTodasLasSucursales();
        return ResponseEntity.ok(new ResponseDto<>(true, "Sucursales obtenidas exitosamente", sucursales));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<SucursalDto>> obtenerSucursalPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener sucursal");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener sucursal con ID: {}", id);
        SucursalDto sucursal = sucursalService.obtenerSucursalPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Sucursal obtenida exitosamente", sucursal));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<SucursalDto>> actualizarSucursal(
            @PathVariable Integer id,
            @RequestBody SucursalDto sucursalDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar sucursal");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar sucursal con ID: {}", id);
        SucursalDto sucursalActualizada = sucursalService.actualizarSucursal(id, sucursalDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Sucursal actualizada exitosamente", sucursalActualizada));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarSucursal(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar sucursal");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar sucursal con ID: {}", id);
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Sucursal eliminada exitosamente", null));
    }
}
