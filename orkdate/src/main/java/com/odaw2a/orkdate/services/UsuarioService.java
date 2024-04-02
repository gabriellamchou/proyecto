package com.odaw2a.orkdate.services;

import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.dtos.UsernameDto;
import com.odaw2a.orkdate.exceptions.InvalidUserDataException;

@Service
public interface UsuarioService {

    public Usuario añadir(Usuario usuario)  throws InvalidUserDataException;

    public Usuario editar(Usuario usuario)  throws InvalidUserDataException;

    public Usuario getCurrentUser();

    public UsernameDto convertUsuarioToUsernameDto(Usuario usuario);

}
