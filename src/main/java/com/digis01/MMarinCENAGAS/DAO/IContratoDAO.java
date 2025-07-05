
package com.digis01.MMarinCENAGAS.DAO;

import com.digis01.MMarinCENAGAS.JPA.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface IContratoDAO extends JpaRepository<Contrato, Integer> {  
    @Query("FROM Contrato C WHERE C.Nombre  = :nombre")
    Contrato findNombre(String nombre);
    
}
