package com.grupod.activosfijos.empresa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaService.class);
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaDto crearEmpresa(EmpresaDto empresaDto) {
        logger.info("Creando empresa: {}", empresaDto.getNombre());

        EmpresaEntity empresaEntity = toEntity(empresaDto);

        EmpresaEntity nuevaEmpresa = empresaRepository.save(empresaEntity);
        logger.info("Empresa creada con ID: {}", nuevaEmpresa.getIdEmpresa());

        return toDto(nuevaEmpresa);
    }

    public List<EmpresaDto> obtenerTodasLasEmpresas() {
        logger.info("Obteniendo todas las empresas");
        List<EmpresaEntity> empresas = empresaRepository.findAll();

        return empresas.stream()
                .map(this::toDto)  // Uso del método privado para convertir la entidad a DTO
                .collect(Collectors.toList());
    }

    public EmpresaDto obtenerEmpresaPorId(Integer id) {
        logger.info("Obteniendo empresa con ID: {}", id);
        EmpresaEntity empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        return toDto(empresa);
    }

    public EmpresaDto actualizarEmpresa(Integer id, EmpresaDto empresaDto) {
        logger.info("Actualizando empresa con ID: {}", id);

        EmpresaEntity empresaEntity = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        empresaEntity.setNombre(empresaDto.getNombre());
        empresaEntity.setDireccion(empresaDto.getDireccion());
        empresaEntity.setNit(empresaDto.getNit());
        empresaEntity.setTelefono(empresaDto.getTelefono());

        EmpresaEntity empresaActualizada = empresaRepository.save(empresaEntity);

        return toDto(empresaActualizada);
    }

    public void eliminarEmpresa(Integer id) {
        logger.info("Eliminando empresa con ID: {}", id);

        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa no encontrada");
        }

        empresaRepository.deleteById(id);
    }

    // Métodos privados para convertir entre Entity y DTO
    private EmpresaDto toDto(EmpresaEntity empresaEntity) {
        return new EmpresaDto(
                empresaEntity.getIdEmpresa(),
                empresaEntity.getNombre(),
                empresaEntity.getDireccion(),
                empresaEntity.getNit(),
                empresaEntity.getTelefono()
        );
    }

    private EmpresaEntity toEntity(EmpresaDto empresaDto) {
        return new EmpresaEntity(
                empresaDto.getIdEmpresa(),
                empresaDto.getNombre(),
                empresaDto.getDireccion(),
                empresaDto.getNit(),
                empresaDto.getTelefono()
        );
    }
}
