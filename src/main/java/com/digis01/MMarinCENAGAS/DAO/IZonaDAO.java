
package com.digis01.MMarinCENAGAS.DAO;

import com.digis01.MMarinCENAGAS.JPA.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface IZonaDAO extends JpaRepository<Zona, Integer> {
    
    @Query("FROM Zona Z WHERE Z.Nombre = :nombre")
    Zona findNombre(String nombre);
    
}
