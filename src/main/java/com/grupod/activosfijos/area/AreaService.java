package com.grupod.activosfijos.area;

import com.grupod.activosfijos.empresa.EmpresaEntity;
import com.grupod.activosfijos.empresa.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService {

    private static final Logger logger = LoggerFactory.getLogger(AreaService.class);
    private final AreaRepository areaRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    public AreaService(AreaRepository areaRepository, EmpresaRepository empresaRepository) {
        this.areaRepository = areaRepository;
        this.empresaRepository = empresaRepository;
    }

    public AreaDto crearArea(AreaDto areaDto) {
        logger.info("Creando área: {}", areaDto.getNombre());

        EmpresaEntity empresa = empresaRepository.findById(areaDto.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + areaDto.getIdEmpresa()));

        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setNombre(areaDto.getNombre());
        areaEntity.setEmpresa(empresa);

        AreaEntity nuevaArea = areaRepository.save(areaEntity);

        logger.info("Área creada con ID: {}", nuevaArea.getIdArea());

        return new AreaDto(
                nuevaArea.getIdArea(),
                nuevaArea.getNombre(),
                nuevaArea.getEmpresa().getIdEmpresa()
        );
    }

    public List<AreaDto> obtenerTodasLasAreas() {
        logger.info("Obteniendo todas las áreas");

        List<AreaEntity> areas = areaRepository.findAll();
        return areas.stream()
                .map(area -> new AreaDto(
                        area.getIdArea(),
                        area.getNombre(),
                        area.getEmpresa().getIdEmpresa()
                ))
                .collect(Collectors.toList());
    }

    public AreaDto obtenerAreaPorId(Integer id) {
        logger.info("Obteniendo área con ID: {}", id);

        AreaEntity area = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + id));

        return new AreaDto(
                area.getIdArea(),
                area.getNombre(),
                area.getEmpresa().getIdEmpresa()
        );
    }

    public AreaDto actualizarArea(Integer id, AreaDto areaDto) {
        logger.info("Actualizando área con ID: {}", id);

        AreaEntity areaEntity = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + id));

        EmpresaEntity empresa = empresaRepository.findById(areaDto.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + areaDto.getIdEmpresa()));

        areaEntity.setNombre(areaDto.getNombre());
        areaEntity.setEmpresa(empresa);

        AreaEntity areaActualizada = areaRepository.save(areaEntity);

        return new AreaDto(
                areaActualizada.getIdArea(),
                areaActualizada.getNombre(),
                areaActualizada.getEmpresa().getIdEmpresa()
        );
    }

    public void eliminarArea(Integer id) {
        logger.info("Eliminando área con ID: {}", id);

        if (!areaRepository.existsById(id)) {
            throw new RuntimeException("Área no encontrada con ID: " + id);
        }

        areaRepository.deleteById(id);
        logger.info("Área eliminada exitosamente");
    }
}
