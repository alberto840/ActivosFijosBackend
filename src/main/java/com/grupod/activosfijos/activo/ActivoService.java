package com.grupod.activosfijos.activo;

import com.grupod.activosfijos.aula.AulaEntity;
import com.grupod.activosfijos.aula.AulaRepository;
import com.grupod.activosfijos.bloque.BloqueEntity;
import com.grupod.activosfijos.bloque.BloqueRepository;
import com.grupod.activosfijos.categoria.CategoriaEntity;
import com.grupod.activosfijos.custodio.CustodioEntity;
import com.grupod.activosfijos.custodio.CustodioRepository;
import com.grupod.activosfijos.proyecto.ProyectoEntity;
import com.grupod.activosfijos.proyecto.ProyectoRepository;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivoService {

    private static final Logger logger = LoggerFactory.getLogger(ActivoService.class);
    private final ActivoRepository activoRepository;
    private final AulaRepository aulaRepository;
    private final BloqueRepository bloqueRepository;
    private final CustodioRepository custodioRepository;
    private final ProyectoRepository proyectoRepository;

    @Autowired
    public ActivoService(
            ActivoRepository activoRepository,
            AulaRepository aulaRepository,
            BloqueRepository bloqueRepository,
            CustodioRepository custodioRepository,
            ProyectoRepository proyectoRepository) {
        this.activoRepository = activoRepository;
        this.aulaRepository = aulaRepository;
        this.bloqueRepository = bloqueRepository;
        this.custodioRepository = custodioRepository;
        this.proyectoRepository = proyectoRepository;
    }

    public ActivoDto crearActivo(ActivoDto activoDto) {
        logger.info("Creando nuevo activo: {}", activoDto.getNombre());
        ActivoEntity activoEntity = convertirDtoAEntidad(activoDto);

        if (activoDto.getAulaId() != null) {
            AulaEntity aula = aulaRepository.findById(activoDto.getAulaId())
                    .orElseThrow(() -> new RuntimeException("Aula no encontrada con ID: " + activoDto.getAulaId()));
            activoEntity.setAulaEntity(aula);
        } else if (activoDto.getCodigoUbicacion() != null) {
            AulaEntity aula = aulaRepository.findByCodigoUbicacion(activoDto.getCodigoUbicacion())
                    .orElseThrow(() -> new RuntimeException("Aula no encontrada con código de ubicación: " + activoDto.getCodigoUbicacion()));
            activoEntity.setAulaEntity(aula);
        } else {
            throw new IllegalArgumentException("Debe proporcionar un `aulaId` o un `codigoUbicacion`.");
        }

        if (activoDto.getCustodioId() != null) {
            CustodioEntity custodio = custodioRepository.findById(activoDto.getCustodioId())
                    .orElseThrow(() -> new RuntimeException("Custodio no encontrado con ID: " + activoDto.getCustodioId()));
            activoEntity.setCustodioEntity(custodio);
        } else if (activoDto.getCiCustodio() != null) {
            CustodioEntity custodio = custodioRepository.findByCi(activoDto.getCiCustodio())
                    .orElseThrow(() -> new RuntimeException("Custodio no encontrado con CI: " + activoDto.getCiCustodio()));
            activoEntity.setCustodioEntity(custodio);
        } else {
            throw new IllegalArgumentException("Debe proporcionar un `custodioId` o un `ciCustodio`.");
        }

        if (activoDto.getProyectoId() != null) {
            ProyectoEntity proyecto = proyectoRepository.findById(activoDto.getProyectoId())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + activoDto.getProyectoId()));
            activoEntity.setProyectoEntity(proyecto);
        } else if (activoDto.getCodigoProyecto() != null) {
            ProyectoEntity proyecto = proyectoRepository.findByCodigoProyecto(activoDto.getCodigoProyecto())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con código: " + activoDto.getCodigoProyecto()));
            activoEntity.setProyectoEntity(proyecto);
        } else {
            throw new IllegalArgumentException("Debe proporcionar un `proyectoId` o un `codigoProyecto`.");
        }

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
        activoEntity.setEstado(activoDto.isEstado());
        activoEntity.setPrecio(activoDto.getPrecio());
        activoEntity.setComprobanteCompra(activoDto.getComprobanteCompra());
        activoEntity.setEstadoActivo(activoDto.getEstadoActivo());

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

    private void actualizarRelaciones(ActivoEntity activoEntity, ActivoDto activoDto) {
        if (activoDto.getAulaId() != null) {
            AulaEntity aula = aulaRepository.findById(activoDto.getAulaId()).orElse(null);
            activoEntity.setAulaEntity(aula);
        }

        if (activoDto.getCategoriaId() != null) {
            CategoriaEntity categoria = new CategoriaEntity();
            categoria.setIdCategoria(activoDto.getCategoriaId());
            activoEntity.setCategoriaEntity(categoria);
        }

        if (activoDto.getCustodioId() != null) {
            CustodioEntity custodio = new CustodioEntity();
            custodio.setIdCustodio(activoDto.getCustodioId());
            activoEntity.setCustodioEntity(custodio);
        }

        if (activoDto.getProyectoId() != null) {
            ProyectoEntity proyecto = new ProyectoEntity();
            proyecto.setIdProyecto(activoDto.getProyectoId());
            activoEntity.setProyectoEntity(proyecto);
        }
    }

    private ActivoEntity convertirDtoAEntidad(ActivoDto activoDto) {
        ActivoEntity activoEntity = new ActivoEntity();
        activoEntity.setNombre(activoDto.getNombre());
        activoEntity.setValorActual(activoDto.getValorActual());
        activoEntity.setValorInicial(activoDto.getValorInicial());
        activoEntity.setFechaRegistro(activoDto.getFechaRegistro());
        activoEntity.setDetalle(activoDto.getDetalle());
        activoEntity.setEstado(activoDto.isEstado());
        activoEntity.setPrecio(activoDto.getPrecio());
        activoEntity.setComprobanteCompra(activoDto.getComprobanteCompra());
        activoEntity.setEstadoActivo(activoDto.getEstadoActivo());

        actualizarRelaciones(activoEntity, activoDto);

        return activoEntity;
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
                activoEntity.getEstadoActivo(),
                activoEntity.getAulaEntity() != null ? activoEntity.getAulaEntity().getIdAula() : null,
                activoEntity.getCategoriaEntity() != null ? activoEntity.getCategoriaEntity().getIdCategoria() : null,
                activoEntity.getCustodioEntity() != null ? activoEntity.getCustodioEntity().getIdCustodio() : null,
                activoEntity.getProyectoEntity() != null ? activoEntity.getProyectoEntity().getIdProyecto() : null,
                activoEntity.getAulaEntity() != null ? activoEntity.getAulaEntity().getCodigoUbicacion() : null,
                activoEntity.getCustodioEntity() != null ? activoEntity.getCustodioEntity().getCi() : null,
                activoEntity.getProyectoEntity() != null ? activoEntity.getProyectoEntity().getCodigoProyecto() : null
        );
    }

    public List<UbicacionDto> obtenerUbicacionesDeTodosLosActivos() {
        List<ActivoEntity> activos = activoRepository.findAll();
        return activos.stream()
                .map(activo -> obtenerUbicacionCompletaPorIdActivo(activo.getIdActivo()))
                .filter(ubicacion -> ubicacion != null)
                .collect(Collectors.toList());
    }

    public UbicacionDto obtenerUbicacionCompletaPorIdActivo(Integer idActivo) {
        ActivoEntity activoEntity = activoRepository.findById(idActivo).orElse(null);
        if (activoEntity == null) {
            logger.warn("Activo con ID {} no encontrado", idActivo);
            return null;
        }

        AulaEntity aula = activoEntity.getAulaEntity();
        if (aula == null || aula.getBloqueEntity() == null) return null;

        BloqueEntity bloque = aula.getBloqueEntity();
        if (bloque.getDireccionEntity() == null || bloque.getSucursalEntity() == null) return null;

        return new UbicacionDto(
                aula.getNombre(),
                bloque.getNombre(),
                bloque.getDireccionEntity().getCalle(),
                bloque.getDireccionEntity().getDetalle(),
                bloque.getDireccionEntity().getZona(),
                bloque.getSucursalEntity().getMunicipioEntity().getNombre(),
                bloque.getSucursalEntity().getMunicipioEntity().getProvinciaId().getNombre(),
                bloque.getSucursalEntity().getMunicipioEntity().getProvinciaId().getDepartamentoId().getNombre(),
                bloque.getSucursalEntity().getMunicipioEntity().getProvinciaId().getDepartamentoId().getPaisEntity().getNombre(),
                bloque.getSucursalEntity().getNombre()
        );
    }

    public List<ActivoDto> cargarActivosMasivos(MultipartFile file) throws Exception {
        List<ActivoDto> activosCargados = new ArrayList<>();

        String fileType = file.getContentType();
        if ("text/csv".equalsIgnoreCase(fileType)) {
            activosCargados = leerActivosDesdeCSV(file);
        } else if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equalsIgnoreCase(fileType)) {
            activosCargados = leerActivosDesdeExcel(file);
        } else {
            throw new IllegalArgumentException("Formato de archivo no compatible");
        }

        List<ActivoEntity> activosEntity = activosCargados.stream()
                .map(this::convertirDtoAEntidad)
                .collect(Collectors.toList());
        activoRepository.saveAll(activosEntity);

        return activosCargados;
    }

    private List<ActivoDto> leerActivosDesdeCSV(MultipartFile file) throws Exception {
        List<ActivoDto> activos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(br)) {
            String[] values;
            csvReader.readNext(); // Salta el encabezado
            while ((values = csvReader.readNext()) != null) {
                ActivoDto activo = new ActivoDto();
                activo.setNombre(values[0]);
                activo.setValorActual(new BigDecimal(values[1]));
                activo.setValorInicial(new BigDecimal(values[2]));
                activo.setFechaRegistro(new SimpleDateFormat("yyyy-MM-dd").parse(values[3]));
                activo.setDetalle(values[4]);
                activo.setEstado(Boolean.parseBoolean(values[5]));
                activo.setPrecio(new BigDecimal(values[6]));
                activo.setComprobanteCompra(values[7]);
                activo.setEstadoActivo(values[8]);
                activo.setCategoriaId(Integer.parseInt(values[9]));

                // Código de ubicación, CI de custodio, y código de proyecto
                final String codigoUbicacion = values[10];
                final String ciCustodio = values[11];
                final String codigoProyecto = values[12];

                // Cargar Aula por código
                if (codigoUbicacion != null && !codigoUbicacion.isEmpty()) {
                    AulaEntity aula = aulaRepository.findByCodigoUbicacion(codigoUbicacion)
                            .orElseThrow(() -> new RuntimeException("Aula no encontrada con código: " + codigoUbicacion));
                    activo.setAulaId(aula.getIdAula());
                }

                // Cargar Custodio por CI
                if (ciCustodio != null && !ciCustodio.isEmpty()) {
                    CustodioEntity custodio = custodioRepository.findByCi(ciCustodio)
                            .orElseThrow(() -> new RuntimeException("Custodio no encontrado con CI: " + ciCustodio));
                    activo.setCustodioId(custodio.getIdCustodio());
                }

                // Cargar Proyecto por código
                if (codigoProyecto != null && !codigoProyecto.isEmpty()) {
                    ProyectoEntity proyecto = proyectoRepository.findByCodigoProyecto(codigoProyecto)
                            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con código: " + codigoProyecto));
                    activo.setProyectoId(proyecto.getIdProyecto());
                }

                activos.add(activo);
            }
        }
        return activos;
    }




    private List<ActivoDto> leerActivosDesdeExcel(MultipartFile file) throws Exception {
        List<ActivoDto> activos = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Salta el encabezado

                ActivoDto activo = new ActivoDto();
                activo.setNombre(row.getCell(0).getStringCellValue());
                activo.setValorActual(new BigDecimal(row.getCell(1).getNumericCellValue()));
                activo.setValorInicial(new BigDecimal(row.getCell(2).getNumericCellValue()));
                activo.setFechaRegistro(row.getCell(3).getDateCellValue());
                activo.setDetalle(row.getCell(4).getStringCellValue());
                activo.setEstado(row.getCell(5).getBooleanCellValue());
                activo.setPrecio(new BigDecimal(row.getCell(6).getNumericCellValue()));
                activo.setComprobanteCompra(row.getCell(7).getStringCellValue());
                activo.setEstadoActivo(row.getCell(8).getStringCellValue());

                final String codigoUbicacion = row.getCell(9) != null ? row.getCell(9).getStringCellValue() : null;
                final String ciCustodio = row.getCell(10) != null ? row.getCell(10).getStringCellValue() : null;
                final String codigoProyecto = row.getCell(11) != null ? row.getCell(11).getStringCellValue() : null;

                // Para Aula
                if (codigoUbicacion != null && !codigoUbicacion.isEmpty()) {
                    logger.info("Buscando Aula con código de ubicación: " + codigoUbicacion);
                    AulaEntity aula = aulaRepository.findByCodigoUbicacion(codigoUbicacion)
                            .orElseThrow(() -> new RuntimeException("Aula no encontrada con código: " + codigoUbicacion));
                    logger.info("Aula encontrada: " + aula.getNombre());
                    activo.setAulaId(aula.getIdAula());
                }

                // Para Custodio
                if (ciCustodio != null && !ciCustodio.isEmpty()) {
                    logger.info("Buscando Custodio con CI: " + ciCustodio);
                    CustodioEntity custodio = custodioRepository.findByCi(ciCustodio)
                            .orElseThrow(() -> new RuntimeException("Custodio no encontrado con CI: " + ciCustodio));
                    logger.info("Custodio encontrado: " + custodio.getNombre());
                    activo.setCustodioId(custodio.getIdCustodio());
                }

                // Para Proyecto
                if (codigoProyecto != null && !codigoProyecto.isEmpty()) {
                    logger.info("Buscando Proyecto con código: " + codigoProyecto);
                    ProyectoEntity proyecto = proyectoRepository.findByCodigoProyecto(codigoProyecto)
                            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con código: " + codigoProyecto));
                    logger.info("Proyecto encontrado: " + proyecto.getNombre());
                    activo.setProyectoId(proyecto.getIdProyecto());
                }

                activos.add(activo);
            }
        }
        return activos;
    }

}
