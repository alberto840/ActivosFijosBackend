package com.grupod.activosfijos.bloque;

import com.grupod.activosfijos.direccion.DireccionEntity;
import com.grupod.activosfijos.direccion.DireccionRepository;
import com.grupod.activosfijos.sucursal.SucursalEntity;
import com.grupod.activosfijos.sucursal.SucursalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloqueService {

    private static final Logger logger = LoggerFactory.getLogger(BloqueService.class);
    private final BloqueRepository bloqueRepository;
    private final DireccionRepository direccionRepository;
    private final SucursalRepository sucursalRepository;

    @Autowired
    public BloqueService(BloqueRepository bloqueRepository, DireccionRepository direccionRepository, SucursalRepository sucursalRepository) {
        this.bloqueRepository = bloqueRepository;
        this.direccionRepository = direccionRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public BloqueDto crearBloque(BloqueDto bloqueDto) {
        logger.info("Creando bloque: Nombre {}", bloqueDto.getNombre());

        DireccionEntity direccionEntity = direccionRepository.findById(bloqueDto.getIdDireccion())
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + bloqueDto.getIdDireccion()));

        SucursalEntity sucursalEntity = sucursalRepository.findById(bloqueDto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + bloqueDto.getIdSucursal()));

        BloqueEntity bloqueEntity = new BloqueEntity();
        bloqueEntity.setNombre(bloqueDto.getNombre());
        bloqueEntity.setDireccionEntity(direccionEntity);
        bloqueEntity.setSucursalEntity(sucursalEntity);

        BloqueEntity nuevoBloque = bloqueRepository.save(bloqueEntity);

        logger.info("Bloque creado con ID: {}", nuevoBloque.getIdBloque());

        return new BloqueDto(
                nuevoBloque.getIdBloque(),
                nuevoBloque.getNombre(),
                nuevoBloque.getDireccionEntity().getIdDireccion(),
                nuevoBloque.getSucursalEntity().getIdSucursal()
        );
    }

    public List<BloqueDto> obtenerTodosLosBloques() {
        logger.info("Obteniendo todos los bloques");

        List<BloqueEntity> bloques = bloqueRepository.findAll();

        return bloques.stream()
                .map(bloque -> new BloqueDto(
                        bloque.getIdBloque(),
                        bloque.getNombre(),
                        bloque.getDireccionEntity().getIdDireccion(),
                        bloque.getSucursalEntity().getIdSucursal()
                ))
                .collect(Collectors.toList());
    }

    public BloqueDto obtenerBloquePorId(Integer id) {
        logger.info("Obteniendo bloque con ID: {}", id);
        BloqueEntity bloque = bloqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloque no encontrado"));

        return new BloqueDto(
                bloque.getIdBloque(),
                bloque.getNombre(),
                bloque.getDireccionEntity().getIdDireccion(),
                bloque.getSucursalEntity().getIdSucursal()
        );
    }

    public BloqueDto actualizarBloque(Integer id, BloqueDto bloqueDto) {
        logger.info("Actualizando bloque con ID: {}", id);

        BloqueEntity bloqueEntity = bloqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloque no encontrado"));

        DireccionEntity direccionEntity = direccionRepository.findById(bloqueDto.getIdDireccion())
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + bloqueDto.getIdDireccion()));

        SucursalEntity sucursalEntity = sucursalRepository.findById(bloqueDto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + bloqueDto.getIdSucursal()));

        bloqueEntity.setNombre(bloqueDto.getNombre());
        bloqueEntity.setDireccionEntity(direccionEntity);
        bloqueEntity.setSucursalEntity(sucursalEntity);

        BloqueEntity bloqueActualizado = bloqueRepository.save(bloqueEntity);

        return new BloqueDto(
                bloqueActualizado.getIdBloque(),
                bloqueActualizado.getNombre(),
                bloqueActualizado.getDireccionEntity().getIdDireccion(),
                bloqueActualizado.getSucursalEntity().getIdSucursal()
        );
    }

    public void eliminarBloque(Integer id) {
        logger.info("Eliminando bloque con ID: {}", id);

        if (!bloqueRepository.existsById(id)) {
            throw new RuntimeException("Bloque no encontrado");
        }

        bloqueRepository.deleteById(id);
    }
}
