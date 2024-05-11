package com.odaw2a.orkdate.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.services.MatchService;
import com.odaw2a.orkdate.services.PersonajeService;
import com.odaw2a.orkdate.services.UsuarioService;

@Controller
@RequestMapping("/cantina")
public class CantinaController {

    @Autowired
    PersonajeService personajeService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    MatchService matchService;
    
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
            model.addAttribute("usuarioCantina", usuarioCantina);
            model.addAttribute("usuarioSiguienteIdx", uSig);
        } catch (Exception e) {
            return "/cantina?msg=1";
        }
        return "cantina/cantinaView";
    }

    @PostMapping("/like")
    public String storeLike(@RequestParam Long uid, @RequestParam Long usig) {
        Usuario currentUser = usuarioService.getCurrentUser();
        Usuario usuarioCantina = usuarioService.obtenerPorId(uid);
        this.matchService.registrarLike(currentUser, usuarioCantina);
        System.out.println(this.matchService.obtenerTodos());
        return "redirect:/cantina?u=" + usig;
    }

}
