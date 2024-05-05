package com.odaw2a.orkdate.controllers;

import java.util.ArrayList;
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


@Controller
@RequestMapping("/cantina")
public class CantinaController {

    @Autowired
    PersonajeService personajeService;
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping({"", "/"})
    public String showCantina(@RequestParam(required = false) Integer u, Model model) {
        Usuario usuarioCantina;
        List<Usuario> listaUsuarios = usuarioService.findAllButCurrent();
        List<Personaje> listaPersonajes = new ArrayList<>();
        Integer lastU = listaUsuarios.size() - 1;
        if (u == null || u < 0 || u > lastU) {
            u = 0;
        }
        Integer uSig = lastU > u ? u + 1 : lastU;
        try {
            usuarioCantina = usuarioService.findAllButCurrent().get(u);
            listaPersonajes = personajeService.obtenerPorUsuario(usuarioCantina);
            model.addAttribute("username", usuarioCantina.getUsername());
            model.addAttribute("listaPersonajes", listaPersonajes);
        } catch (Exception e) {
            return "/cantina?msg=1";
        }
        model.addAttribute("usuarioSiguiente", uSig);
        return "cantina/cantinaView";
    }

}
