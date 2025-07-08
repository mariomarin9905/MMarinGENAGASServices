
package com.digis01.MMarinCENAGAS.Service;

import com.digis01.MMarinCENAGAS.DAO.IFacturaDAO;
import com.digis01.MMarinCENAGAS.JPA.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {
    
    @Autowired
    private IFacturaDAO iFacturaDAO;
    
    public Factura save(Factura factura){
        return this.iFacturaDAO.save(factura);
    }
    
}
