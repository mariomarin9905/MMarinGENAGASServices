
package com.digis01.MMarinCENAGAS.Service;

import com.digis01.MMarinCENAGAS.DAO.INodoEntregaDAO;
import com.digis01.MMarinCENAGAS.JPA.NodoEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodoEntregaService {
    
    @Autowired
    private INodoEntregaDAO iNodoEntregaDAO;
    
    public NodoEntrega findCodigo(String Codigo){
        return this.iNodoEntregaDAO.findCodigo(Codigo);
    }
    
    
}
