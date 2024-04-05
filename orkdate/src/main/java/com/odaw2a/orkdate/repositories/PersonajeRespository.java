package com.odaw2a.orkdate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Usuario;

public interface PersonajeRespository extends JpaRepository<Personaje, Long> {
    
    List<Personaje> findByUsuario(Usuario usuario);

}
