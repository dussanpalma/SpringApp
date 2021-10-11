package Prueba.tecnica.servicio;

import Prueba.tecnica.dao.iPersonaDao;
import Prueba.tecnica.domain.Persona;
import Prueba.tecnica.redis.config.CacheConfig;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    //TestRepository
    private iPersonaDao personaRepositoryTest;

    @Autowired
    iPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CacheConfig.PERSONA_CACHE, unless = "#result == null")
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    @CachePut(cacheNames = CacheConfig.PERSONA_CACHE, key = "#persona", unless = "#result == null")
    public void guardar(Persona persona) {

        personaDao.save(persona);

    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = CacheConfig.PERSONA_CACHE, key = "#persona")
    public void eliminar(Persona persona) {

        personaDao.delete(persona);

    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }

    
    //TestService 
    @Override

    public Persona TestfindById(Long id) {
        return personaDao.findById(id).orElseThrow();
    }

    //TestRepository
    @Override
    public Persona findById(Long id) {
        return personaRepositoryTest.findById(id).orElseThrow();
    }

}
