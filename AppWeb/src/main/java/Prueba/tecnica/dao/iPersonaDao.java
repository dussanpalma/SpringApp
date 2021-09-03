package Prueba.tecnica.dao;

import Prueba.tecnica.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface iPersonaDao  extends CrudRepository<Persona, Long>{
    
}
