package com.digis01.MMarinCENAGAS.Controller;

import com.digis01.MMarinCENAGAS.DAO.ICantidadDAO;
import com.digis01.MMarinCENAGAS.DAO.ICantidadEmisionDAO;
import com.digis01.MMarinCENAGAS.DAO.ICargoDAO;
import com.digis01.MMarinCENAGAS.DAO.IContratoDAO;
import com.digis01.MMarinCENAGAS.DAO.IFacturaDAO;
import com.digis01.MMarinCENAGAS.DAO.INodoComercialDAO;
import com.digis01.MMarinCENAGAS.DAO.INodoDAO;
import com.digis01.MMarinCENAGAS.DAO.ITarifaConsumoDAO;
import com.digis01.MMarinCENAGAS.DAO.ITarifaDAO;
import com.digis01.MMarinCENAGAS.DAO.IUsuarioDAO;
import com.digis01.MMarinCENAGAS.DAO.IZonaDAO;
import com.digis01.MMarinCENAGAS.DAO.IZonaTarifaDAO;
import com.digis01.MMarinCENAGAS.JPA.Cantidad;
import com.digis01.MMarinCENAGAS.JPA.CantidadEmision;
import com.digis01.MMarinCENAGAS.JPA.Cargo;
import com.digis01.MMarinCENAGAS.JPA.Contrato;
import com.digis01.MMarinCENAGAS.JPA.ErrorFile;
import com.digis01.MMarinCENAGAS.JPA.Factura;
import com.digis01.MMarinCENAGAS.JPA.Nodo;
import com.digis01.MMarinCENAGAS.JPA.NodoComercial;
import com.digis01.MMarinCENAGAS.JPA.Result;
import com.digis01.MMarinCENAGAS.JPA.ResultFile;
import com.digis01.MMarinCENAGAS.JPA.Tarifa;
import com.digis01.MMarinCENAGAS.JPA.TarifaConsumo;
import com.digis01.MMarinCENAGAS.JPA.Usuario;
import com.digis01.MMarinCENAGAS.JPA.Zona;
import com.digis01.MMarinCENAGAS.JPA.ZonaTarifa;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/facturaapi")
public class FacturaRestContoller {
    
    @Autowired
    private IFacturaDAO iFacturaDAO;
    @Autowired
    private ICantidadDAO iCantidadDAO;
    @Autowired
    private ICantidadEmisionDAO iCantidadEmisionDAO;
    @Autowired
    private ICargoDAO iCargoDAO;
    @Autowired
    private IContratoDAO iContratoDAO;
    @Autowired
    private INodoComercialDAO iNodoComercialDAO;
    @Autowired
    private INodoDAO iNodoDAO;
    @Autowired
    private ITarifaConsumoDAO iTarifaConsumoDAO;
    @Autowired
    private ITarifaDAO iTarifaDAO;
    @Autowired
    private IUsuarioDAO iUsuarioDAO;
    @Autowired
    private IZonaDAO iZonaDAO;
    @Autowired
    private IZonaTarifaDAO iZonaTarifaDAO;
    
    @PostMapping("/cargamasiva")
    public ResponseEntity CargaMasiv(@RequestParam MultipartFile archivo) {
        
        Result result = new Result();
        try {
            if (archivo == null && archivo.isEmpty()) {
                result.correct = false;
                result.errorMessage = "Error es nulo o vacio";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            String root = System.getProperty("user.dir");//Obtiene la ruta donde esta el proyecto
            String path = "src/main/resources/static/archivos";
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
            String absolutePath = root + "/" + path + "/" + archivo.getName() + fecha;
            String[] split = archivo.getOriginalFilename().split("\\.");
            if (!split[split.length - 1].equals("xlsx")) {
                result.correct = false;
                result.errorMessage = "Tipo de Archivo Invalido";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
            archivo.transferTo(new File(absolutePath));
            List<Factura> facturas = this.LeerArchivo(new File(absolutePath));
            List<ErrorFile> errores = this.validaArchivoFactura(facturas);            
            if (!errores.isEmpty()) {                
                result.correct = false;
                result.object = new ResultFile("", errores);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
            result.correct = true;
            result.object = new ResultFile(absolutePath, null);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            return ResponseEntity.internalServerError().body(result);
        }
    }
    
    @PostMapping("/cargamasiva/procesar")
    public ResponseEntity ProcesarArchivo(@RequestBody ResultFile resultFile) {
        Result result = new Result();
        try {
            if ((resultFile.pathFile != null) && (!resultFile.pathFile.isEmpty())) {
                result.correct = false;
                result.errorMessage = "La ruta esta vacia";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
            List<Factura> facturas = this.LeerArchivo(new File(resultFile.pathFile));
            for (Factura factura : facturas) {
                Contrato contrato = this.iContratoDAO.findNombre(factura.Contrato.getNombre());
                if (contrato != null) {                    
                }
            }
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(result);
        }
        
    }
    
    private List<Factura> LeerArchivo(File archivo) {
        List<Factura> facturas = new ArrayList();
        try (XSSFWorkbook workbook = new XSSFWorkbook(archivo);) {
            for (Sheet sheet : workbook) {
                int rowCount = sheet.getPhysicalNumberOfRows();
                int i = 1;
                boolean bandera = true;
                while (i < rowCount && bandera) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Factura factura = new Factura();
                        factura.Contrato = new Contrato();
                        factura.Contrato.Usuario = new Usuario();
                        factura.NodoComercial = new NodoComercial();
                        factura.NodoComercial.NodoEntrega = new Nodo();
                        factura.NodoComercial.NodoRecepcion = new Nodo();
                        factura.CantidadEmision = new CantidadEmision();
                        factura.CantidadEmision.CantidadEntrega = new Cantidad();
                        factura.CantidadEmision.CantidadRecepcion = new Cantidad();
                        factura.ZonaTarifa = new ZonaTarifa();
                        factura.ZonaTarifa.ZonaExtraccion = new Zona();
                        factura.ZonaTarifa.ZonaInyeccion = new Zona();
                        factura.TarifaConsumo = new TarifaConsumo();
                        factura.TarifaConsumo.TarifaExeceso = new Tarifa();
                        factura.TarifaConsumo.TarifaUso = new Tarifa();
                        factura.Cargo = new Cargo();                        
                        factura.setFecha(row.getCell(0).getDateCellValue());
                        factura.Contrato.setNombre(row.getCell(1).toString());
                        factura.Contrato.Usuario.setNombre(row.getCell(2).toString());
                        factura.NodoComercial.NodoRecepcion.setCodigo(row.getCell(3).toString());
                        factura.NodoComercial.NodoRecepcion.setDescripcion(row.getCell(4).toString());
                        factura.NodoComercial.NodoEntrega.setCodigo(row.getCell(5).toString());
                        factura.NodoComercial.NodoEntrega.setDescripcion(row.getCell(6).toString());
                        factura.ZonaTarifa.ZonaInyeccion.setNombre(row.getCell(7).toString());
                        factura.ZonaTarifa.ZonaExtraccion.setNombre(row.getCell(8).toString());
                        factura.CantidadEmision.CantidadRecepcion.setCantidadNominada(row.getCell(9).getNumericCellValue());
                        factura.CantidadEmision.CantidadRecepcion.setCantidadAsignada(row.getCell(10).getNumericCellValue());
                        factura.CantidadEmision.CantidadEntrega.setCantidadNominada(row.getCell(11).getNumericCellValue());
                        factura.CantidadEmision.CantidadEntrega.setCantidadAsignada(row.getCell(12).getNumericCellValue());
                        factura.setGasExeceso(row.getCell(13).getNumericCellValue());
                        factura.TarifaConsumo.TarifaExeceso.setFirme(row.getCell(14).getNumericCellValue());
                        factura.TarifaConsumo.TarifaUso.setInterrumpible(row.getCell(15).getNumericCellValue());
                        factura.Cargo.setCargaUso(row.getCell(16).getNumericCellValue());
                        factura.Cargo.setCargaExeceso(row.getCell(17).getNumericCellValue());
                        factura.setTotalFactura(row.getCell(18).getNumericCellValue());
                        facturas.add(factura);
                    } else {
                        bandera = false;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            facturas = null;
        }
        return facturas;
    }
    
    public List<ErrorFile> validaArchivoFactura(List<Factura> facturas) {
        List<ErrorFile> listaErrores = new ArrayList();
        if (facturas == null) {
            listaErrores.add(new ErrorFile(0, "l", "Posible error en el archivo"));
            
        } else if (facturas.isEmpty()) {
            listaErrores.add(new ErrorFile(0, "", "Posible error en el archivo"));
        } else {
            int fila = 1;
            for (Factura factura : facturas) {
                if (factura.getFecha() == null) {
                    listaErrores.add(new ErrorFile(fila, "Fecha", "La fecha esta vacia"));
                }
                if (factura.Contrato.getNombre() == null || factura.Contrato.getNombre().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Contrato", "El nombre del contrato esta vacio"));
                }
                if (factura.Contrato.Usuario.getNombre() == null || factura.Contrato.Usuario.getNombre().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Usuario", "El nombre del usuario esta vacio"));
                }
                if (factura.NodoComercial.NodoRecepcion.getCodigo() == null || factura.NodoComercial.NodoRecepcion.getCodigo().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Nodo Comercial Recepcion", "El codgio del nodo esta vacio"));
                }
                if (factura.NodoComercial.NodoRecepcion.getDescripcion() == null || factura.NodoComercial.NodoRecepcion.getDescripcion().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Descripcion Nodo Comercial Recepcion", "La  descripcion del nodo esta vacio"));
                }
                if (factura.NodoComercial.NodoEntrega.getCodigo() == null || factura.NodoComercial.NodoEntrega.getCodigo().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Nodo Comercial Entrega", "El codgio del nodo esta vacio"));
                }
                if (factura.NodoComercial.NodoEntrega.getDescripcion() == null || factura.NodoComercial.NodoEntrega.getDescripcion().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Descripcion Nodo Comercial Entrega", "La  descripcion del nodo esta vacio"));
                }
                if (factura.ZonaTarifa.ZonaInyeccion.getNombre() == null || factura.ZonaTarifa.ZonaInyeccion.getNombre().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Zona de Tarifa de Inyeccion", "Esta vacio"));                    
                }
                if (factura.ZonaTarifa.ZonaExtraccion.getNombre() == null || factura.ZonaTarifa.ZonaExtraccion.getNombre().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Zona de Tarifa de Extraccion", "Esta vacio"));                    
                }
            }
            
        }
        return listaErrores;
    }
    
}
