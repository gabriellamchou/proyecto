package com.odaw2a.orkdate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.dtos.PasswordDto;
import com.odaw2a.orkdate.dtos.UsernameDto;
import com.odaw2a.orkdate.services.UsuarioService;
import com.odaw2a.orkdate.utilities.Params;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/usuario")

public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/mis-datos")
    public String showMisDatos(@RequestParam(required = false) Integer msg, Model model) {
        if (msg != null) {
            model.addAttribute("msg", Params.USERMSG[msg]);
        }
        Usuario currentUser = usuarioService.getCurrentUser();
        UsernameDto usernameDtoForm = usuarioService.convertUsuarioToUsernameDto(currentUser);
        PasswordDto passwordDtoForm = usuarioService.convertUsuarioToPasswordDto(currentUser);
        model.addAttribute("user", currentUser);
        model.addAttribute("usernameDtoForm", usernameDtoForm);
        model.addAttribute("passwordDtoForm", passwordDtoForm);
        return "usuario/misDatosView";
    }

    @PostMapping("/cambiar-username/submit")
    public String processUsernameChange(@Valid UsernameDto usernameDtoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/usuario/mis-datos?msg=0";
        }
        
        // Cambiar el nombre de usuario en la base de datos
        Usuario currentUser = usuarioService.getCurrentUser();
        currentUser.setUsername(usernameDtoForm.getUsername());
        try {
            usuarioService.editar(currentUser);
        } catch (Exception e) {
            return "redirect:/usuario/mis-datos?msg=0";
        }

        // Actualizar el principal de autenticación en la sesión del usuario
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
            currentUser.getUsername(),
            auth.getCredentials(), 
            auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // Devolver vista con parámetro username para indicar éxito en el cambio
        return "redirect:/usuario/mis-datos?username";
    }

    @PostMapping("/cambiar-password/submit")
    public String processPasswordChange(@Valid PasswordDto passwordDtoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/usuario/mis-datos?msg=3";
        }
        
        // Cambiar la contraseña en la base de datos
        Usuario currentUser = usuarioService.getCurrentUser();
        currentUser.setPassword(passwordDtoForm.getPassword());
        try {
            usuarioService.editar(currentUser);
        } catch (Exception e) {
            return "redirect:/usuario/mis-datos?msg=3";
        }
        return "redirect:/usuario/mis-datos?msg=4";
    }

    @PostMapping("/borrar-usuario/submit")
    public String processDeleteUser() {
        Usuario currentUser = usuarioService.getCurrentUser();
        usuarioService.borrar(currentUser);
        return "redirect:/public/registro";
    }
    

}
