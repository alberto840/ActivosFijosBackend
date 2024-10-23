package com.grupod.activosfijos.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public ResponseEntity<RolDto> crearRol(RolDto rolDto) {
        RolEntity rolEntity = new RolEntity();
        rolEntity.setNombre(rolDto.getNombre());

        RolEntity nuevoRol = rolRepository.save(rolEntity);

        RolDto nuevoRolDto = new RolDto(nuevoRol.getIdRol(), nuevoRol.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRolDto);
    }

    public ResponseEntity<List<RolDto>> obtenerTodosLosRoles() {
        List<RolEntity> roles = rolRepository.findAll();
        List<RolDto> rolesDto = roles.stream()
                .map(rol -> new RolDto(rol.getIdRol(), rol.getNombre()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rolesDto);
    }

    public ResponseEntity<RolDto> obtenerRolPorId(Integer id) {
        Optional<RolEntity> rolOpt = rolRepository.findById(id);
        if (rolOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        RolEntity rolEntity = rolOpt.get();
        RolDto rolDto = new RolDto(rolEntity.getIdRol(), rolEntity.getNombre());
        return ResponseEntity.ok(rolDto);
    }

    public ResponseEntity<RolDto> actualizarRol(Integer id, RolDto rolDto) {
        Optional<RolEntity> rolOpt = rolRepository.findById(id);
        if (rolOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        RolEntity rolEntity = rolOpt.get();
        rolEntity.setNombre(rolDto.getNombre());
        rolEntity = rolRepository.save(rolEntity);

        RolDto actualizadoRolDto = new RolDto(rolEntity.getIdRol(), rolEntity.getNombre());
        return ResponseEntity.ok(actualizadoRolDto);
    }

    public ResponseEntity<Void> eliminarRol(Integer id) {
        if (!rolRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        rolRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
