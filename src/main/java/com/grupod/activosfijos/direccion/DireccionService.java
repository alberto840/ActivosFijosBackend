package com.grupod.activosfijos.direccion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DireccionService {

    private static final Logger logger = LoggerFactory.getLogger(DireccionService.class);
    private final DireccionRepository direccionRepository;

    @Autowired
    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public DireccionDto crearDireccion(DireccionDto direccionDto) {
        logger.info("Creando dirección: Calle {}, Zona {}", direccionDto.getCalle(), direccionDto.getZona());

        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCalle(direccionDto.getCalle());
        direccionEntity.setDetalle(direccionDto.getDetalle());
        direccionEntity.setZona(direccionDto.getZona());

        DireccionEntity nuevaDireccion = direccionRepository.save(direccionEntity);

        logger.info("Dirección creada con ID: {}", nuevaDireccion.getIdDireccion());

        return new DireccionDto(
                nuevaDireccion.getIdDireccion(),
                nuevaDireccion.getCalle(),
                nuevaDireccion.getDetalle(),
                nuevaDireccion.getZona()
        );
    }

    public List<DireccionDto> obtenerTodasLasDirecciones() {
        logger.info("Obteniendo todas las direcciones");

        List<DireccionEntity> direcciones = direccionRepository.findAll();

        return direcciones.stream()
                .map(direccion -> new DireccionDto(
                        direccion.getIdDireccion(),
                        direccion.getCalle(),
                        direccion.getDetalle(),
                        direccion.getZona()
                ))
                .collect(Collectors.toList());
    }

    public DireccionDto obtenerDireccionPorId(Integer id) {
        logger.info("Obteniendo dirección con ID: {}", id);
        DireccionEntity direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));

        return new DireccionDto(
                direccion.getIdDireccion(),
                direccion.getCalle(),
                direccion.getDetalle(),
                direccion.getZona()
        );
    }

    public DireccionDto actualizarDireccion(Integer id, DireccionDto direccionDto) {
        logger.info("Actualizando dirección con ID: {}", id);

        DireccionEntity direccionEntity = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));

        direccionEntity.setCalle(direccionDto.getCalle());
        direccionEntity.setDetalle(direccionDto.getDetalle());
        direccionEntity.setZona(direccionDto.getZona());

        DireccionEntity direccionActualizada = direccionRepository.save(direccionEntity);

        return new DireccionDto(
                direccionActualizada.getIdDireccion(),
                direccionActualizada.getCalle(),
                direccionActualizada.getDetalle(),
                direccionActualizada.getZona()
        );
    }

    public void eliminarDireccion(Integer id) {
        logger.info("Eliminando dirección con ID: {}", id);

        if (!direccionRepository.existsById(id)) {
            throw new RuntimeException("Dirección no encontrada");
        }

        direccionRepository.deleteById(id);
    }
}
