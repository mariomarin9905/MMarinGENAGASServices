
package com.digis01.MMarinCENAGAS.Service;

import com.digis01.MMarinCENAGAS.DAO.INodoRecepcionDAO;
import com.digis01.MMarinCENAGAS.JPA.NodoRecepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodoRecepcionService {
    
    @Autowired
    private INodoRecepcionDAO iNodoRecepcionDAO;
    
    public NodoRecepcion findCodigo(String Codigo){
        return this.iNodoRecepcionDAO.findCodigo(Codigo);
    }
    
    
}
