 package Prueba.tecnica.dao;

import Prueba.tecnica.domain.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


 public interface iPersonaDao  extends JpaRepository<Persona, Long>{

    @Query("select p from Persona p where p.nombre=?1")
    Optional<Persona> findByPersona(String nombre);

    
        default public List<Persona> findAll(Persona personas)  {
    	System.out.println("Llamado al repository=" + personas);
    	return this.findAll(personas);
        }
    
}
