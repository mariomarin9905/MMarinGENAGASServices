
package com.digis01.MMarinCENAGAS.Service;

import com.digis01.MMarinCENAGAS.DAO.IContratoDAO;
import com.digis01.MMarinCENAGAS.JPA.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContratoService {
    
    @Autowired    
    private IContratoDAO iContratoDAO;
    
    public Contrato findNombre(String nombre){
        return this.iContratoDAO.findNombre(nombre);
    
    }
    
    public Contrato save(Contrato Contrato){
        return this.iContratoDAO.save(Contrato);
    }
    
    
}
