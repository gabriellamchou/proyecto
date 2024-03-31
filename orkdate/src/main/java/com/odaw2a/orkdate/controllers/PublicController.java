package com.odaw2a.orkdate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.odaw2a.orkdate.domain.Rol;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/tutorial")
    public String showTutorial() {
        return "public/tutorialView";
    }

    @GetMapping({"/registro", "/login"})
    public String showRegistro(@RequestParam(required = false) Integer err, Model model) {
        model.addAttribute("registroForm", new Usuario());
        return "public/loginView";
    }

    @PostMapping("/registro/submit")
    public String processRegistro(@Valid Usuario registroForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/public/registro?err=0";
        }
        registroForm.setRol(Rol.USUARIO);
        usuarioService.añadir(registroForm);
        return "redirect:/public/login";
    }

    @GetMapping("/logout")
    public String showLogout() {
        return "redirect:/public/login";
    }
    

}
