package com.grupod.activosfijos.categoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDto crearCategoria(CategoriaDto categoriaDto) {
        logger.info("Creando nueva categoría: {}", categoriaDto.getNombre());

        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNombre(categoriaDto.getNombre());
        categoriaEntity.setTiempoDeVida(categoriaDto.getTiempoDeVida());
        categoriaEntity.setCoeficienteAnual(categoriaDto.getCoeficienteAnual());

        CategoriaEntity nuevaCategoria = categoriaRepository.save(categoriaEntity);
        return convertirEntidadADto(nuevaCategoria);
    }

    public List<CategoriaDto> obtenerTodasLasCategorias() {
        logger.info("Obteniendo todas las categorías");
        return categoriaRepository.findAll().stream()
                .map(this::convertirEntidadADto)
                .collect(Collectors.toList());
    }

    public CategoriaDto obtenerCategoriaPorId(Integer id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return convertirEntidadADto(categoria);
    }

    public CategoriaDto actualizarCategoria(Integer id, CategoriaDto categoriaDto) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoriaEntity.setNombre(categoriaDto.getNombre());
        categoriaEntity.setTiempoDeVida(categoriaDto.getTiempoDeVida());
        categoriaEntity.setCoeficienteAnual(categoriaDto.getCoeficienteAnual());

        return convertirEntidadADto(categoriaRepository.save(categoriaEntity));
    }

    public void eliminarCategoria(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaDto convertirEntidadADto(CategoriaEntity categoriaEntity) {
        return new CategoriaDto(
                categoriaEntity.getIdCategoria(),
                categoriaEntity.getNombre(),
                categoriaEntity.getTiempoDeVida(),
                categoriaEntity.getCoeficienteAnual()
        );
    }
}
