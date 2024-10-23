package com.grupod.activosfijos.estadoActivo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    private final Logger logger = LoggerFactory.getLogger(EstadoService.class);
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public EstadoactivoDto crearEstado(EstadoactivoDto estadoactivoDto) {
        logger.info("Creando nuevo estado: {}", estadoactivoDto.getNombre());
        EstadoactivoEntity estadoEntity = convertirDtoAEntidad(estadoactivoDto);
        EstadoactivoEntity nuevoEstado = estadoRepository.save(estadoEntity);
        return convertirEntidadADto(nuevoEstado);
    }

    public List<EstadoactivoDto> obtenerTodosLosEstados() {
        logger.info("Obteniendo todos los estados");
        List<EstadoactivoEntity> estados = estadoRepository.findAll();
        return estados.stream().map(this::convertirEntidadADto).collect(Collectors.toList());
    }

    public EstadoactivoDto obtenerEstadoPorId(Integer id) {
        logger.info("Obteniendo estado con ID: {}", id);
        EstadoactivoEntity estado = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + id));
        return convertirEntidadADto(estado);
    }

    public EstadoactivoDto actualizarEstado(Integer id, EstadoactivoDto estadoactivoDto) {
        logger.info("Actualizando estado con ID: {}", id);
        EstadoactivoEntity estadoEntity = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + id));

        estadoEntity.setNombre(estadoactivoDto.getNombre());
        estadoEntity.setDescripcion(estadoactivoDto.getDescripcion());

        EstadoactivoEntity estadoActualizado = estadoRepository.save(estadoEntity);
        return convertirEntidadADto(estadoActualizado);
    }

    public void eliminarEstado(Integer id) {
        logger.info("Eliminando estado con ID: {}", id);
        if (!estadoRepository.existsById(id)) {
            throw new RuntimeException("Estado no encontrado con ID: " + id);
        }
        estadoRepository.deleteById(id);
    }

    private EstadoactivoEntity convertirDtoAEntidad(EstadoactivoDto estadoactivoDto) {
        EstadoactivoEntity estadoEntity = new EstadoactivoEntity();
        estadoEntity.setNombre(estadoactivoDto.getNombre());
        estadoEntity.setDescripcion(estadoactivoDto.getDescripcion());
        return estadoEntity;
    }

    private EstadoactivoDto convertirEntidadADto(EstadoactivoEntity estadoEntity) {
        return new EstadoactivoDto(
                estadoEntity.getIdEstado(),
                estadoEntity.getNombre(),
                estadoEntity.getDescripcion()
        );
    }
}
