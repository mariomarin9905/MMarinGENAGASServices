
package com.digis01.MMarinCENAGAS.DAO;

import com.digis01.MMarinCENAGAS.JPA.NodoRecepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface INodoRecepcionDAO extends JpaRepository<NodoRecepcion, Integer> {
    
    @Query("FROM NodoRecepcion N WHERE N.Codigo = :codigo")
    NodoRecepcion findCodigo(String codigo);
    
}
