package Prueba.tecnica;

import Prueba.tecnica.dao.iPersonaDao;
import Prueba.tecnica.domain.Persona;
import Prueba.tecnica.servicio.PersonaService;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TestService {

    @MockBean
    iPersonaDao personaRepository;

    @Autowired
    PersonaService service;

    //----------- Probando service ----------// 
    @Test
    void testFindById() {
        when(personaRepository.findById(1L)).thenReturn(Datos.crearPersona001());
        when(personaRepository.findById(2L)).thenReturn(Datos.crearPersona002());
        when(personaRepository.findById(3L)).thenReturn(Datos.crearPersona003());

        Persona persona1 = service.TestfindById(1L);
        Persona persona2 = service.TestfindById(2L);
        Persona persona3 = service.TestfindById(3L);

        assertEquals("Carlos", persona1.getNombre());
        assertEquals("Enrique", persona2.getNombre());
        assertEquals("Maria", persona3.getNombre());

        assertNotNull(persona1);
        assertNotNull(persona2);
        assertNotNull(persona3);

        assertFalse(persona1 == persona2);
        assertFalse(persona2 == persona3);
        assertFalse(persona3 == persona1);

        assertTrue(persona1 != persona2);
        assertTrue(persona2 != persona3);
        assertTrue(persona3 != persona1);

        verify(personaRepository, times(1)).findById(1L);
        verify(personaRepository, times(1)).findById(2L);
        verify(personaRepository, times(1)).findById(3L);

    }

    @Test
    void testFindAll() {
        List<Persona> personas = service.listarPersonas();
        assertTrue(personas.isEmpty());
        assertEquals(0, personas.size());
    }

    @Test
    void testGuardar() {

        Persona personaEjemplo = new Persona(1L, "Carlos", "Meza", "11462445556",
                "carlosmeza@hotmail.com", "3006852645");

        service.guardar(personaEjemplo);

        assertEquals(1, personaEjemplo.getIdPersona());
        assertEquals("Carlos", personaEjemplo.getNombre());
        assertEquals("Meza", personaEjemplo.getApellido());
        assertEquals("11462445556", personaEjemplo.getCedula());
        assertEquals("carlosmeza@hotmail.com", personaEjemplo.getEmail());
        assertEquals("3006852645", personaEjemplo.getTelefono());

    }

    @Test
    void testActualizar() {

        Persona persona1 = new Persona(1L, "Carlos", "Meza", "11462445556",
                "carlosmeza@hotmail.com", "3006852645");

        service.guardar(persona1);

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
    void testEliminar() {

        Persona persona1 = new Persona(1L, "Carlos", "Meza", "11462445556",
                "carlosmeza@hotmail.com", "3006852645");

        service.guardar(persona1);

        assertEquals(1, persona1.getIdPersona());
        assertEquals("Carlos", persona1.getNombre());
        assertEquals("Meza", persona1.getApellido());
        assertEquals("11462445556", persona1.getCedula());
        assertEquals("carlosmeza@hotmail.com", persona1.getEmail());
        assertEquals("3006852645", persona1.getTelefono());

        service.eliminar(persona1);

        assertEquals(null, service.encontrarPersona(persona1));
    }

}
