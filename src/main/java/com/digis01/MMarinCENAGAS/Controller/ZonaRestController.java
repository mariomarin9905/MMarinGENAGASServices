package com.digis01.MMarinCENAGAS.Controller;

import com.digis01.MMarinCENAGAS.DAO.IZonaDAO;
import com.digis01.MMarinCENAGAS.JPA.Result;
import com.digis01.MMarinCENAGAS.JPA.Zona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zonaapi")
public class ZonaRestController {
    
    @Autowired
    private IZonaDAO iZonaDAO;    
    @GetMapping()
    public ResponseEntity GetAll(){
        Result result = new Result();
        try {
            List<Zona> zonas = this.iZonaDAO.findAll();
            if (zonas == null) {
                return ResponseEntity.notFound().build();
            }
            if (zonas.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            result.correct = true;
            result.object = zonas;
            return ResponseEntity.ok(zonas);
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage  = e.getLocalizedMessage();
            result.ex = e;
            return ResponseEntity.internalServerError().body(result);
        }
    }
    
}
