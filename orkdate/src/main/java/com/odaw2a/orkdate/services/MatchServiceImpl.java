package com.odaw2a.orkdate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Interaccion;
import com.odaw2a.orkdate.domain.TipoInteraccion;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.repositories.MatchRepository;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchRepository matchRepository;

    public List<Interaccion> obtenerTodos() {
        return this.matchRepository.findAll();
    }
    
    public Interaccion registrarLike(Usuario usuarioA, Usuario usuarioB) {
        Interaccion like = new Interaccion(null, usuarioA, usuarioB, TipoInteraccion.LIKE);
        return this.matchRepository.save(like);
    }

}
