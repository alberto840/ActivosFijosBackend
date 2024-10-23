package com.grupod.activosfijos.proyecto;

import com.grupod.activosfijos.area.AreaEntity;
import com.grupod.activosfijos.area.AreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoService.class);
    private final ProyectoRepository proyectoRepository;
    private final AreaRepository areaRepository;

    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository, AreaRepository areaRepository) {
        this.proyectoRepository = proyectoRepository;
        this.areaRepository = areaRepository;
    }

    public ProyectoDto crearProyecto(ProyectoDto proyectoDto) {
        logger.info("Creando proyecto: {}", proyectoDto.getNombre());

        AreaEntity areaEntity = areaRepository.findById(proyectoDto.getIdArea())
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + proyectoDto.getIdArea()));

        ProyectoEntity proyectoEntity = new ProyectoEntity();
        proyectoEntity.setNombre(proyectoDto.getNombre());
        proyectoEntity.setFechaInicio(proyectoDto.getFechaInicio());
        proyectoEntity.setFechaFin(proyectoDto.getFechaFin());
        proyectoEntity.setAreaEntityId(areaEntity);

        ProyectoEntity nuevoProyecto = proyectoRepository.save(proyectoEntity);
        logger.info("Proyecto creado con ID: {}", nuevoProyecto.getIdProyecto());

        return new ProyectoDto(
                nuevoProyecto.getIdProyecto(),
                nuevoProyecto.getNombre(),
                nuevoProyecto.getFechaInicio(),
                nuevoProyecto.getFechaFin(),
                nuevoProyecto.getAreaEntityId().getIdArea()
        );
    }

    public List<ProyectoDto> obtenerTodosLosProyectos() {
        logger.info("Obteniendo todos los proyectos");

        List<ProyectoEntity> proyectos = proyectoRepository.findAll();
        return proyectos.stream()
                .map(proyecto -> new ProyectoDto(
                        proyecto.getIdProyecto(),
                        proyecto.getNombre(),
                        proyecto.getFechaInicio(),
                        proyecto.getFechaFin(),
                        proyecto.getAreaEntityId().getIdArea()
                ))
                .collect(Collectors.toList());
    }

    public ProyectoDto obtenerProyectoPorId(Integer id) {
        logger.info("Obteniendo proyecto con ID: {}", id);

        ProyectoEntity proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));

        return new ProyectoDto(
                proyecto.getIdProyecto(),
                proyecto.getNombre(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                proyecto.getAreaEntityId().getIdArea()
        );
    }

    public ProyectoDto actualizarProyecto(Integer id, ProyectoDto proyectoDto) {
        logger.info("Actualizando proyecto con ID: {}", id);

        ProyectoEntity proyectoEntity = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));

        AreaEntity areaEntity = areaRepository.findById(proyectoDto.getIdArea())
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + proyectoDto.getIdArea()));

        proyectoEntity.setNombre(proyectoDto.getNombre());
        proyectoEntity.setFechaInicio(proyectoDto.getFechaInicio());
        proyectoEntity.setFechaFin(proyectoDto.getFechaFin());
        proyectoEntity.setAreaEntityId(areaEntity);

        ProyectoEntity proyectoActualizado = proyectoRepository.save(proyectoEntity);
        logger.info("Proyecto actualizado con ID: {}", proyectoActualizado.getIdProyecto());

        return new ProyectoDto(
                proyectoActualizado.getIdProyecto(),
                proyectoActualizado.getNombre(),
                proyectoActualizado.getFechaInicio(),
                proyectoActualizado.getFechaFin(),
                proyectoActualizado.getAreaEntityId().getIdArea()
        );
    }

    public void eliminarProyecto(Integer id) {
        logger.info("Eliminando proyecto con ID: {}", id);

        if (!proyectoRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado con ID: " + id);
        }

        proyectoRepository.deleteById(id);
        logger.info("Proyecto eliminado con éxito");
    }
}
