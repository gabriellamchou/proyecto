package com.odaw2a.orkdate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.dtos.UsernameDto;
import com.odaw2a.orkdate.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")

public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/mis-datos")
    public String showMisDatos(Model model) {
        Usuario currentUser = usuarioService.getCurrentUser();
        UsernameDto usernameDtoForm = usuarioService.convertUsuarioToUsernameDto(currentUser);
        model.addAttribute("user", currentUser);
        model.addAttribute("usernameDtoForm", usernameDtoForm);
        return "usuario/misDatosView";
    }

    @PostMapping("/cambiar-username/submit")
    public String processUsernameChange(@Valid UsernameDto usernameDtoForm) {
        // Cambiar el nombre de usuario en la base de datos
        Usuario currentUser = usuarioService.getCurrentUser();
        currentUser.setUsername(usernameDtoForm.getUsername());
        usuarioService.editar(currentUser);

        // Actualizar el principal de autenticación en la sesión del usuario
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
            currentUser.getUsername(),
            auth.getCredentials(), 
            auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "redirect:/usuario/mis-datos?username";
    }

}
