
package com.digis01.MMarinCENAGAS.DAO;

import com.digis01.MMarinCENAGAS.JPA.Nodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface INodoDAO extends JpaRepository<Nodo, Integer> {
    
    @Query("FROM Nodo N WHERE N.Codigo = :codigo")
    Nodo findCodigo(String codigo);
    
}
