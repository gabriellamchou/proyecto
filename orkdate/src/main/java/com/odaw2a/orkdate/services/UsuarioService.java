package com.odaw2a.orkdate.services;

import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.dtos.UsernameDto;

@Service
public interface UsuarioService {

    public Usuario añadir(Usuario usuario);

    public Usuario editar(Usuario usuario);

    public Usuario getCurrentUser();

    public UsernameDto convertUsuarioToUsernameDto(Usuario usuario);

}
