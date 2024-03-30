package com.odaw2a.orkdate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.repositories.UsuarioRespository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRespository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null)
            throw (new UsernameNotFoundException("Usuario no encontrado!"));
        return User
                .withUsername(username)
                .roles(usuario.getRol().toString())
                .password(usuario.getPassword())
                .build();
    }
}
