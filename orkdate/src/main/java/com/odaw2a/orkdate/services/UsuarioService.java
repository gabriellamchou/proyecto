package com.odaw2a.orkdate.services;

import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Usuario;

@Service
public interface UsuarioService {

    public Usuario añadir(Usuario usuario);

}
