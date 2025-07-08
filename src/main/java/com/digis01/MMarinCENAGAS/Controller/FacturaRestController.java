package com.digis01.MMarinCENAGAS.Controller;

import com.digis01.MMarinCENAGAS.DAO.IContratoDAO;

import com.digis01.MMarinCENAGAS.DAO.INodoEntregaDAO;

import com.digis01.MMarinCENAGAS.JPA.Contrato;
import com.digis01.MMarinCENAGAS.JPA.ErrorFile;
import com.digis01.MMarinCENAGAS.JPA.Factura;
import com.digis01.MMarinCENAGAS.JPA.NodoRecepcion;

import com.digis01.MMarinCENAGAS.JPA.Result;
import com.digis01.MMarinCENAGAS.JPA.ResultFile;

import com.digis01.MMarinCENAGAS.JPA.Usuario;
import com.digis01.MMarinCENAGAS.JPA.Zona;

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

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;
import com.digis01.MMarinCENAGAS.DAO.INodoRecepcionDAO;
import com.digis01.MMarinCENAGAS.DAO.IUsuarioDAO;
import com.digis01.MMarinCENAGAS.JPA.NodoEntrega;
import com.digis01.MMarinCENAGAS.Service.ContratoService;
import com.digis01.MMarinCENAGAS.Service.FacturaService;
import com.digis01.MMarinCENAGAS.Service.NodoEntregaService;
import com.digis01.MMarinCENAGAS.Service.NodoRecepcionService;
import com.digis01.MMarinCENAGAS.Service.UsuarioService;

@RestController
@RequestMapping("/facturaapi")
public class FacturaRestController {

    @Autowired
    private FacturaService facturaService;
    @Autowired
    private ContratoService contratoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private NodoRecepcionService nodoRecepcionService;
    @Autowired
    private NodoEntregaService nodoEntregaService;    
    
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
                Contrato contrato = this.contratoService.findNombre(factura.Contrato.getNombre());
                if (contrato != null) {
                   factura.Contrato.setIdContrato(contrato.getIdContrato());
                   this.facturaService.save(factura);
                } else {
                    Usuario usuario = this.usuarioService.findNombre(factura.Contrato.Usuario.getNombre());
                    if (usuario == null) {
                        result.correct = false;
                        result.errorMessage = "El Usuario no existe en la base de datos";
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
                    }
                    contrato = new Contrato();
                    contrato.Usuario = usuario;
                    NodoRecepcion nodoRecepcion =this.nodoRecepcionService.findCodigo(factura.Contrato.NodoRecepcion.getCodigo());
                    if (nodoRecepcion == null) {
                        result.correct = false;
                        result.errorMessage = "El Nodo Comercial Recepcion no existe en la base de datos";
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
                    }                    
                    contrato.NodoRecepcion = nodoRecepcion;
                    NodoEntrega nodoEntrega = this.nodoEntregaService.findCodigo(factura.Contrato.NodoEntrega.getCodigo());
                    if (nodoEntrega == null) {
                        result.correct = false;
                        result.errorMessage = "El Nodo Comercial Entrega no existe en la base de datos";
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);                        
                    }
                    contrato.NodoEntrega = nodoEntrega;
                    contrato.setNombre(factura.Contrato.getNombre());
                    int idContrato = this.contratoService.save(contrato).getIdContrato();
                    contrato.setIdContrato(idContrato);
                    factura.Contrato = contrato;
                    this.facturaService.save(factura);
                }
            }
            result.correct = true;            
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
                        factura.Contrato.NodoEntrega = new NodoEntrega();
                        factura.Contrato.NodoRecepcion = new NodoRecepcion();
                        factura.setFecha(row.getCell(0).getDateCellValue());
                        factura.Contrato.setNombre(row.getCell(1).toString());
                        factura.Contrato.Usuario.setNombre(row.getCell(2).toString());
                        factura.Contrato.NodoRecepcion.setCodigo(row.getCell(3).toString());
                        factura.Contrato.NodoRecepcion.setDescripcion(row.getCell(4).toString());
                        factura.Contrato.NodoEntrega.setCodigo(row.getCell(5).toString());
                        factura.Contrato.NodoEntrega.setDescripcion(row.getCell(6).toString());
                        factura.Contrato.NodoRecepcion.ZonaInyeccion.setNombre(row.getCell(7).toString());
                        factura.Contrato.NodoEntrega.ZonaExtraccion.setNombre(row.getCell(8).toString());
                        factura.setCantidadNominadaRecepcion(row.getCell(9).getNumericCellValue());
                        factura.setCantidadNominadaEntrega(row.getCell(10).getNumericCellValue());
                        factura.setCantidadAsignadaRecepcion(row.getCell(11).getNumericCellValue());
                        factura.setCantidadAsignadaEntrega(row.getCell(12).getNumericCellValue());
                        factura.setGasExeceso(row.getCell(13).getNumericCellValue());
                        factura.setTarifaExcesoFirme(row.getCell(14).getNumericCellValue());
                        factura.setTarifaUsoInterrumpible(row.getCell(15).getNumericCellValue());
                        factura.setCargoUso(row.getCell(16).getNumericCellValue());
                        factura.setGasExeceso(row.getCell(17).getNumericCellValue());
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
            listaErrores.add(new ErrorFile(0, "", "Posible error en el archivo"));

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
                if (factura.Contrato.NodoRecepcion.getCodigo() == null || factura.Contrato.NodoRecepcion.getCodigo().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Nodo Comercial Recepcion", "El codgio del nodo esta vacio"));
                }
                if (factura.Contrato.NodoRecepcion.getDescripcion() == null || factura.Contrato.NodoRecepcion.getDescripcion().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Descripcion Nodo Comercial Recepcion", "La  descripcion del nodo esta vacio"));
                }
                if (factura.Contrato.NodoEntrega.getCodigo() == null || factura.Contrato.NodoEntrega.getCodigo().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Nodo Comercial Entrega", "El codgio del nodo esta vacio"));
                }
                if (factura.Contrato.NodoEntrega.getDescripcion() == null || factura.Contrato.NodoEntrega.getDescripcion().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Descripcion Nodo Comercial Entrega", "La  descripcion del nodo esta vacio"));
                }
                if (factura.Contrato.NodoRecepcion.ZonaInyeccion.getNombre() == null || factura.Contrato.NodoRecepcion.ZonaInyeccion.getNombre().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Zona de Tarifa de Inyeccion", "Esta vacio"));
                }
                if (factura.Contrato.NodoEntrega.ZonaExtraccion.getNombre() == null || factura.Contrato.NodoEntrega.ZonaExtraccion.getNombre().isEmpty()) {
                    listaErrores.add(new ErrorFile(fila, "Zona de Tarifa de Extraccion", "Esta vacio"));
                }
            }

        }
        return listaErrores;
    }

}
