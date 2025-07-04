package com.digis01.MMarinCENAGAS.Controller;

import com.digis01.MMarinCENAGAS.DAO.IFacturaDAO;
import com.digis01.MMarinCENAGAS.JPA.Factura;
import com.digis01.MMarinCENAGAS.JPA.Result;
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

@RestController
@RequestMapping("/facturaapi")
public class FacturaRestContoller {
    
    @Autowired
    private IFacturaDAO iFacturaDAO;
    
    @PostMapping("/cargamasiva")    
    public ResponseEntity CargaMasiv(@RequestParam MultipartFile archivo){
        Result result  = new Result();
        try {
            if (archivo == null && archivo.isEmpty()) {                
                result.correct = false;
                result.errorMessage = "Error es nulo o vacio";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            String root = System.getProperty("user.dir");//Obtiene la ruta donde esta el proyecto
            String path  = "src/main/resources/static/archivos";
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
            String absolutePath = root+"/"+path+"/"+ archivo.getName() + fecha;
            archivo.transferTo(new File(absolutePath));
             String[] split= archivo.getName().split("\\.");
             if (!split[split.length-1].equals("xlsx")) {
                 result.correct = false;
                 result.errorMessage = "Tipo de Archivo Invalido";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
             List<Factura> facturas = this.LeerArchivo();
            return ResponseEntity.ok(result);            
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage= e.getLocalizedMessage();
            return ResponseEntity.internalServerError().body(result);
        }
    }
    
    private List<Factura> LeerArchivo(){
        List<Factura> facturas = new ArrayList();
        try {
            
            return facturas;
        } catch (Exception e) {
            return facturas = null;            
        }
    }
    
}
