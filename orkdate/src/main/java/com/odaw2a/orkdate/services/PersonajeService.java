package com.odaw2a.orkdate.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.exceptions.InvalidUserDataException;

@Service
public interface PersonajeService {

    public List<Personaje> obtenerTodos(Usuario usuario);

    public Personaje obtenerPorId(Long id) throws InvalidUserDataException;

    public Personaje añadir(Personaje personaje, Usuario usuario)  throws InvalidUserDataException;

    public Personaje editar(Personaje personaje)  throws InvalidUserDataException;

    public void borrar(Long id) throws InvalidUserDataException;

}
