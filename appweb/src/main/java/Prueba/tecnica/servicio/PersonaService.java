package Prueba.tecnica.servicio;

import Prueba.tecnica.domain.Persona;
import java.util.List;


public interface PersonaService {
    
    public List<Persona> listarPersonas();
    
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontrarPersona(Persona persona);
    
    public Persona TestfindById(Long id);
    
    Persona findById(Long id);
    
    

   
    
}
