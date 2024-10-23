package com.grupod.activosfijos.empresa;

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
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
    private final EmpresaService empresaService;
    private final JwtConfig jwtConfig;

    @Autowired
    public EmpresaController(EmpresaService empresaService, JwtConfig jwtConfig) {
        this.empresaService = empresaService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<EmpresaDto>> crearEmpresa(
            @RequestBody EmpresaDto empresaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para crear empresa");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para crear empresa: {}", username);
        EmpresaDto nuevaEmpresa = empresaService.crearEmpresa(empresaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Empresa creada exitosamente", nuevaEmpresa));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<EmpresaDto>>> obtenerTodasLasEmpresas(
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener empresas");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener empresas: {}", username);
        List<EmpresaDto> empresas = empresaService.obtenerTodasLasEmpresas();
        return ResponseEntity.ok(new ResponseDto<>(true, "Empresas obtenidas exitosamente", empresas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<EmpresaDto>> obtenerEmpresaPorId(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para obtener empresa");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para obtener empresa con ID: {}", id);
        EmpresaDto empresa = empresaService.obtenerEmpresaPorId(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Empresa obtenida exitosamente", empresa));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<EmpresaDto>> actualizarEmpresa(
            @PathVariable Integer id,
            @RequestBody EmpresaDto empresaDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar empresa");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar empresa con ID: {}", id);
        EmpresaDto empresaActualizada = empresaService.actualizarEmpresa(id, empresaDto);
        return ResponseEntity.ok(new ResponseDto<>(true, "Empresa actualizada exitosamente", empresaActualizada));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarEmpresa(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar empresa");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar empresa con ID: {}", id);
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Empresa eliminada exitosamente", null));
    }
}
