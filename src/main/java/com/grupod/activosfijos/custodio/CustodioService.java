package com.grupod.activosfijos.custodio;

import com.grupod.activosfijos.config.JwtConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustodioService {

    private static final Logger logger = LoggerFactory.getLogger(CustodioService.class);
    private final CustodioRepository custodioRepository;
    private final JwtConfig jwtConfig;

    @Autowired
    public CustodioService(CustodioRepository custodioRepository, JwtConfig jwtConfig) {
        this.custodioRepository = custodioRepository;
        this.jwtConfig = jwtConfig;
    }

    public CustodioDto crearCustodio(CustodioDto custodioDto) {
        logger.info("Creando nuevo custodio: {}", custodioDto.getNombre());

        CustodioEntity custodioEntity = new CustodioEntity(
                null,
                custodioDto.getNombre(),
                custodioDto.getApellidoPaterno(),
                custodioDto.getApellidoMaterno(),
                custodioDto.getCorreo(),
                custodioDto.getTelefono(),
                custodioDto.getCi() // Asignar el valor de CI (carnet de identidad)
        );

        CustodioEntity nuevoCustodio = custodioRepository.save(custodioEntity);
        logger.info("Custodio creado con ID: {}", nuevoCustodio.getIdCustodio());

        return new CustodioDto(
                nuevoCustodio.getIdCustodio(),
                nuevoCustodio.getNombre(),
                nuevoCustodio.getApellidoPaterno(),
                nuevoCustodio.getApellidoMaterno(),
                nuevoCustodio.getCorreo(),
                nuevoCustodio.getTelefono(),
                nuevoCustodio.getCi() // Asignar el valor de CI en el DTO
        );
    }

    public List<CustodioDto> obtenerTodosLosCustodios() {
        logger.info("Obteniendo todos los custodios");

        List<CustodioEntity> custodios = custodioRepository.findAll();
        return custodios.stream().map(custodio -> new CustodioDto(
                custodio.getIdCustodio(),
                custodio.getNombre(),
                custodio.getApellidoPaterno(),
                custodio.getApellidoMaterno(),
                custodio.getCorreo(),
                custodio.getTelefono(),
                custodio.getCi() // Incluir CI en la lista de DTOs
        )).collect(Collectors.toList());
    }

    public CustodioDto obtenerCustodioPorId(Integer id) {
        logger.info("Obteniendo custodio con ID: {}", id);

        CustodioEntity custodio = custodioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Custodio no encontrado con ID: " + id));

        return new CustodioDto(
                custodio.getIdCustodio(),
                custodio.getNombre(),
                custodio.getApellidoPaterno(),
                custodio.getApellidoMaterno(),
                custodio.getCorreo(),
                custodio.getTelefono(),
                custodio.getCi() // Incluir CI en el DTO
        );
    }

    public CustodioDto actualizarCustodio(Integer id, CustodioDto custodioDto) {
        logger.info("Actualizando custodio con ID: {}", id);

        CustodioEntity custodioEntity = custodioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Custodio no encontrado con ID: " + id));

        custodioEntity.setNombre(custodioDto.getNombre());
        custodioEntity.setApellidoPaterno(custodioDto.getApellidoPaterno());
        custodioEntity.setApellidoMaterno(custodioDto.getApellidoMaterno());
        custodioEntity.setCorreo(custodioDto.getCorreo());
        custodioEntity.setTelefono(custodioDto.getTelefono());
        custodioEntity.setCi(custodioDto.getCi()); // Actualizar el valor de CI en la entidad

        CustodioEntity custodioActualizado = custodioRepository.save(custodioEntity);

        return new CustodioDto(
                custodioActualizado.getIdCustodio(),
                custodioActualizado.getNombre(),
                custodioActualizado.getApellidoPaterno(),
                custodioActualizado.getApellidoMaterno(),
                custodioActualizado.getCorreo(),
                custodioActualizado.getTelefono(),
                custodioActualizado.getCi() // Asignar el valor de CI en el DTO actualizado
        );
    }

    public void eliminarCustodio(Integer id) {
        logger.info("Eliminando custodio con ID: {}", id);

        if (!custodioRepository.existsById(id)) {
            throw new RuntimeException("Custodio no encontrado con ID: " + id);
        }

        custodioRepository.deleteById(id);
        logger.info("Custodio con ID {} eliminado", id);
    }
}
