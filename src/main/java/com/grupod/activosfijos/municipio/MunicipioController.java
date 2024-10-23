package com.grupod.activosfijos.municipio;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/municipios")
public class MunicipioController {

    private final MunicipioService municipioService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(MunicipioController.class);

    @Autowired
    public MunicipioController(MunicipioService municipioService, JwtConfig jwtConfig) {
        this.municipioService = municipioService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<MunicipioDto>> crearMunicipio(
            @RequestBody MunicipioDto municipioDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear municipio");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear municipio: {}", username);
        MunicipioDto createdMunicipio = municipioService.crearMunicipio(municipioDto);
        return ResponseEntity.status(201)
                .body(new ResponseDto<>(true, "Municipio creado exitosamente", createdMunicipio));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<MunicipioDto>>> obtenerTodosLosMunicipios(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener municipios");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener municipios: {}", username);
        List<MunicipioDto> municipios = municipioService.obtenerTodosLosMunicipios();
        return ResponseEntity.ok(new ResponseDto<>(true, "Municipios obtenidos exitosamente", municipios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<MunicipioDto>> obtenerMunicipioPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener municipio");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener municipio con ID: {}", id);
        MunicipioDto municipio = municipioService.obtenerMunicipioPorId(id);
        return municipio != null ?
                ResponseEntity.ok(new ResponseDto<>(true, "Municipio obtenido exitosamente", municipio)) :
                ResponseEntity.status(404).body(new ResponseDto<>(false, "Municipio no encontrado", null));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<MunicipioDto>> actualizarMunicipio(
            @PathVariable Integer id,
            @RequestBody MunicipioDto municipioDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar municipio");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar municipio con ID: {}", id);
        MunicipioDto updatedMunicipio = municipioService.actualizarMunicipio(id, municipioDto);
        return updatedMunicipio != null ?
                ResponseEntity.ok(new ResponseDto<>(true, "Municipio actualizado exitosamente", updatedMunicipio)) :
                ResponseEntity.status(404).body(new ResponseDto<>(false, "Municipio no encontrado", null));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarMunicipio(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar municipio");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar municipio con ID: {}", id);
        municipioService.eliminarMunicipio(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Municipio eliminado exitosamente", null));
    }
}
