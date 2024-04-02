package com.odaw2a.orkdate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.dtos.UsernameDto;
import com.odaw2a.orkdate.repositories.UsuarioRespository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRespository usuarioRepository;
    @Autowired
    ModelMapper modelMapper;
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

    public Usuario editar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return usuarioRepository.findByUsername(currentUserName);
        } else {
            return null;
        }
    }

    public UsernameDto convertUsuarioToUsernameDto(Usuario usuario) {
        return modelMapper.map(usuario, UsernameDto.class);
    }

}
