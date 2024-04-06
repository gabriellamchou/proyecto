package com.odaw2a.orkdate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.exceptions.InvalidUserDataException;
import com.odaw2a.orkdate.services.PersonajeService;
import com.odaw2a.orkdate.services.UsuarioService;
import com.odaw2a.orkdate.utilities.Params;

import jakarta.validation.Valid;





@Controller
@RequestMapping("/personaje")
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/list")
    public String showAllPersonajes(@RequestParam(required = false) Integer msg, Model model) {
        if (msg != null) {
            model.addAttribute("msg", Params.CHARMSG[msg]);
        }
        Usuario currentUser = usuarioService.getCurrentUser();
        List<Personaje> listaPersonajes = personajeService.obtenerTodos(currentUser);
        model.addAttribute("listaPersonajes", listaPersonajes);
        return "personaje/personajeView";
    }
    
    @GetMapping("/nuevo")
    public String showNewPersonaje(Model model) {
        Personaje personajeForm = new Personaje();
        model.addAttribute("personajeForm", personajeForm);
        return "personaje/nuevoPersonajeView";
    }

    @PostMapping("/nuevo/submit")
    public String processNewPersonaje(@Valid Personaje personajeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/personaje/list?msg=1";
        }
        try {
            Usuario currentUser = usuarioService.getCurrentUser();
            personajeService.añadir(personajeForm, currentUser);
        } catch (InvalidUserDataException e) {
            return "redirect:/personaje/list?msg=1";
        }
        return "redirect:/personaje/list?msg=0";
    }
    
    @GetMapping("/editar/{id}")
    public String showEditPersonaje(@PathVariable Long id, Model model) {
        Personaje personajeForm;
        try {
            personajeForm = personajeService.obtenerPorId(id);
        } catch (InvalidUserDataException e) {
            return "redirect:/personaje/list?msg=2";
        }
        model.addAttribute("personajeForm", personajeForm);
        return "/personaje/editarPersonajeView";
    }

    @PostMapping("/editar/submit")
    public String processEditPersonaje(@Valid Personaje personajeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/personaje/list?msg=1";
        }
        try {
            personajeService.editar(personajeForm);
        } catch (InvalidUserDataException e) {
            return "redirect:/personaje/list?msg=1";
        }
        return "redirect:/personaje/list?msg=3";
    }
    
    @PostMapping("/borrar/{id}")
    public String processDeletePersonaje(@PathVariable Long id) {
        try {
            personajeService.borrar(id);
        } catch (InvalidUserDataException e) {
            return "redirect:/personaje/list?msg=2";
        }
        return "redirect:/personaje/list?msg=4";
    }
    

}
