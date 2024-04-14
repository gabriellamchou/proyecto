package com.odaw2a.orkdate.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showCantina(@RequestParam(required = false) Integer pag, Model model) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        // Usuario currentUser = usuarioService.getCurrentUser();
        Integer ultPag = usuarioService.getTotalPaginas() - 1;
        if (pag == null || pag < 0 || pag > ultPag) {
            pag = 0;
        }
        Integer pagSig = ultPag > pag ? pag + 1 : ultPag;
        Integer pagAnt = pag > 0 ? pag - 1 : 0;
        try {
            listaUsuarios = usuarioService.obtenerPerfilesPaginados(pag);
            model.addAttribute("listaUsuarios", listaUsuarios);
        } catch (Exception e) {
            return "/cantina?op=1";
        }
        model.addAttribute("pagSiguiente", pagSig);
        model.addAttribute("pagAnterior", pagAnt);
        model.addAttribute("pagFinal", ultPag);
        return "cantina/cantinaView";
    }

}
