package Prueba.tecnica.web;

import Prueba.tecnica.domain.Persona;
import Prueba.tecnica.servicio.PersonaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String inicio(Model model) {

        var personas = personaService.listarPersonas();
        model.addAttribute("personas", personas);
        return "index.html";

    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {

        return "modificar";

    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores) {

        if (errores.hasErrors()) {

            return "modificar";

        }

        personaService.guardar(persona);
        return "redirect:/";

    }

    @GetMapping("/editar")
    public String editar(Persona persona, Model model) {

        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";

    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona) {

        personaService.eliminar(persona);
        return "redirect:/";

    }
}
