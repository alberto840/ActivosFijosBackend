package com.grupod.activosfijos.aula;

import com.grupod.activosfijos.bloque.BloqueEntity;
import com.grupod.activosfijos.bloque.BloqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {
    private static final Logger logger = LoggerFactory.getLogger(AulaService.class);
    private final AulaRepository aulaRepository;
    private final BloqueRepository bloqueRepository;

    @Autowired
    public AulaService(AulaRepository aulaRepository, BloqueRepository bloqueRepository) {
        this.aulaRepository = aulaRepository;
        this.bloqueRepository = bloqueRepository;
    }

    public AulaDto crearAula(AulaDto aulaDto) {
        logger.info("Creando aula: {}", aulaDto.getNombre());

        BloqueEntity bloque = bloqueRepository.findById(aulaDto.getIdBloque())
                .orElseThrow(() -> new RuntimeException("Bloque no encontrado con ID: " + aulaDto.getIdBloque()));

        AulaEntity aulaEntity = new AulaEntity();
        aulaEntity.setNombre(aulaDto.getNombre());
        aulaEntity.setBloqueEntity(bloque);

        AulaEntity nuevaAula = aulaRepository.save(aulaEntity);
        logger.info("Aula creada con ID: {}", nuevaAula.getIdAula());

        return convertirAulaEntityADto(nuevaAula);
    }

    public List<AulaDto> obtenerTodasLasAulas() {
        logger.info("Obteniendo todas las aulas");

        List<AulaEntity> aulas = aulaRepository.findAll();
        return aulas.stream()
                .map(this::convertirAulaEntityADto)
                .collect(Collectors.toList());
    }

    public AulaDto obtenerAulaPorId(Integer id) {
        logger.info("Obteniendo aula con ID: {}", id);

        AulaEntity aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada con ID: " + id));

        return convertirAulaEntityADto(aula);
    }

    public AulaDto actualizarAula(Integer id, AulaDto aulaDto) {
        logger.info("Actualizando aula con ID: {}", id);

        AulaEntity aulaEntity = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada con ID: " + id));

        BloqueEntity bloque = bloqueRepository.findById(aulaDto.getIdBloque())
                .orElseThrow(() -> new RuntimeException("Bloque no encontrado con ID: " + aulaDto.getIdBloque()));

        aulaEntity.setNombre(aulaDto.getNombre());
        aulaEntity.setBloqueEntity(bloque);

        AulaEntity aulaActualizada = aulaRepository.save(aulaEntity);

        return convertirAulaEntityADto(aulaActualizada);
    }

    public void eliminarAula(Integer id) {
        logger.info("Eliminando aula con ID: {}", id);

        if (!aulaRepository.existsById(id)) {
            throw new RuntimeException("Aula no encontrada con ID: " + id);
        }

        aulaRepository.deleteById(id);
    }

    private AulaDto convertirAulaEntityADto(AulaEntity aulaEntity) {
        return new AulaDto(
                aulaEntity.getIdAula(),
                aulaEntity.getNombre(),
                aulaEntity.getBloqueEntity().getIdBloque()
        );
    }
}
