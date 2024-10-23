package com.grupod.activosfijos.custodio;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/custodio")
public class CustodioController {

    private static final Logger logger = LoggerFactory.getLogger(CustodioController.class);
    private final CustodioService custodioService;
    private final JwtConfig jwtConfig;

    @Autowired
    public CustodioController(CustodioService custodioService, JwtConfig jwtConfig) {
        this.custodioService = custodioService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<CustodioDto>> crearCustodio(
            @RequestBody CustodioDto custodioDto,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear custodio: {}", username);
        CustodioDto nuevoCustodio = custodioService.crearCustodio(custodioDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Custodio creado exitosamente", nuevoCustodio));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CustodioDto>>> obtenerTodosLosCustodios(
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener custodios: {}", username);
        List<CustodioDto> custodios = custodioService.obtenerTodosLosCustodios();
        return ResponseEntity.ok(new ResponseDto<>(true, "Custodios obtenidos exitosamente", custodios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<CustodioDto>> obtenerCustodioPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener custodio con ID: {}", id);
        CustodioDto custodio = custodioService.obtenerCustodioPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Custodio obtenido exitosamente", custodio));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<CustodioDto>> actualizarCustodio(
            @PathVariable Integer id,
            @RequestBody CustodioDto custodioDto,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar custodio con ID: {}", id);
        CustodioDto custodioActualizado = custodioService.actualizarCustodio(id, custodioDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Custodio actualizado exitosamente", custodioActualizado));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarCustodio(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String username = validarToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar custodio con ID: {}", id);
        custodioService.eliminarCustodio(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Custodio eliminado exitosamente", null));
    }

    private String validarToken(String token) {
        String extractedToken = token.replace("Bearer ", "");
        return jwtConfig.extractUsername(extractedToken);
    }
}
