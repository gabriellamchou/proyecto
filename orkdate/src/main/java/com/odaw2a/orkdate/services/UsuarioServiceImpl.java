package com.odaw2a.orkdate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.repositories.UsuarioRespository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRespository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario añadir(Usuario usuario) {
        String passCrypted = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passCrypted);
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
