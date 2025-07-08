
package com.digis01.MMarinCENAGAS.DAO;

import com.digis01.MMarinCENAGAS.JPA.NodoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface INodoEntregaDAO extends JpaRepository<NodoEntrega, Integer> {
    
    @Query("FROM NodoEntrega N WHERE N.Codigo = :codigo")
    NodoEntrega findCodigo(String codigo);
}
