package com.grupod.activosfijos.depreciacion;

import com.grupod.activosfijos.divisa.DivisasEntity;
import com.grupod.activosfijos.divisa.DivisasRepository;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepreciacionService {

    private static final Logger logger = LoggerFactory.getLogger(DepreciacionService.class);
    private final DepreciacionRepository depreciacionRepository;
    private final DivisasRepository divisasRepository;

    @Autowired
    public DepreciacionService(DepreciacionRepository depreciacionRepository, DivisasRepository divisasRepository) {
        this.depreciacionRepository = depreciacionRepository;
        this.divisasRepository = divisasRepository;
    }

    public DepreciacionDto crearDepreciacion(DepreciacionDto depreciacionDto) {
        logger.info("Creando depreciación con método: {}", depreciacionDto.getMetodo());

        DivisasEntity divisasEntity = divisasRepository.findById(depreciacionDto.getIdDivisa())
                .orElseThrow(() -> new RuntimeException("Divisa no encontrada con ID: " + depreciacionDto.getIdDivisa()));

        DepreciacionEntity depreciacionEntity = new DepreciacionEntity();
        depreciacionEntity.setFecha(depreciacionDto.getFecha());
        depreciacionEntity.setMetodo(depreciacionDto.getMetodo());
        depreciacionEntity.setDetalle(depreciacionDto.getDetalle());
        depreciacionEntity.setDivisasEntity(divisasEntity);

        DepreciacionEntity nuevaDepreciacion = depreciacionRepository.save(depreciacionEntity);
        logger.info("Depreciación creada con ID: {}", nuevaDepreciacion.getIdDepreciacion());

        return new DepreciacionDto(
                nuevaDepreciacion.getIdDepreciacion(),
                nuevaDepreciacion.getFecha(),
                nuevaDepreciacion.getMetodo(),
                nuevaDepreciacion.getDetalle(),
                nuevaDepreciacion.getDivisasEntity().getIdDivisa()
        );
    }

    public List<DepreciacionDto> obtenerTodasLasDepreciaciones() {
        logger.info("Obteniendo todas las depreciaciones");

        List<DepreciacionEntity> depreciaciones = depreciacionRepository.findAll();
        return depreciaciones.stream()
                .map(depreciacion -> new DepreciacionDto(
                        depreciacion.getIdDepreciacion(),
                        depreciacion.getFecha(),
                        depreciacion.getMetodo(),
                        depreciacion.getDetalle(),
                        depreciacion.getDivisasEntity().getIdDivisa()
                ))
                .collect(Collectors.toList());
    }

    public DepreciacionDto obtenerDepreciacionPorId(Integer id) {
        logger.info("Obteniendo depreciación con ID: {}", id);

        DepreciacionEntity depreciacion = depreciacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Depreciación no encontrada con ID: " + id));

        return new DepreciacionDto(
                depreciacion.getIdDepreciacion(),
                depreciacion.getFecha(),
                depreciacion.getMetodo(),
                depreciacion.getDetalle(),
                depreciacion.getDivisasEntity().getIdDivisa()
        );
    }

    public DepreciacionDto actualizarDepreciacion(Integer id, DepreciacionDto depreciacionDto) {
        logger.info("Actualizando depreciación con ID: {}", id);

        DepreciacionEntity depreciacionEntity = depreciacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Depreciación no encontrada con ID: " + id));

        DivisasEntity divisasEntity = divisasRepository.findById(depreciacionDto.getIdDivisa())
                .orElseThrow(() -> new RuntimeException("Divisa no encontrada con ID: " + depreciacionDto.getIdDivisa()));

        depreciacionEntity.setFecha(depreciacionDto.getFecha());
        depreciacionEntity.setMetodo(depreciacionDto.getMetodo());
        depreciacionEntity.setDetalle(depreciacionDto.getDetalle());
        depreciacionEntity.setDivisasEntity(divisasEntity);

        DepreciacionEntity depreciacionActualizada = depreciacionRepository.save(depreciacionEntity);
        logger.info("Depreciación actualizada con ID: {}", depreciacionActualizada.getIdDepreciacion());

        return new DepreciacionDto(
                depreciacionActualizada.getIdDepreciacion(),
                depreciacionActualizada.getFecha(),
                depreciacionActualizada.getMetodo(),
                depreciacionActualizada.getDetalle(),
                depreciacionActualizada.getDivisasEntity().getIdDivisa()
        );
    }

    public void eliminarDepreciacion(Integer id) {
        logger.info("Eliminando depreciación con ID: {}", id);

        if (!depreciacionRepository.existsById(id)) {
            throw new RuntimeException("Depreciación no encontrada con ID: " + id);
        }

        depreciacionRepository.deleteById(id);
        logger.info("Depreciación eliminada exitosamente");
    }
}
