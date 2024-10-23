package com.grupod.activosfijos.usuario;

import com.grupod.activosfijos.config.JwtConfig;
import com.grupod.activosfijos.rol.RolEntity;
import com.grupod.activosfijos.rol.RolRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.grupod.activosfijos.utils.ResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final JwtConfig jwtConfig;
    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, JwtConfig jwtConfig) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.jwtConfig = jwtConfig;
    }

    public ResponseEntity<ResponseDto<UsuarioDto>> crearUsuario(UsuarioDto usuarioDto) {
        // Verificar si el correo ya está registrado en la base de datos
        if (usuarioRepository.existsByCorreo(usuarioDto.getCorreo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDto<>(false, "El correo ya está registrado", null)
            );
        }

        // Encriptar la contraseña
        String encodedPassword = BCrypt.hashpw(usuarioDto.getPassword(), BCrypt.gensalt());

        // Buscar el rol asociado al usuario
        RolEntity rolEntity = rolRepository.findById(usuarioDto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Crear la entidad Usuario
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellidoPaterno(usuarioDto.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioDto.getApellidoMaterno());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setPassword(encodedPassword);
        usuario.setEstado(usuarioDto.isEstado());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setRolId(rolEntity);

        // Guardar el usuario en la base de datos
        usuario = usuarioRepository.save(usuario);

        // Crear un DTO de respuesta con los datos del usuario guardado
        UsuarioDto responseDto = new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getCorreo(),
                usuario.getEstado(),
                usuario.getTelefono(),
                usuario.getRolId().getIdRol()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "Usuario creado con éxito", responseDto)
        );
    }

    public ResponseEntity<ResponseDto<String>> loginUsuario(String correo, String password) {
        // Buscar el usuario por su correo
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isEmpty()) {
            logger.warn("Intento de login fallido: correo no encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseDto<>(false, "Credenciales incorrectas", null)
            );
        }
        UsuarioEntity usuario = usuarioOpt.get();
        // Verificar la contraseña
        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            logger.warn("Intento de login fallido: contraseña incorrecta para el correo {}", correo);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseDto<>(false, "Credenciales incorrectas", null)
            );
        }

        // Generar el token JWT para el usuario autenticado
        String token = jwtConfig.generateToken(usuario.getCorreo(), usuario.getIdUsuario(), usuario.getRolId().getIdRol());
        logger.info("Usuario {} ha iniciado sesión exitosamente", correo);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(true, "Login exitoso", token)
        );
    }

    public ResponseEntity<ResponseDto<UsuarioDto>> actualizarUsuario(Integer id, UsuarioDto usuarioDto, String token) {
        // Validar el token y el usuario asociado
        String usernameFromToken = jwtConfig.extractUsername(token);
        logger.debug("Correo extraído del token: {}", usernameFromToken);
        logger.debug("Correo en el DTO: {}", usuarioDto.getCorreo());

        if (usernameFromToken == null || !usernameFromToken.equals(usuarioDto.getCorreo())) {
            logger.warn("Token inválido o usuario no autorizado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseDto<>(false, "Token inválido o usuario no autorizado", null)
            );
        }
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            logger.warn("Usuario con ID {} no encontrado", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDto<>(false, "Usuario no encontrado", null)
            );
        }

        // Actualizar la entidad Usuario con los nuevos datos
        UsuarioEntity usuario = usuarioOpt.get();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellidoPaterno(usuarioDto.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioDto.getApellidoMaterno());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setEstado(usuarioDto.isEstado());

        // Si el rol es diferente, actualizarlo
        if (usuarioDto.getRolId() != null) {
            RolEntity rolEntity = rolRepository.findById(usuarioDto.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRolId(rolEntity);
        }

        // Guardar el usuario actualizado
        usuario = usuarioRepository.save(usuario);

        // Crear un DTO de respuesta con los datos del usuario actualizado
        UsuarioDto responseDto = new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getCorreo(),
                usuario.getEstado(),
                usuario.getTelefono(),
                usuario.getRolId().getIdRol()
        );
        logger.info("Usuario con ID {} actualizado exitosamente", id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(true, "Usuario actualizado con éxito", responseDto)
        );
    }

    public ResponseEntity<ResponseDto<UsuarioDto>> getUsuarioById(Integer id, String token) {
        // Validar el token
        String usernameFromToken = jwtConfig.extractUsername(token);
        logger.debug("Correo extraído del token: {}", usernameFromToken);

        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            logger.warn("Usuario con ID {} no encontrado", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDto<>(false, "Usuario no encontrado", null)
            );
        }

        // Crear un DTO con los datos del usuario encontrado
        UsuarioEntity usuario = usuarioOpt.get();
        UsuarioDto responseDto = new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getCorreo(),
                usuario.getEstado(),
                usuario.getTelefono(),
                usuario.getRolId().getIdRol()
        );

        logger.info("Usuario con ID {} encontrado", id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(true, "Usuario encontrado con éxito", responseDto)
        );
    }

    public ResponseEntity<ResponseDto<List<UsuarioDto>>> getAllUsuarios(String token) {
        // Validar el token
        String usernameFromToken = jwtConfig.extractUsername(token);
        logger.debug("Correo extraído del token: {}", usernameFromToken);

        // Obtener la lista de usuarios
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuariosDto = usuarios.stream().map(usuario ->
                new UsuarioDto(
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getApellidoPaterno(),
                        usuario.getApellidoMaterno(),
                        usuario.getCorreo(),
                        usuario.getEstado(),
                        usuario.getTelefono(),
                        usuario.getRolId().getIdRol()
                )
        ).collect(Collectors.toList());

        logger.info("Todos los usuarios obtenidos");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(true, "Usuarios obtenidos con éxito", usuariosDto)
        );
    }

    public ResponseEntity<ResponseDto<String>> eliminarUsuario(Integer id, String token) {
        // Validar el token
        String usernameFromToken = jwtConfig.extractUsername(token);
        logger.debug("Correo extraído del token: {}", usernameFromToken);

        // Buscar el usuario por ID
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            logger.warn("Usuario con ID {} no encontrado", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDto<>(false, "Usuario no encontrado", null)
            );
        }

        // Eliminar el usuario
        usuarioRepository.deleteById(id);
        logger.info("Usuario con ID {} eliminado exitosamente", id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(true, "Usuario eliminado con éxito", null)
        );
    }
}
