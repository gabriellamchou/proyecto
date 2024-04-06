package com.odaw2a.orkdate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.exceptions.InvalidUserDataException;
import com.odaw2a.orkdate.repositories.PersonajeRespository;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    PersonajeRespository personajeRespository;

    public List<Personaje> obtenerTodos(Usuario usuario) {
        return personajeRespository.findByUsuario(usuario);
    }

    public Personaje obtenerPorId(Long id) throws InvalidUserDataException {
        Personaje personaje = personajeRespository.findById(id).orElseThrow();
        return personaje;
    }

    public Personaje añadir(Personaje personaje, Usuario usuario) throws InvalidUserDataException {
        personaje.setUsuario(usuario);
        try {
            return personajeRespository.save(personaje);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidUserDataException();
        }
    }

    public Personaje editar(Personaje personaje) throws InvalidUserDataException {
        try {
            return personajeRespository.save(personaje);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidUserDataException();
        }
    }

    public void borrar(Long id) throws InvalidUserDataException {
        personajeRespository.deleteById(id);;
    }

}
