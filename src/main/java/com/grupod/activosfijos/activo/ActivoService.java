package com.grupod.activosfijos.activo;



import com.grupod.activosfijos.aula.AulaEntity;
import com.grupod.activosfijos.aula.AulaRepository;
import com.grupod.activosfijos.bloque.BloqueEntity;
import com.grupod.activosfijos.bloque.BloqueRepository;
import com.grupod.activosfijos.categoria.CategoriaEntity;
import com.grupod.activosfijos.custodio.CustodioEntity;
import com.grupod.activosfijos.departamento.DepartamentoEntity;
import com.grupod.activosfijos.departamento.DepartamentoRepository;
import com.grupod.activosfijos.depreciacion.DepreciacionEntity;
import com.grupod.activosfijos.direccion.DireccionEntity;
import com.grupod.activosfijos.direccion.DireccionRepository;
import com.grupod.activosfijos.estadoActivo.EstadoactivoEntity;
import com.grupod.activosfijos.modelo.ModeloEntity;
import com.grupod.activosfijos.municipio.MunicipioEntity;
import com.grupod.activosfijos.municipio.MunicipioRepository;
import com.grupod.activosfijos.pais.PaisEntity;
import com.grupod.activosfijos.pais.PaisRepository;
import com.grupod.activosfijos.provincia.ProvinciaEntity;
import com.grupod.activosfijos.provincia.ProvinciaRepository;
import com.grupod.activosfijos.proyecto.ProyectoEntity;
import com.grupod.activosfijos.sucursal.SucursalEntity;
import com.grupod.activosfijos.sucursal.SucursalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivoService {

    private static final Logger logger = LoggerFactory.getLogger(ActivoService.class);
    private final ActivoRepository activoRepository;
    private final AulaRepository aulaRepository;
    private final BloqueRepository bloqueRepository;
    private final DireccionRepository direccionRepository;
    private final SucursalRepository sucursalRepository;
    private final MunicipioRepository municipioRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final PaisRepository paisRepository;

    @Autowired
    public ActivoService(
            ActivoRepository activoRepository,
            AulaRepository aulaRepository,
            BloqueRepository bloqueRepository,
            DireccionRepository direccionRepository,
            SucursalRepository sucursalRepository,
            MunicipioRepository municipioRepository,
            ProvinciaRepository provinciaRepository,
            DepartamentoRepository departamentoRepository,
            PaisRepository paisRepository) {
        this.activoRepository = activoRepository;
        this.aulaRepository = aulaRepository;
        this.bloqueRepository = bloqueRepository;
        this.direccionRepository = direccionRepository;
        this.sucursalRepository = sucursalRepository;
        this.municipioRepository = municipioRepository;
        this.provinciaRepository = provinciaRepository;
        this.departamentoRepository = departamentoRepository;
        this.paisRepository = paisRepository;
    }

    public ActivoDto crearActivo(ActivoDto activoDto) {
        logger.info("Creando nuevo activo: {}", activoDto.getNombre());
        ActivoEntity activoEntity = convertirDtoAEntidad(activoDto);
        ActivoEntity nuevoActivo = activoRepository.save(activoEntity);
        return convertirEntidadADto(nuevoActivo);
    }

    public List<ActivoDto> obtenerTodosLosActivos() {
        logger.info("Obteniendo todos los activos");
        List<ActivoEntity> activos = activoRepository.findAll();
        return activos.stream().map(this::convertirEntidadADto).collect(Collectors.toList());
    }

    public ActivoDto obtenerActivoPorId(Integer id) {
        logger.info("Obteniendo activo con ID: {}", id);
        Optional<ActivoEntity> activoOpt = activoRepository.findById(id);
        return activoOpt.map(this::convertirEntidadADto).orElse(null);
    }

    public ActivoDto actualizarActivo(Integer id, ActivoDto activoDto) {
        logger.info("Actualizando activo con ID: {}", id);
        Optional<ActivoEntity> activoOpt = activoRepository.findById(id);
        if (activoOpt.isEmpty()) {
            logger.warn("Activo con ID {} no encontrado", id);
            return null;
        }

        ActivoEntity activoEntity = activoOpt.get();
        activoEntity.setNombre(activoDto.getNombre());
        activoEntity.setValorActual(activoDto.getValorActual());
        activoEntity.setValorInicial(activoDto.getValorInicial());
        activoEntity.setFechaRegistro(activoDto.getFechaRegistro());
        activoEntity.setDetalle(activoDto.getDetalle());
        activoEntity.setEstado(activoDto.getEstado());
        activoEntity.setPrecio(activoDto.getPrecio());
        activoEntity.setComprobanteCompra(activoDto.getComprobanteCompra());

        // Actualizar las relaciones con otras entidades si se proporcionan los IDs
        actualizarRelaciones(activoEntity, activoDto);

        ActivoEntity activoActualizado = activoRepository.save(activoEntity);
        return convertirEntidadADto(activoActualizado);
    }

    public boolean eliminarActivo(Integer id) {
        logger.info("Eliminando activo con ID: {}", id);
        if (!activoRepository.existsById(id)) {
            logger.warn("Activo con ID {} no encontrado", id);
            return false;
        }
        activoRepository.deleteById(id);
        return true;
    }

    public UbicacionDto obtenerUbicacionCompletaPorIdActivo(Integer idActivo) {
        ActivoEntity activoEntity = activoRepository.findById(idActivo).orElse(null);
        if (activoEntity == null) {
            logger.warn("Activo con ID {} no encontrado", idActivo);
            return null;
        }

        ActivoDto activoDto = convertirEntidadADto(activoEntity);

        // Obtención de relaciones utilizando las entidades correctas y sus métodos
        AulaEntity aula = aulaRepository.findById(activoDto.getIdAula()).orElse(null);
        if (aula == null || aula.getBloqueEntity() == null) return null;

        BloqueEntity bloque = aula.getBloqueEntity();
        if (bloque.getDireccionEntity() == null || bloque.getSucursalEntity() == null) return null;

        DireccionEntity direccion = bloque.getDireccionEntity();
        SucursalEntity sucursal = bloque.getSucursalEntity();

        MunicipioEntity municipio = sucursal.getMunicipioEntity();
        if (municipio == null || municipio.getProvinciaId() == null) return null;

        ProvinciaEntity provincia = municipio.getProvinciaId();
        if (provincia.getDepartamentoId() == null) return null;

        DepartamentoEntity departamento = provincia.getDepartamentoId();
        PaisEntity pais = departamento.getPaisEntity();

        return new UbicacionDto(
                aula.getNombre(),
                bloque.getNombre(),
                direccion.getCalle(),
                direccion.getDetalle(),
                direccion.getZona(),
                municipio.getNombre(),
                provincia.getNombre(),
                departamento.getNombre(),
                pais != null ? pais.getNombre() : "N/A",
                sucursal.getNombre()
        );
    }

    private void actualizarRelaciones(ActivoEntity activoEntity, ActivoDto activoDto) {
        if (activoDto.getIdAula() != null) {
            AulaEntity aula = aulaRepository.findById(activoDto.getIdAula()).orElse(null);
            activoEntity.setAulaEntity(aula);
        }

        if (activoDto.getIdBloque() != null) {
            BloqueEntity bloque = bloqueRepository.findById(activoDto.getIdBloque()).orElse(null);
            activoEntity.setBloqueEntity(bloque);
        }

        if (activoDto.getIdCategoria() != null) {
            CategoriaEntity categoria = new CategoriaEntity();
            categoria.setIdCategoria(activoDto.getIdCategoria());
            activoEntity.setCategoriaEntity(categoria);
        }

        if (activoDto.getIdCustodio() != null) {
            CustodioEntity custodio = new CustodioEntity();
            custodio.setIdCustodio(activoDto.getIdCustodio());
            activoEntity.setCustodioEntity(custodio);
        }

        if (activoDto.getIdDepreciacion() != null) {
            DepreciacionEntity depreciacion = new DepreciacionEntity();
            depreciacion.setIdDepreciacion(activoDto.getIdDepreciacion());
            activoEntity.setDepreciacionEntity(depreciacion);
        }

        if (activoDto.getIdEstadoactivo() != null) {
            EstadoactivoEntity estadoactivo = new EstadoactivoEntity();
            estadoactivo.setIdEstado(activoDto.getIdEstadoactivo());
            activoEntity.setEstadoactivoEntity(estadoactivo);
        }

        if (activoDto.getIdProyecto() != null) {
            ProyectoEntity proyecto = new ProyectoEntity();
            proyecto.setIdProyecto(activoDto.getIdProyecto());
            activoEntity.setProyectoEntity(proyecto);
        }

        if (activoDto.getIdModelo() != null) {
            ModeloEntity modelo = new ModeloEntity();
            modelo.setIdModelo(activoDto.getIdModelo());
            activoEntity.setModeloEntity(modelo);
        }
    }

    // Conversión de Dto a Entidad y Entidad a Dto
    private ActivoEntity convertirDtoAEntidad(ActivoDto activoDto) {
        ActivoEntity activoEntity = new ActivoEntity();
        activoEntity.setNombre(activoDto.getNombre());
        activoEntity.setValorActual(activoDto.getValorActual());
        activoEntity.setValorInicial(activoDto.getValorInicial());
        activoEntity.setFechaRegistro(activoDto.getFechaRegistro());
        activoEntity.setDetalle(activoDto.getDetalle());
        activoEntity.setEstado(activoDto.getEstado());
        activoEntity.setPrecio(activoDto.getPrecio());
        activoEntity.setComprobanteCompra(activoDto.getComprobanteCompra());

        // Asignar las entidades relacionadas si los IDs no son nulos
        actualizarRelaciones(activoEntity, activoDto);

        return activoEntity;
    }

    public List<UbicacionDto> obtenerUbicacionesDeTodosLosActivos() {
        List<ActivoEntity> activos = activoRepository.findAll();
        return activos.stream()
                .map(activo -> obtenerUbicacionCompletaPorIdActivo(activo.getIdActivo()))
                .filter(ubicacion -> ubicacion != null)  // Filtrar activos sin ubicación
                .collect(Collectors.toList());
    }

    private ActivoDto convertirEntidadADto(ActivoEntity activoEntity) {
        return new ActivoDto(
                activoEntity.getIdActivo(),
                activoEntity.getNombre(),
                activoEntity.getValorActual(),
                activoEntity.getValorInicial(),
                activoEntity.getFechaRegistro(),
                activoEntity.getDetalle(),
                activoEntity.isEstado(),
                activoEntity.getPrecio(),
                activoEntity.getComprobanteCompra(),
                activoEntity.getAulaEntity() != null ? activoEntity.getAulaEntity().getIdAula() : null,
                activoEntity.getBloqueEntity() != null ? activoEntity.getBloqueEntity().getIdBloque() : null,
                activoEntity.getCategoriaEntity() != null ? activoEntity.getCategoriaEntity().getIdCategoria() : null,
                activoEntity.getCustodioEntity() != null ? activoEntity.getCustodioEntity().getIdCustodio() : null,
                activoEntity.getDepreciacionEntity() != null ? activoEntity.getDepreciacionEntity().getIdDepreciacion() : null,
                activoEntity.getEstadoactivoEntity() != null ? activoEntity.getEstadoactivoEntity().getIdEstado() : null,
                activoEntity.getProyectoEntity() != null ? activoEntity.getProyectoEntity().getIdProyecto() : null,
                activoEntity.getModeloEntity() != null ? activoEntity.getModeloEntity().getIdModelo() : null
        );
    }
}
