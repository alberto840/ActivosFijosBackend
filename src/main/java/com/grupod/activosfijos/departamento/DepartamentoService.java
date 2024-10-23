package com.grupod.activosfijos.departamento;

import com.grupod.activosfijos.pais.PaisEntity;
import com.grupod.activosfijos.pais.PaisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    private static final Logger logger = LoggerFactory.getLogger(DepartamentoService.class);

    private final DepartamentoRepository departamentoRepository;
    private final PaisRepository paisRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository, PaisRepository paisRepository) {
        this.departamentoRepository = departamentoRepository;
        this.paisRepository = paisRepository;
    }

    public DepartamentoDto crearDepartamento(DepartamentoDto departamentoDto) {
        logger.debug("Creando departamento con nombre: {} y idPais: {}", departamentoDto.getNombre(), departamentoDto.getIdPais());

        PaisEntity paisEntity = paisRepository.findById(departamentoDto.getIdPais())
                .orElseThrow(() -> new RuntimeException("País no encontrado con id: " + departamentoDto.getIdPais()));

        DepartamentoEntity departamentoEntity = new DepartamentoEntity();
        departamentoEntity.setNombre(departamentoDto.getNombre());
        departamentoEntity.setPaisEntity(paisEntity);

        DepartamentoEntity nuevoDepartamento = departamentoRepository.save(departamentoEntity);
        logger.info("Departamento creado con ID: {}", nuevoDepartamento.getIdDepartamento());

        return new DepartamentoDto(
                nuevoDepartamento.getIdDepartamento(),
                nuevoDepartamento.getNombre(),
                nuevoDepartamento.getPaisEntity().getIdPais()
        );
    }

    public List<DepartamentoDto> obtenerTodosLosDepartamentos() {
        logger.debug("Obteniendo todos los departamentos");
        List<DepartamentoEntity> departamentos = departamentoRepository.findAll();

        return departamentos.stream()
                .map(departamento -> new DepartamentoDto(
                        departamento.getIdDepartamento(),
                        departamento.getNombre(),
                        departamento.getPaisEntity().getIdPais()
                ))
                .collect(Collectors.toList());
    }

    public DepartamentoDto obtenerDepartamentoPorId(Integer id) {
        logger.debug("Obteniendo departamento con ID: {}", id);
        DepartamentoEntity departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con id: " + id));

        return new DepartamentoDto(
                departamento.getIdDepartamento(),
                departamento.getNombre(),
                departamento.getPaisEntity().getIdPais()
        );
    }

    public DepartamentoDto actualizarDepartamento(Integer id, DepartamentoDto departamentoDto) {
        logger.debug("Actualizando departamento con ID: {}", id);
        DepartamentoEntity departamentoEntity = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con id: " + id));

        departamentoEntity.setNombre(departamentoDto.getNombre());

        if (departamentoDto.getIdPais() != null) {
            PaisEntity paisEntity = paisRepository.findById(departamentoDto.getIdPais())
                    .orElseThrow(() -> new RuntimeException("País no encontrado con id: " + departamentoDto.getIdPais()));
            departamentoEntity.setPaisEntity(paisEntity);
        }

        DepartamentoEntity departamentoActualizado = departamentoRepository.save(departamentoEntity);
        logger.info("Departamento actualizado con ID: {}", departamentoActualizado.getIdDepartamento());

        return new DepartamentoDto(
                departamentoActualizado.getIdDepartamento(),
                departamentoActualizado.getNombre(),
                departamentoActualizado.getPaisEntity().getIdPais()
        );
    }

    public void eliminarDepartamento(Integer id) {
        logger.debug("Eliminando departamento con ID: {}", id);
        if (!departamentoRepository.existsById(id)) {
            logger.warn("Intento de eliminar departamento inexistente con ID: {}", id);
            throw new RuntimeException("Departamento no encontrado con id: " + id);
        }

        departamentoRepository.deleteById(id);
        logger.info("Departamento eliminado con ID: {}", id);
    }
}
