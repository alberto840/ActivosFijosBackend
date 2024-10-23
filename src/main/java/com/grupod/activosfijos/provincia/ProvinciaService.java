package com.grupod.activosfijos.provincia;

import com.grupod.activosfijos.departamento.DepartamentoEntity;
import com.grupod.activosfijos.departamento.DepartamentoRepository;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinciaService {

    private static final Logger logger = LoggerFactory.getLogger(ProvinciaService.class);

    private final ProvinciaRepository provinciaRepository;
    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public ProvinciaService(ProvinciaRepository provinciaRepository, DepartamentoRepository departamentoRepository) {
        this.provinciaRepository = provinciaRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public ProvinciaDto crearProvincia(ProvinciaDto provinciaDto) {
        logger.debug("Creando provincia con nombre: {} y idDepartamento: {}", provinciaDto.getNombre(), provinciaDto.getIdDepartamento());

        DepartamentoEntity departamentoEntity = departamentoRepository.findById(provinciaDto.getIdDepartamento())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con id: " + provinciaDto.getIdDepartamento()));

        ProvinciaEntity provinciaEntity = new ProvinciaEntity();
        provinciaEntity.setNombre(provinciaDto.getNombre());
        provinciaEntity.setDepartamentoId(departamentoEntity);

        ProvinciaEntity nuevaProvincia = provinciaRepository.save(provinciaEntity);
        logger.info("Provincia creada con ID: {}", nuevaProvincia.getIdProvincia());

        return new ProvinciaDto(
                nuevaProvincia.getIdProvincia(),
                nuevaProvincia.getNombre(),
                nuevaProvincia.getDepartamentoId().getIdDepartamento()
        );
    }

    public List<ProvinciaDto> obtenerTodasLasProvincias() {
        logger.debug("Obteniendo todas las provincias");
        List<ProvinciaEntity> provincias = provinciaRepository.findAll();

        return provincias.stream()
                .map(provincia -> new ProvinciaDto(
                        provincia.getIdProvincia(),
                        provincia.getNombre(),
                        provincia.getDepartamentoId().getIdDepartamento()
                ))
                .collect(Collectors.toList());
    }

    public ProvinciaDto obtenerProvinciaPorId(Integer id) {
        logger.debug("Obteniendo provincia con ID: {}", id);
        ProvinciaEntity provincia = provinciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provincia no encontrada con id: " + id));

        return new ProvinciaDto(
                provincia.getIdProvincia(),
                provincia.getNombre(),
                provincia.getDepartamentoId().getIdDepartamento()
        );
    }

    public ProvinciaDto actualizarProvincia(Integer id, ProvinciaDto provinciaDto) {
        logger.debug("Actualizando provincia con ID: {}", id);
        ProvinciaEntity provinciaEntity = provinciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provincia no encontrada con id: " + id));

        provinciaEntity.setNombre(provinciaDto.getNombre());

        if (provinciaDto.getIdDepartamento() != null) {
            DepartamentoEntity departamentoEntity = departamentoRepository.findById(provinciaDto.getIdDepartamento())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado con id: " + provinciaDto.getIdDepartamento()));
            provinciaEntity.setDepartamentoId(departamentoEntity);
        }

        ProvinciaEntity provinciaActualizada = provinciaRepository.save(provinciaEntity);
        logger.info("Provincia actualizada con ID: {}", provinciaActualizada.getIdProvincia());

        return new ProvinciaDto(
                provinciaActualizada.getIdProvincia(),
                provinciaActualizada.getNombre(),
                provinciaActualizada.getDepartamentoId().getIdDepartamento()
        );
    }

    public void eliminarProvincia(Integer id) {
        logger.debug("Eliminando provincia con ID: {}", id);
        if (!provinciaRepository.existsById(id)) {
            logger.warn("Intento de eliminar provincia inexistente con ID: {}", id);
            throw new RuntimeException("Provincia no encontrada con id: " + id);
        }

        provinciaRepository.deleteById(id);
        logger.info("Provincia eliminada con ID: {}", id);
    }
}
