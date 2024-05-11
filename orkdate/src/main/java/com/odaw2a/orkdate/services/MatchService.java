package com.odaw2a.orkdate.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Interaccion;
import com.odaw2a.orkdate.domain.Usuario;

@Service
public interface MatchService {
    public List<Interaccion> obtenerTodos();
    public Interaccion registrarLike(Usuario usuarioA, Usuario usuarioB);
}
