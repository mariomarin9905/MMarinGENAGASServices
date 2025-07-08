
package com.digis01.MMarinCENAGAS.Service;

import com.digis01.MMarinCENAGAS.DAO.IUsuarioDAO;
import com.digis01.MMarinCENAGAS.JPA.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private IUsuarioDAO iUsuarioDAO;
    
    public Usuario findNombre(String Nombre){
        return this.iUsuarioDAO.findNombre(Nombre);
    }
    
}
