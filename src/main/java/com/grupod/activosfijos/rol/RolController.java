package com.grupod.activosfijos.rol;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.utils.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolService rolService;
    private final JwtConfig jwtConfig;
    private static final Logger logger = LoggerFactory.getLogger(RolController.class);

    @Autowired
    public RolController(RolService rolService, JwtConfig jwtConfig) {
        this.rolService = rolService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<RolDto>> crearRol(
            @RequestBody RolDto rolDto) {

        logger.info("Creando un nuevo rol");

        RolDto createdRol = rolService.crearRol(rolDto).getBody();
        return ResponseEntity.status(201)
                .body(new ResponseDto<>(true, "Rol creado exitosamente", createdRol));
    }


    @GetMapping
    public ResponseEntity<ResponseDto<List<RolDto>>> obtenerTodosLosRoles() {
        List<RolDto> roles = rolService.obtenerTodosLosRoles().getBody();
        return ResponseEntity.ok(new ResponseDto<>(true, "Roles obtenidos exitosamente", roles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RolDto>> obtenerRolPorId(@PathVariable Integer id) {
        RolDto rol = rolService.obtenerRolPorId(id).getBody();
        return rol != null ?
                ResponseEntity.ok(new ResponseDto<>(true, "Rol obtenido exitosamente", rol)) :
                ResponseEntity.status(404).body(new ResponseDto<>(false, "Rol no encontrado", null));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<RolDto>> actualizarRol(
            @PathVariable Integer id,
            @RequestBody RolDto rolDto,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para actualizar rol");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para actualizar rol: {}", username);
        RolDto updatedRol = rolService.actualizarRol(id, rolDto).getBody();
        return updatedRol != null ?
                ResponseEntity.ok(new ResponseDto<>(true, "Rol actualizado exitosamente", updatedRol)) :
                ResponseEntity.status(404).body(new ResponseDto<>(false, "Rol no encontrado", null));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Void>> eliminarRol(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {

        String extractedToken = token.replace("Bearer ", "");
        String username = jwtConfig.extractUsername(extractedToken);

        if (username == null || !jwtConfig.validateToken(extractedToken, username)) {
            logger.warn("Token inválido o usuario no autorizado para eliminar rol");
            return ResponseEntity.status(401)
                    .body(new ResponseDto<>(false, "Token inválido o usuario no autorizado", null));
        }

        logger.info("Usuario autorizado para eliminar rol: {}", username);
        rolService.eliminarRol(id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Rol eliminado exitosamente", null));
    }
}
