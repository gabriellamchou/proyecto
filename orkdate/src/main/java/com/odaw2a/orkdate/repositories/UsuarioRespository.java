package com.odaw2a.orkdate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odaw2a.orkdate.domain.Usuario;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {
    
    Usuario findByUsername(String username);

}
