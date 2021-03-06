package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Rol;
import com.Alkemy.Challenge.Java.entity.Usuario;
import com.Alkemy.Challenge.Java.exception.BadRequestException;
import com.Alkemy.Challenge.Java.repository.RolRepository;
import com.Alkemy.Challenge.Java.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

//    cargamos todos los usuarios por username para llevarlo a security config
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(username == null){
            log.error("usuario no encontrado en la base de datos");
            throw new UsernameNotFoundException("usuario no encontrado en la base de datos");
        }else{
            log.info("usuario encontrado en la base de datos: {}", username);
        }
        Collection<SimpleGrantedAuthority> autorizaciones = new ArrayList<>();
        usuario.getRoles().forEach(role -> {
            autorizaciones.add(new SimpleGrantedAuthority(role.getNombre().toString()));
        });
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getContrasenia(), autorizaciones);
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new BadRequestException("El usuario con el email " + usuario.getEmail() + " ya se encuentra registrado");
        }
        if(usuarioRepository.existsByUsername(usuario.getUsername())){
            throw new BadRequestException("El usuario con el nombre el username " + usuario.getUsername() + " ya se encuentra registrado");
        }
        log.info("guardar nuevo usuario {} en la base de datos", usuario.getUsername());
//        encriptamos la contrase??a en la base de datos
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        return usuarioRepository.save(usuario);
    }
    @Override
    public Rol guardarRol(Rol rol) {
        log.info("guardar nuevo rol {} en la base de datos", rol.getNombre());
        return rolRepository.save(rol);
    }
    @Override
    public void agregarRolAUsuario(String username, String rolNombre) {
        log.info("Agregando role {} al usuario {}.", rolNombre, username);
        Usuario usuario = usuarioRepository.findByUsername(username);
        Rol rol = rolRepository.findByNombre(rolNombre);
        usuario.getRoles().add(rol);
    }
    @Override
    public Usuario getUsuario(String username) {
        log.info("obteniendo username {}.", username);
        return usuarioRepository.findByUsername(username);
    }
    @Override
    public List<Usuario> getUsuarios() {
        log.info("obteniendo a todos los usuarios");
        return usuarioRepository.findAll();
    }
    @Override
    public Page<Usuario> getUsuarioPaginacion(int page, int size, String sort) {
        return usuarioRepository.
                findAll(PageRequest.of(page, size).withSort(Sort.by(sort)));
    }
}
