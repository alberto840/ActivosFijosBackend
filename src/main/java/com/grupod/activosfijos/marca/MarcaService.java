package com.grupod.activosfijos.marca;

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
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private static final Logger logger = LoggerFactory.getLogger(MarcaService.class);

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    // Método para crear una nueva marca
    public ResponseEntity<ResponseDto<MarcaDto>> crearMarca(MarcaDto marcaDto) {
        logger.info("Creando nueva marca: {}", marcaDto.getNombre());

        // Convertir DTO a entidad
        MarcaEntity marcaEntity = new MarcaEntity();
        marcaEntity.setNombre(marcaDto.getNombre());
        marcaEntity.setPaisOrigen(marcaDto.getPaisOrigen());
        marcaEntity.setDescripcion(marcaDto.getDescripcion());
        marcaEntity.setEstado(marcaDto.getEstado());

        // Guardar la entidad en la base de datos
        MarcaEntity nuevaMarca = marcaRepository.save(marcaEntity);
        logger.info("Marca creada con ID: {}", nuevaMarca.getIdMarca());

        // Convertir la entidad guardada a DTO para retornar como respuesta
        MarcaDto nuevaMarcaDto = new MarcaDto(
                nuevaMarca.getIdMarca(),
                nuevaMarca.getNombre(),
                nuevaMarca.getPaisOrigen(),
                nuevaMarca.getDescripcion(),
                nuevaMarca.getEstado()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto<>(true, "Marca creada exitosamente", nuevaMarcaDto));
    }

    // Método para obtener todas las marcas
    public ResponseEntity<ResponseDto<List<MarcaDto>>> obtenerTodasLasMarcas() {
        logger.info("Obteniendo todas las marcas");

        List<MarcaEntity> marcas = marcaRepository.findAll();
        List<MarcaDto> marcasDto = marcas.stream().map(marca -> new MarcaDto(
                marca.getIdMarca(),
                marca.getNombre(),
                marca.getPaisOrigen(),
                marca.getDescripcion(),
                marca.getEstado()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseDto<>(true, "Marcas obtenidas exitosamente", marcasDto));
    }

    // Método para obtener una marca por su ID
    public ResponseEntity<ResponseDto<MarcaDto>> obtenerMarcaPorId(Integer id) {
        logger.info("Obteniendo marca con ID: {}", id);

        Optional<MarcaEntity> marcaOpt = marcaRepository.findById(id);
        if (marcaOpt.isEmpty()) {
            logger.warn("Marca con ID {} no encontrada", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Marca no encontrada", null));
        }

        MarcaEntity marca = marcaOpt.get();
        MarcaDto marcaDto = new MarcaDto(
                marca.getIdMarca(),
                marca.getNombre(),
                marca.getPaisOrigen(),
                marca.getDescripcion(),
                marca.getEstado()
        );

        return ResponseEntity.ok(new ResponseDto<>(true, "Marca obtenida exitosamente", marcaDto));
    }

    // Método para actualizar una marca por su ID
    public ResponseEntity<ResponseDto<MarcaDto>> actualizarMarca(Integer id, MarcaDto marcaDto) {
        logger.info("Actualizando marca con ID: {}", id);

        Optional<MarcaEntity> marcaOpt = marcaRepository.findById(id);
        if (marcaOpt.isEmpty()) {
            logger.warn("Marca con ID {} no encontrada", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Marca no encontrada", null));
        }

        MarcaEntity marcaEntity = marcaOpt.get();
        marcaEntity.setNombre(marcaDto.getNombre());
        marcaEntity.setPaisOrigen(marcaDto.getPaisOrigen());
        marcaEntity.setDescripcion(marcaDto.getDescripcion());
        marcaEntity.setEstado(marcaDto.getEstado());

        MarcaEntity marcaActualizada = marcaRepository.save(marcaEntity);
        MarcaDto marcaActualizadaDto = new MarcaDto(
                marcaActualizada.getIdMarca(),
                marcaActualizada.getNombre(),
                marcaActualizada.getPaisOrigen(),
                marcaActualizada.getDescripcion(),
                marcaActualizada.getEstado()
        );

        return ResponseEntity.ok(new ResponseDto<>(true, "Marca actualizada exitosamente", marcaActualizadaDto));
    }

    // Método para eliminar una marca por su ID
    public ResponseEntity<ResponseDto<Void>> eliminarMarca(Integer id) {
        logger.info("Eliminando marca con ID: {}", id);

        if (!marcaRepository.existsById(id)) {
            logger.warn("Marca con ID {} no encontrada", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Marca no encontrada", null));
        }

        marcaRepository.deleteById(id);
        logger.info("Marca con ID {} eliminada exitosamente", id);
        return ResponseEntity.ok(new ResponseDto<>(true, "Marca eliminada exitosamente", null));
    }
}
