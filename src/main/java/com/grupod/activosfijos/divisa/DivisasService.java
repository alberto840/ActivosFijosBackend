package com.grupod.activosfijos.divisa;

import com.grupod.activosfijos.utils.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DivisasService {
    private final DivisasRepository divisasRepository;

    @Autowired
    public DivisasService(DivisasRepository divisasRepository) {
        this.divisasRepository = divisasRepository;
    }

    // Método para crear una nueva divisa
    public ResponseEntity<ResponseDto<DivisasDto>> crearDivisa(DivisasDto divisasDto) {
        // Crear una nueva entidad DivisasEntity a partir del DTO
        DivisasEntity divisaEntity = new DivisasEntity();
        divisaEntity.setNombre(divisasDto.getNombre());
        divisaEntity.setValor(divisasDto.getValor());
        divisaEntity.setFecha(new Date()); // Asignar la fecha actual
        divisaEntity.setAbreviacion(divisasDto.getAbreviacion()); // Asignar la abreviación de la divisa

        // Guardar la nueva entidad en el repositorio
        DivisasEntity nuevaDivisa = divisasRepository.save(divisaEntity);

        // Crear un DTO a partir de la entidad guardada
        DivisasDto nuevaDivisaDto = new DivisasDto(
                nuevaDivisa.getIdDivisa(),
                nuevaDivisa.getNombre(),
                nuevaDivisa.getValor(),
                nuevaDivisa.getFecha(),
                nuevaDivisa.getAbreviacion() // Incluir la abreviación en el DTO
        );

        // Retornar una respuesta exitosa con el DTO creado
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto<>(true, "Divisa creada exitosamente", nuevaDivisaDto));
    }

    // Método para obtener todas las divisas
    public ResponseEntity<ResponseDto<List<DivisasDto>>> obtenerTodasLasDivisas() {
        // Obtener todas las entidades desde el repositorio
        List<DivisasEntity> divisas = divisasRepository.findAll();

        // Convertir las entidades a DTOs
        List<DivisasDto> divisasDto = divisas.stream()
                .map(divisa -> new DivisasDto(
                        divisa.getIdDivisa(),
                        divisa.getNombre(),
                        divisa.getValor(),
                        divisa.getFecha(),
                        divisa.getAbreviacion() // Incluir la abreviación en el DTO
                ))
                .collect(Collectors.toList());

        // Retornar una respuesta exitosa con la lista de DTOs
        ResponseDto<List<DivisasDto>> responseDto = new ResponseDto<>(true, "Divisas obtenidas exitosamente", divisasDto);
        return ResponseEntity.ok(responseDto);
    }

    // Método para obtener una divisa por su ID
    public ResponseEntity<ResponseDto<DivisasDto>> obtenerDivisaPorId(Integer id) {
        // Buscar la entidad por su ID
        Optional<DivisasEntity> divisaOpt = divisasRepository.findById(id);
        if (divisaOpt.isEmpty()) {
            // Retornar una respuesta de error si no se encuentra la divisa
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Divisa no encontrada", null));
        }

        // Convertir la entidad encontrada a un DTO
        DivisasEntity divisaEntity = divisaOpt.get();
        DivisasDto divisaDto = new DivisasDto(
                divisaEntity.getIdDivisa(),
                divisaEntity.getNombre(),
                divisaEntity.getValor(),
                divisaEntity.getFecha(),
                divisaEntity.getAbreviacion() // Incluir la abreviación en el DTO
        );

        // Retornar una respuesta exitosa con el DTO creado
        return ResponseEntity.ok(new ResponseDto<>(true, "Divisa obtenida exitosamente", divisaDto));
    }

    // Método para actualizar una divisa por su ID
    public ResponseEntity<ResponseDto<DivisasDto>> actualizarDivisa(Integer id, DivisasDto divisasDto) {
        // Buscar la entidad por su ID
        Optional<DivisasEntity> divisaOpt = divisasRepository.findById(id);
        if (divisaOpt.isEmpty()) {
            // Retornar una respuesta de error si no se encuentra la divisa
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Divisa no encontrada", null));
        }

        // Actualizar la entidad con los nuevos valores del DTO
        DivisasEntity divisaEntity = divisaOpt.get();
        divisaEntity.setNombre(divisasDto.getNombre());
        divisaEntity.setValor(divisasDto.getValor());
        divisaEntity.setAbreviacion(divisasDto.getAbreviacion()); // Actualizar la abreviación

        // Mantener la fecha existente si no se proporciona en el DTO
        if (divisasDto.getFecha() != null) {
            divisaEntity.setFecha(divisasDto.getFecha());
        }

        // Guardar la entidad actualizada en el repositorio
        divisaEntity = divisasRepository.save(divisaEntity);

        // Convertir la entidad actualizada a un DTO
        DivisasDto actualizadaDivisaDto = new DivisasDto(
                divisaEntity.getIdDivisa(),
                divisaEntity.getNombre(),
                divisaEntity.getValor(),
                divisaEntity.getFecha(),
                divisaEntity.getAbreviacion() // Incluir la abreviación en el DTO
        );

        // Retornar una respuesta exitosa con el DTO actualizado
        return ResponseEntity.ok(new ResponseDto<>(true, "Divisa actualizada exitosamente", actualizadaDivisaDto));
    }

    // Método para eliminar una divisa por su ID
    public ResponseEntity<ResponseDto<Void>> eliminarDivisa(Integer id) {
        // Verificar si la divisa existe en el repositorio
        if (!divisasRepository.existsById(id)) {
            // Retornar una respuesta de error si no se encuentra la divisa
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>(false, "Divisa no encontrada", null));
        }

        // Eliminar la divisa del repositorio
        divisasRepository.deleteById(id);

        // Retornar una respuesta exitosa indicando que la divisa fue eliminada
        return ResponseEntity.ok(new ResponseDto<>(true, "Divisa eliminada exitosamente", null));
    }
}
