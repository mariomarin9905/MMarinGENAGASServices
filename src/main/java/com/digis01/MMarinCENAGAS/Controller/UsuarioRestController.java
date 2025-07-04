
package com.digis01.MMarinCENAGAS.Controller;

import com.digis01.MMarinCENAGAS.DAO.IUsuarioDAO;
import com.digis01.MMarinCENAGAS.JPA.Result;
import com.digis01.MMarinCENAGAS.JPA.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarioapi")
public class UsuarioRestController {
    
    @Autowired
    private IUsuarioDAO iUsuarioDAO;
    
    @GetMapping
    public ResponseEntity GetAll(){
        Result result = new Result();
        try {
             List<Usuario> usuarios = this.iUsuarioDAO.findAll();
            if ( usuarios == null ) {
                return ResponseEntity.notFound().build();
            }
            if (usuarios.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            result.correct = true;
            result.object = usuarios;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex =e;
            return ResponseEntity.internalServerError().body(result);
        }       
    }
    
}
