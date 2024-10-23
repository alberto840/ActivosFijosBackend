package com.grupod.activosfijos.pais;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaisService {

    private static final Logger logger = LoggerFactory.getLogger(PaisService.class);

    private final PaisRepository paisRepository;

    @Autowired
    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public PaisDto crearPais(PaisDto paisDto) {
        // Convertir PaisDto a PaisEntity
        PaisEntity paisEntity = new PaisEntity();
        paisEntity.setNombre(paisDto.getNombre());
        // Guardar la entidad en la base de datos
        PaisEntity nuevoPais = paisRepository.save(paisEntity);
        // Convertir la entidad guardada de nuevo a PaisDto
        return new PaisDto(
                nuevoPais.getIdPais(),
                nuevoPais.getNombre()
        );
    }

    public List<PaisDto> obtenerTodosLosPaises() {
        // Obtener todas las entidades de la base de datos
        List<PaisEntity> paises = paisRepository.findAll();
        // Convertir la lista de entidades a una lista de DTOs
        return paises.stream()
                .map(pais -> new PaisDto(
                        pais.getIdPais(),
                        pais.getNombre()
                ))
                .collect(Collectors.toList());
    }

    public PaisDto obtenerPaisPorId(Integer id) {
        // Buscar la entidad por su ID
        PaisEntity pais = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("País no encontrado"));
        // Convertir la entidad a DTO
        return new PaisDto(
                pais.getIdPais(),
                pais.getNombre()
        );
    }

    public PaisDto actualizarPais(Integer id, PaisDto paisDto) {
        // Buscar la entidad existente por su ID
        PaisEntity paisEntity = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("País no encontrado"));
        // Actualizar los campos de la entidad
        paisEntity.setNombre(paisDto.getNombre());
        // Guardar la entidad actualizada en la base de datos
        PaisEntity paisActualizado = paisRepository.save(paisEntity);
        // Convertir la entidad actualizada a DTO
        return new PaisDto(
                paisActualizado.getIdPais(),
                paisActualizado.getNombre()
        );
    }

    public void eliminarPais(Integer id) {
        // Verificar si la entidad existe antes de eliminarla
        if (!paisRepository.existsById(id)) {
            throw new RuntimeException("País no encontrado");
        }
        // Eliminar la entidad de la base de datos
        paisRepository.deleteById(id);
    }
}
