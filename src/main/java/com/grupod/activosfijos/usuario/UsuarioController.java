package com.grupod.activosfijos.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.grupod.activosfijos.utils.ResponseDto;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDto<UsuarioDto>> crearUsuario(@RequestBody UsuarioDto usuarioDto) {
        logger.info("Solicitud de creación de usuario recibida: {}", usuarioDto.getCorreo());
        return usuarioService.crearUsuario(usuarioDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<String>> loginUsuario(@RequestBody UsuarioDto usuarioDto) {
        logger.info("Solicitud de login recibida para el correo: {}", usuarioDto.getCorreo());
        return usuarioService.loginUsuario(usuarioDto.getCorreo(), usuarioDto.getPassword());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDto<UsuarioDto>> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody UsuarioDto usuarioDto,
            @RequestHeader("Authorization") String token) {
        logger.info("Solicitud de actualización de usuario recibida para el ID: {}", id);

        String extractedToken = token.replace("Bearer ", "");
        logger.debug("Token extraído: {}", extractedToken);
        ResponseEntity<ResponseDto<UsuarioDto>> response = usuarioService.actualizarUsuario(id, usuarioDto, extractedToken);

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Usuario actualizado exitosamente: ID {}", id);
        } else {
            logger.warn("Error al actualizar usuario: {}", response.getBody().getMessage());
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UsuarioDto>> getUsuarioById(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {
        logger.info("Solicitud para obtener usuario con ID: {}", id);
        String extractedToken = token.replace("Bearer ", "");
        return usuarioService.getUsuarioById(id, extractedToken);
    }

    @GetMapping("/todos")
    public ResponseEntity<ResponseDto<List<UsuarioDto>>> getAllUsuarios(
            @RequestHeader("Authorization") String token) {
        logger.info("Solicitud para obtener todos los usuarios");
        String extractedToken = token.replace("Bearer ", "");
        return usuarioService.getAllUsuarios(extractedToken);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<String>> eliminarUsuario(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String token) {
        logger.info("Solicitud de eliminación de usuario recibida para el ID: {}", id);

        String extractedToken = token.replace("Bearer ", "");
        ResponseEntity<ResponseDto<String>> response = usuarioService.eliminarUsuario(id, extractedToken);

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Usuario eliminado exitosamente: ID {}", id);
        } else {
            logger.warn("Error al eliminar usuario: {}", response.getBody().getMessage());
        }

        return response;
    }


}