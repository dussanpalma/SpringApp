package Prueba.tecnica;


import Prueba.tecnica.dao.iPersonaDao;
import Prueba.tecnica.domain.Persona;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TestRepository {

    @Autowired
    iPersonaDao personaRepositoryTest;

    @Test
    void testFindById() {

        Optional<Persona> persona = personaRepositoryTest.findById(1L);
        assertTrue(persona.isPresent());
        assertEquals("Dussan", persona.orElseThrow().getNombre());
        assertEquals("Ortega", persona.orElseThrow().getApellido());

    }

    @Test
    void testFindAll() {
        List<Persona> personas = personaRepositoryTest.findAll();
        assertFalse(personas.isEmpty());
        assertEquals(1, personas.size());
    }

    @Test
    void testSave() {

        // Given
        Persona personaEjemplo = new Persona(1L, "Carlos", "Meza", "11462445556",
                "carlosmeza@hotmail.com", "3006852645");
        // When
        Persona persona1 = personaRepositoryTest.save(personaEjemplo);
        // Then
        assertEquals(1, persona1.getIdPersona());
        assertEquals("Carlos", persona1.getNombre());
        assertEquals("Meza", persona1.getApellido());
        assertEquals("11462445556", persona1.getCedula());
        assertEquals("carlosmeza@hotmail.com", persona1.getEmail());
        assertEquals("3006852645", persona1.getTelefono());

    }

    @Test
    void testUpdate() {

        // Given
        Persona personaEjemplo = new Persona(1L, "Carlos", "Meza", "11462445556",
                "carlosmeza@hotmail.com", "3006852645");

        // When
        Persona persona1 = personaRepositoryTest.save(personaEjemplo);

        // Then
        assertEquals(1, persona1.getIdPersona());
        assertEquals("Carlos", persona1.getNombre());
        assertEquals("Meza", persona1.getApellido());
        assertEquals("11462445556", persona1.getCedula());
        assertEquals("carlosmeza@hotmail.com", persona1.getEmail());
        assertEquals("3006852645", persona1.getTelefono());

        // When
        persona1.setNombre("Camilo");
        persona1.setApellido("Orozco");
        persona1.setCedula("24545554");
        persona1.setEmail("camilorozco@hotmail.com");
        persona1.setTelefono("3305655465");

        // Then
        assertEquals(1, persona1.getIdPersona());
        assertEquals("Camilo", persona1.getNombre());
        assertEquals("Orozco", persona1.getApellido());
        assertEquals("24545554", persona1.getCedula());
        assertEquals("camilorozco@hotmail.com", persona1.getEmail());
        assertEquals("3305655465", persona1.getTelefono());

    }

    @Test
    void testDelete() {
        Persona persona1 = personaRepositoryTest.findById(1L).orElseThrow();
        assertEquals("Dussan", persona1.getNombre());

        personaRepositoryTest.delete(persona1);

//        assertThrows(NoSuchElementException.class, () -> {
//            personaRepositoryTest.findById(1L).orElseThrow();
//        });

        assertEquals(0, personaRepositoryTest.findAll().size());
    }

}
