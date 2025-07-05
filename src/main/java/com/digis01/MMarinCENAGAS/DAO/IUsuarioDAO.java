
package com.digis01.MMarinCENAGAS.DAO;

import com.digis01.MMarinCENAGAS.JPA.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
    
    @Query("FROM Usuario U WHERE U.Nombre = :nombre")
    Usuario findNombre(String nombre);
    
}
