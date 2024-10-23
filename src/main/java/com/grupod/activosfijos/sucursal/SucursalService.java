package com.grupod.activosfijos.sucursal;

import com.grupod.activosfijos.municipio.MunicipioEntity;
import com.grupod.activosfijos.municipio.MunicipioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalService {

    private static final Logger logger = LoggerFactory.getLogger(SucursalService.class);

    private final SucursalRepository sucursalRepository;
    private final MunicipioRepository municipioRepository;

    @Autowired
    public SucursalService(SucursalRepository sucursalRepository, MunicipioRepository municipioRepository) {
        this.sucursalRepository = sucursalRepository;
        this.municipioRepository = municipioRepository;
    }

    public SucursalDto crearSucursal(SucursalDto sucursalDto) {
        MunicipioEntity municipio = municipioRepository.findById(sucursalDto.getMunicipioId())
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));

        SucursalEntity sucursalEntity = new SucursalEntity();
        sucursalEntity.setNombre(sucursalDto.getNombre());
        sucursalEntity.setMunicipioEntity(municipio);

        SucursalEntity nuevaSucursal = sucursalRepository.save(sucursalEntity);

        return new SucursalDto(
                nuevaSucursal.getIdSucursal(),
                nuevaSucursal.getNombre(),
                municipio.getIdMunicipio()
        );
    }

    public List<SucursalDto> obtenerTodasLasSucursales() {
        List<SucursalEntity> sucursales = sucursalRepository.findAll();

        return sucursales.stream()
                .map(sucursal -> new SucursalDto(
                        sucursal.getIdSucursal(),
                        sucursal.getNombre(),
                        sucursal.getMunicipioEntity().getIdMunicipio()
                ))
                .collect(Collectors.toList());
    }

    public SucursalDto obtenerSucursalPorId(Integer id) {
        SucursalEntity sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        return new SucursalDto(
                sucursal.getIdSucursal(),
                sucursal.getNombre(),
                sucursal.getMunicipioEntity().getIdMunicipio()
        );
    }

    public SucursalDto actualizarSucursal(Integer id, SucursalDto sucursalDto) {
        SucursalEntity sucursalEntity = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        sucursalEntity.setNombre(sucursalDto.getNombre());
        if (sucursalDto.getMunicipioId() != null) {
            MunicipioEntity municipio = municipioRepository.findById(sucursalDto.getMunicipioId())
                    .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
            sucursalEntity.setMunicipioEntity(municipio);
        }

        SucursalEntity sucursalActualizada = sucursalRepository.save(sucursalEntity);

        return new SucursalDto(
                sucursalActualizada.getIdSucursal(),
                sucursalActualizada.getNombre(),
                sucursalActualizada.getMunicipioEntity().getIdMunicipio()
        );
    }

    public void eliminarSucursal(Integer id) {
        if (!sucursalRepository.existsById(id)) {
            throw new RuntimeException("Sucursal no encontrada");
        }

        sucursalRepository.deleteById(id);
    }
}
