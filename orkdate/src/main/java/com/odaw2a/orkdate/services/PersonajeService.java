package com.odaw2a.orkdate.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.exceptions.InvalidUserDataException;

@Service
public interface PersonajeService {

    public List<Personaje> obtenerTodos(Usuario usuario);

    public Personaje añadir(Personaje personaje)  throws InvalidUserDataException;

    public Personaje editar(Personaje personaje)  throws InvalidUserDataException;

    public void borrar(Personaje personaje);

}
