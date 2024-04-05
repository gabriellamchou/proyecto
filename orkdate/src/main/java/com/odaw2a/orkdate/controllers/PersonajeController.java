package com.odaw2a.orkdate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.services.PersonajeService;
import com.odaw2a.orkdate.services.UsuarioService;
import com.odaw2a.orkdate.utilities.Params;


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
    

}
