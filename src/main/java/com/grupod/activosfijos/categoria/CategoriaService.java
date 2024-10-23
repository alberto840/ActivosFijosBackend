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

        CategoriaEntity nuevaCategoria = categoriaRepository.save(categoriaEntity);
        logger.info("Categoría creada con ID: {}", nuevaCategoria.getIdCategoria());

        return new CategoriaDto(nuevaCategoria.getIdCategoria(), nuevaCategoria.getNombre());
    }

    public List<CategoriaDto> obtenerTodasLasCategorias() {
        logger.info("Obteniendo todas las categorías");

        List<CategoriaEntity> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(categoria -> new CategoriaDto(categoria.getIdCategoria(), categoria.getNombre()))
                .collect(Collectors.toList());
    }

    public CategoriaDto obtenerCategoriaPorId(Integer id) {
        logger.info("Obteniendo categoría con ID: {}", id);
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return new CategoriaDto(categoria.getIdCategoria(), categoria.getNombre());
    }

    public CategoriaDto actualizarCategoria(Integer id, CategoriaDto categoriaDto) {
        logger.info("Actualizando categoría con ID: {}", id);

        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoriaEntity.setNombre(categoriaDto.getNombre());

        CategoriaEntity categoriaActualizada = categoriaRepository.save(categoriaEntity);

        return new CategoriaDto(categoriaActualizada.getIdCategoria(), categoriaActualizada.getNombre());
    }

    public void eliminarCategoria(Integer id) {
        logger.info("Eliminando categoría con ID: {}", id);

        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }

        categoriaRepository.deleteById(id);
    }
}
