package Prueba.tecnica;

import Prueba.tecnica.domain.Persona;
import java.util.Optional;

public class Datos {
    
    

    public static  Optional<Persona> crearPersona001() {

        return Optional.of(new Persona(1L, "Carlos", "Meza", "11462445556", "carlosmeza@hotmail.com", "3006852645"));

    }

    public static Optional<Persona> crearPersona002() {

        return  Optional.of(new Persona(2L, "Enrique", "Ortega", "1144546656", "enriqueortega@hotmail.com", "3006852645"));

    }

    public static Optional<Persona> crearPersona003() {

        return  Optional.of(new Persona(3L, "Maria", "tereza", "114627456", "tmaria@hotmail.com", "300685545"));

    }

}
