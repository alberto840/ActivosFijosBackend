package com.grupod.activosfijos.municipio;

import com.grupod.activosfijos.provincia.ProvinciaEntity;
import com.grupod.activosfijos.provincia.ProvinciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    private static final Logger logger = LoggerFactory.getLogger(MunicipioService.class);

    private final MunicipioRepository municipioRepository;
    private final ProvinciaRepository provinciaRepository;

    @Autowired
    public MunicipioService(MunicipioRepository municipioRepository, ProvinciaRepository provinciaRepository) {
        this.municipioRepository = municipioRepository;
        this.provinciaRepository = provinciaRepository;
    }

    public MunicipioDto crearMunicipio(MunicipioDto municipioDto) {
        logger.info("Creando municipio con nombre: {} y provinciaId: {}", municipioDto.getNombre(), municipioDto.getProvinciaId());

        if (municipioDto.getProvinciaId() == null) {
            throw new IllegalArgumentException("El id de la provincia no puede ser nulo");
        }

        ProvinciaEntity provincia = provinciaRepository.findById(municipioDto.getProvinciaId())
                .orElseThrow(() -> new RuntimeException("Provincia no encontrada con id: " + municipioDto.getProvinciaId()));

        MunicipioEntity municipioEntity = new MunicipioEntity();
        municipioEntity.setNombre(municipioDto.getNombre());
        municipioEntity.setProvinciaId(provincia);

        MunicipioEntity nuevoMunicipio = municipioRepository.save(municipioEntity);

        logger.info("Municipio creado con ID: {}", nuevoMunicipio.getIdMunicipio());

        return new MunicipioDto(
                nuevoMunicipio.getIdMunicipio(),
                nuevoMunicipio.getNombre(),
                nuevoMunicipio.getProvinciaId().getIdProvincia()
        );
    }


    public List<MunicipioDto> obtenerTodosLosMunicipios() {
        List<MunicipioEntity> municipios = municipioRepository.findAll();

        return municipios.stream()
                .map(municipio -> new MunicipioDto(
                        municipio.getIdMunicipio(),
                        municipio.getNombre(),
                        municipio.getProvinciaId().getIdProvincia()
                ))
                .collect(Collectors.toList());
    }

    public MunicipioDto obtenerMunicipioPorId(Integer id) {
        MunicipioEntity municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));

        return new MunicipioDto(
                municipio.getIdMunicipio(),
                municipio.getNombre(),
                municipio.getProvinciaId().getIdProvincia()
        );
    }

    public MunicipioDto actualizarMunicipio(Integer id, MunicipioDto municipioDto) {
        MunicipioEntity municipioEntity = municipioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));

        municipioEntity.setNombre(municipioDto.getNombre());
        if (municipioDto.getProvinciaId() != null) {
            ProvinciaEntity provincia = provinciaRepository.findById(municipioDto.getProvinciaId())
                    .orElseThrow(() -> new RuntimeException("Provincia no encontrada"));
            municipioEntity.setProvinciaId(provincia);
        }

        MunicipioEntity municipioActualizado = municipioRepository.save(municipioEntity);

        return new MunicipioDto(
                municipioActualizado.getIdMunicipio(),
                municipioActualizado.getNombre(),
                municipioActualizado.getProvinciaId().getIdProvincia()
        );
    }

    public void eliminarMunicipio(Integer id) {
        if (!municipioRepository.existsById(id)) {
            throw new RuntimeException("Municipio no encontrado");
        }

        municipioRepository.deleteById(id);
    }
}
