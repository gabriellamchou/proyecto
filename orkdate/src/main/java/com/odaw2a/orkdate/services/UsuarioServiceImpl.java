package com.odaw2a.orkdate.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.dtos.PasswordDto;
import com.odaw2a.orkdate.dtos.UsernameDto;
import com.odaw2a.orkdate.exceptions.InvalidUserDataException;
import com.odaw2a.orkdate.repositories.UsuarioRespository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRespository usuarioRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario añadir(Usuario usuario) throws InvalidUserDataException {
        if (usuario.getPassword().length() < 4 || usuario.getPassword().length() > 15) {
            throw new InvalidUserDataException();
        }
        String passCrypted = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passCrypted);
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidUserDataException();
        }
    }

    public Usuario editar(Usuario usuario) throws InvalidUserDataException {
        String passCrypted = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passCrypted);
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidUserDataException();
        }
    }

    public void borrar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
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

    public List<Usuario> findAllButCurrent() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        Usuario currentUser = this.getCurrentUser();
        listaUsuarios.remove(currentUser);
        return listaUsuarios;
    }

    public UsernameDto convertUsuarioToUsernameDto(Usuario usuario) {
        return modelMapper.map(usuario, UsernameDto.class);
    }

    public PasswordDto convertUsuarioToPasswordDto(Usuario usuario) {
        return modelMapper.map(usuario, PasswordDto.class);
    }

}
