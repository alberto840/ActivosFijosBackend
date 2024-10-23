package com.grupod.activosfijos.historialActivos;

import com.grupod.activosfijos.activo.ActivoEntity;
import com.grupod.activosfijos.activo.ActivoRepository;
import com.grupod.activosfijos.aula.AulaEntity;
import com.grupod.activosfijos.aula.AulaRepository;
import com.grupod.activosfijos.custodio.CustodioEntity;
import com.grupod.activosfijos.custodio.CustodioRepository;
import com.grupod.activosfijos.proyecto.ProyectoEntity;
import com.grupod.activosfijos.proyecto.ProyectoRepository;
import com.grupod.activosfijos.usuario.UsuarioEntity;
import com.grupod.activosfijos.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialACtivosService {

    private static final Logger logger = LoggerFactory.getLogger(HistorialACtivosService.class);
    private final HistorialActivosRepository historialActivosRepository;
    private final UsuarioRepository usuarioRepository;
    private final ActivoRepository activoRepository;
    private final AulaRepository aulaRepository;
    private final CustodioRepository custodioRepository;
    private final ProyectoRepository proyectoRepository;

    @Autowired
    public HistorialACtivosService(
            HistorialActivosRepository historialActivosRepository,
            UsuarioRepository usuarioRepository,
            ActivoRepository activoRepository,
            AulaRepository aulaRepository,
            CustodioRepository custodioRepository,
            ProyectoRepository proyectoRepository) {
        this.historialActivosRepository = historialActivosRepository;
        this.usuarioRepository = usuarioRepository;
        this.activoRepository = activoRepository;
        this.aulaRepository = aulaRepository;
        this.custodioRepository = custodioRepository;
        this.proyectoRepository = proyectoRepository;
    }

    public HistorialActivosDto crearHistorial(HistorialActivosDto historialDto) {
        logger.info("Creando nuevo historial: {}", historialDto.getAccion());

        UsuarioEntity usuarioEntity = usuarioRepository.findById(historialDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + historialDto.getIdUsuario()));

        HistorialActivosEntity historialEntity = convertirDtoAEntidad(historialDto);
        historialEntity.setUsuarioEntity(usuarioEntity);

        HistorialActivosEntity nuevoHistorial = historialActivosRepository.save(historialEntity);
        return convertirEntidadADto(nuevoHistorial);
    }

    public List<HistorialActivosDto> obtenerTodosLosHistoriales() {
        logger.info("Obteniendo todos los historiales");
        List<HistorialActivosEntity> historiales = historialActivosRepository.findAll();
        return historiales.stream()
                .map(this::convertirEntidadADto)
                .collect(Collectors.toList());
    }

    public HistorialActivosDto obtenerHistorialPorId(Integer id) {
        logger.info("Obteniendo historial con ID: {}", id);
        HistorialActivosEntity historial = historialActivosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado con ID: " + id));
        return convertirEntidadADto(historial);
    }

    public HistorialActivosDto actualizarHistorial(Integer id, HistorialActivosDto historialDto) {
        logger.info("Actualizando historial con ID: {}", id);
        HistorialActivosEntity historialEntity = historialActivosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado con ID: " + id));

        historialEntity.setAccion(historialDto.getAccion());
        historialEntity.setValorActual(historialDto.getValorActual());
        historialEntity.setFechaModificacion(historialDto.getFechaModificacion());
        historialEntity.setComprobante(historialDto.getComprobante());
        historialEntity.setDetalle(historialDto.getDetalle());
        historialEntity.setEstado(historialDto.isEstado());
        historialEntity.setEstadoUso(historialDto.getEstadoUso());

        asignarEntidadesRelacionadas(historialEntity, historialDto);

        HistorialActivosEntity historialActualizado = historialActivosRepository.save(historialEntity);
        return convertirEntidadADto(historialActualizado);
    }

    public void eliminarHistorial(Integer id) {
        logger.info("Eliminando historial con ID: {}", id);
        if (!historialActivosRepository.existsById(id)) {
            throw new RuntimeException("Historial no encontrado con ID: " + id);
        }
        historialActivosRepository.deleteById(id);
    }

    private HistorialActivosEntity convertirDtoAEntidad(HistorialActivosDto historialDto) {
        HistorialActivosEntity historialEntity = new HistorialActivosEntity();
        historialEntity.setAccion(historialDto.getAccion());
        historialEntity.setValorActual(historialDto.getValorActual());
        historialEntity.setFechaModificacion(historialDto.getFechaModificacion());
        historialEntity.setComprobante(historialDto.getComprobante());
        historialEntity.setDetalle(historialDto.getDetalle());
        historialEntity.setEstado(historialDto.isEstado());
        historialEntity.setEstadoUso(historialDto.getEstadoUso());

        asignarEntidadesRelacionadas(historialEntity, historialDto);

        return historialEntity;
    }

    private void asignarEntidadesRelacionadas(HistorialActivosEntity historialEntity, HistorialActivosDto historialDto) {
        if (historialDto.getIdActivo() != null) {
            ActivoEntity activoEntity = activoRepository.findById(historialDto.getIdActivo())
                    .orElseThrow(() -> new RuntimeException("Activo no encontrado con ID: " + historialDto.getIdActivo()));
            historialEntity.setActivoEntity(activoEntity);
        }

        if (historialDto.getIdAula() != null) {
            AulaEntity aulaEntity = aulaRepository.findById(historialDto.getIdAula())
                    .orElseThrow(() -> new RuntimeException("Aula no encontrada con ID: " + historialDto.getIdAula()));
            historialEntity.setAulaEntity(aulaEntity);
        }

        if (historialDto.getIdCustodio() != null) {
            CustodioEntity custodioEntity = custodioRepository.findById(historialDto.getIdCustodio())
                    .orElseThrow(() -> new RuntimeException("Custodio no encontrado con ID: " + historialDto.getIdCustodio()));
            historialEntity.setCustodioEntity(custodioEntity);
        }

        if (historialDto.getIdProyecto() != null) {
            ProyectoEntity proyectoEntity = proyectoRepository.findById(historialDto.getIdProyecto())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + historialDto.getIdProyecto()));
            historialEntity.setProyectoEntity(proyectoEntity);
        }
    }

    private HistorialActivosDto convertirEntidadADto(HistorialActivosEntity historialEntity) {
        return new HistorialActivosDto(
                historialEntity.getIdHistorial(),
                historialEntity.getAccion(),
                historialEntity.getValorActual(),
                historialEntity.getFechaModificacion(),
                historialEntity.getComprobante(),
                historialEntity.getDetalle(),
                historialEntity.isEstado(),
                historialEntity.getEstadoUso(),
                historialEntity.getActivoEntity() != null ? historialEntity.getActivoEntity().getIdActivo() : null,
                historialEntity.getAulaEntity() != null ? historialEntity.getAulaEntity().getIdAula() : null,
                historialEntity.getCustodioEntity() != null ? historialEntity.getCustodioEntity().getIdCustodio() : null,
                historialEntity.getProyectoEntity() != null ? historialEntity.getProyectoEntity().getIdProyecto() : null,
                historialEntity.getUsuarioEntity() != null ? historialEntity.getUsuarioEntity().getIdUsuario() : null
        );
    }
}
