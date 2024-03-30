package com.odaw2a.orkdate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/public")
public class PublicController {
    
    @GetMapping("/tutorial")
    public String showTutorial() {
        return "public/tutorial";
    }

    @GetMapping("/registro")
    public String showRegistro() {
        return "public/registro";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "public/login";
    }
    

}
