
package com.digis01.MMarinCENAGAS.Controller;

import com.digis01.MMarinCENAGAS.JPA.NodoRecepcion;
import com.digis01.MMarinCENAGAS.JPA.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digis01.MMarinCENAGAS.DAO.INodoRecepcionDAO;

@RestController
@RequestMapping("/nodoapi")
public class NodoRestController {
    
    @Autowired
    private INodoRecepcionDAO iNodoDAO;
    
    @GetMapping()
    public ResponseEntity GetAll(){
        Result result = new Result();        
        try {
            List<NodoRecepcion> nodos = this.iNodoDAO.findAll();
            if (nodos == null) {
                return ResponseEntity.notFound().build();
            }
            if(nodos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            result.correct = true;
            result.object=nodos;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();            
            return ResponseEntity.internalServerError().body(result);           
            
        }
    
    }
    
    
}
