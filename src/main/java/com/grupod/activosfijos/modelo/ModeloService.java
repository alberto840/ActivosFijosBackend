package com.grupod.activosfijos.modelo;

import com.grupod.activosfijos.marca.MarcaEntity;
import com.grupod.activosfijos.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private static final Logger logger = LoggerFactory.getLogger(ModeloService.class);

    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    // Método para crear un nuevo modelo
    public ResponseEntity<ResponseDto<ModeloDto>> crearModelo(ModeloDto modeloDto) {
        logger.info("Creando nuevo modelo: {}", modeloDto.getNombre());

        // Convertir DTO a entidad
        ModeloEntity modeloEntity = new ModeloEntity();
        modeloEntity.setNombre(modeloDto.getNombre());
        modeloEntity.setDescripcion(modeloDto.getDescripcion());
        modeloEntity.setEstado(modeloDto.getEstado());

        // Crear la entidad asociada a la marca con el ID proporcionado en el DTO
        MarcaEntity marcaEntity = new MarcaEntity();
        marcaEntity.setIdMarca(modeloDto.getMarcaId());
        modeloEntity.setMarca(marcaEntity);

        // Guardar la entidad en la base de datos
        ModeloEntity nuevoModelo = modeloRepository.save(modeloEntity);
        logger.info("Modelo creado con ID: {}", nuevoModelo.getIdModelo());

        // Convertir la entidad guardada a DTO para retornar como respuesta
        ModeloDto nuevoModeloDto = new ModeloDto(
                nuevoModelo.getIdModelo(),
                nuevoModelo.getNombre(),
                nuevoModelo.getDescripcion(),
                nuevoModelo.getEstado(),
                nuevoModelo.getMarca().getIdMarca()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto<>(true, "Modelo creado exitosamente", nuevoModeloDto));
    }

    // Método para obtener todos los modelos
    public ResponseEntity<ResponseDto<List<ModeloDto>>> obtenerTodosLosModelos() {
        logger.info("Obteniendo todos los modelos");

        List<ModeloEntity> modelos = modeloRepository.findAll();
        List<ModeloDto> modelosDto = modelos.stream().map(modelo -> new ModeloDto(
                modelo.getIdModelo(),
                modelo.getNombre(),
                modelo.getDescripcion(),
                modelo.getEstado(),
                modelo.getMarca().getIdMarca()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseDto<>(true, "Modelos obtenidos exitosamente", modelosDto));
    }

    // Método para obtener un modelo por su ID
    public ResponseEntity<ResponseDto<ModeloDto>> obtenerModeloPorId(Integer id) {
        logger.info("Obteniendo modelo con ID: {}", id);

        Optional<ModeloEntity> modeloOpt = modeloRepository.findById(id);
        if (modeloOpt.isEmpty()) {
            logger.warn("Modelo con ID {} no encontrado", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Modelo no encontrado", null));
        }

        ModeloEntity modelo = modeloOpt.get();
        ModeloDto modeloDto = new ModeloDto(
                modelo.getIdModelo(),
                modelo.getNombre(),
                modelo.getDescripcion(),
                modelo.getEstado(),
                modelo.getMarca().getIdMarca()
        );

        return ResponseEntity.ok(new ResponseDto<>(true, "Modelo obtenido exitosamente", modeloDto));
    }

    // Método para actualizar un modelo por su ID
    public ResponseEntity<ResponseDto<ModeloDto>> actualizarModelo(Integer id, ModeloDto modeloDto) {
        logger.info("Actualizando modelo con ID: {}", id);

        Optional<ModeloEntity> modeloOpt = modeloRepository.findById(id);
        if (modeloOpt.isEmpty()) {
            logger.warn("Modelo con ID {} no encontrado", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Modelo no encontrado", null));
        }

        ModeloEntity modeloEntity = modeloOpt.get();
        modeloEntity.setNombre(modeloDto.getNombre());
        modeloEntity.setDescripcion(modeloDto.getDescripcion());
        modeloEntity.setEstado(modeloDto.getEstado());

        // Actualizar la marca asociada si se proporciona un nuevo ID de marca
        if (modeloDto.getMarcaId() != null) {
            MarcaEntity marcaEntity = new MarcaEntity();
            marcaEntity.setIdMarca(modeloDto.getMarcaId());
            modeloEntity.setMarca(marcaEntity);
        }

        ModeloEntity modeloActualizado = modeloRepository.save(modeloEntity);
        ModeloDto modeloActualizadoDto = new ModeloDto(
                modeloActualizado.getIdModelo(),
                modeloActualizado.getNombre(),
                modeloActualizado.getDescripcion(),
                modeloActualizado.getEstado(),
                modeloActualizado.getMarca().getIdMarca()
        );

        return ResponseEntity.ok(new ResponseDto<>(true, "Modelo actualizado exitosamente", modeloActualizadoDto));
    }

    // Método para eliminar un modelo por su ID
    public ResponseEntity<ResponseDto<Void>> eliminarModelo(Integer id) {
        logger.info("Eliminando modelo con ID: {}", id);

        if (!modeloRepository.existsById(id)) {
            logger.warn("Modelo con ID {} no encontrado", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Modelo no encontrado", null));
        }

        modeloRepository.deleteById(id);
        logger.info("Modelo con ID {} eliminado exitosamente", id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Modelo eliminado exitosamente", null));
    }
}
