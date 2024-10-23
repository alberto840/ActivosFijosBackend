package com.grupod.activosfijos.identificador;

import com.grupod.activosfijos.activo.ActivoEntity; // Importación de ActivoEntity
import com.grupod.activosfijos.activo.ActivoRepository; // Importación de ActivoRepository
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IdentificadorService {

    private static final Logger logger = LoggerFactory.getLogger(IdentificadorService.class);
    private final IdentificadorRepository identificadorRepository;
    private final ActivoRepository activoRepository; // Repositorio para obtener ActivoEntity

    @Autowired
    public IdentificadorService(IdentificadorRepository identificadorRepository, ActivoRepository activoRepository) {
        this.identificadorRepository = identificadorRepository;
        this.activoRepository = activoRepository; // Inyección de ActivoRepository
    }

    public IdentificadorDto crearIdentificador(IdentificadorDto identificadorDto) {
        logger.info("Creando identificador con código QR: {}", identificadorDto.getCodigoQr());

        // Obtener el ActivoEntity correspondiente si se proporciona un idActivo
        ActivoEntity activoEntity = null;
        if (identificadorDto.getIdActivo() != null) {
            activoEntity = activoRepository.findById(identificadorDto.getIdActivo())
                    .orElseThrow(() -> new RuntimeException("Activo no encontrado con ID: " + identificadorDto.getIdActivo()));
        }

        // Crear IdentificadorEntity y asignar los valores
        IdentificadorEntity identificadorEntity = new IdentificadorEntity(
                null,
                identificadorDto.getCodigoQr(),
                identificadorDto.getCodigoBarra(),
                activoEntity // Asignar la relación con el ActivoEntity
        );

        // Guardar el identificador en la base de datos
        IdentificadorEntity nuevoIdentificador = identificadorRepository.save(identificadorEntity);
        logger.info("Identificador creado con ID: {}", nuevoIdentificador.getIdIdentificador());

        // Retornar el DTO del identificador creado
        return convertirAIdentificadorDto(nuevoIdentificador);
    }

    public List<IdentificadorDto> obtenerTodosLosIdentificadores() {
        logger.info("Obteniendo todos los identificadores");
        return identificadorRepository.findAll()
                .stream()
                .map(this::convertirAIdentificadorDto)
                .collect(Collectors.toList());
    }

    public IdentificadorDto obtenerIdentificadorPorId(Integer id) {
        logger.info("Obteniendo identificador con ID: {}", id);
        IdentificadorEntity identificadorEntity = identificadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identificador no encontrado"));

        return convertirAIdentificadorDto(identificadorEntity);
    }

    public IdentificadorDto actualizarIdentificador(Integer id, IdentificadorDto identificadorDto) {
        logger.info("Actualizando identificador con ID: {}", id);

        // Obtener el identificador existente
        IdentificadorEntity identificadorEntity = identificadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identificador no encontrado"));

        // Actualizar los valores del identificador
        identificadorEntity.setCodigoQr(identificadorDto.getCodigoQr());
        identificadorEntity.setCodigoBarra(identificadorDto.getCodigoBarra());

        // Si se proporciona un nuevo idActivo, actualizar la relación
        if (identificadorDto.getIdActivo() != null) {
            ActivoEntity activoEntity = activoRepository.findById(identificadorDto.getIdActivo())
                    .orElseThrow(() -> new RuntimeException("Activo no encontrado con ID: " + identificadorDto.getIdActivo()));
            identificadorEntity.setActivoEntity(activoEntity);
        }

        IdentificadorEntity identificadorActualizado = identificadorRepository.save(identificadorEntity);
        logger.info("Identificador actualizado con ID: {}", identificadorActualizado.getIdIdentificador());

        return convertirAIdentificadorDto(identificadorActualizado);
    }

    public void eliminarIdentificador(Integer id) {
        logger.info("Eliminando identificador con ID: {}", id);
        if (!identificadorRepository.existsById(id)) {
            throw new RuntimeException("Identificador no encontrado");
        }
        identificadorRepository.deleteById(id);
    }

    private IdentificadorDto convertirAIdentificadorDto(IdentificadorEntity identificadorEntity) {
        return new IdentificadorDto(
                identificadorEntity.getIdIdentificador(),
                identificadorEntity.getCodigoQr(),
                identificadorEntity.getCodigoBarra(),
                identificadorEntity.getActivoEntity() != null ? identificadorEntity.getActivoEntity().getIdActivo() : null // Incluir el ID del activo en el DTO
        );
    }
}
