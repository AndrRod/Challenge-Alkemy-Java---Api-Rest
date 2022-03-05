package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Rol;
import com.Alkemy.Challenge.Java.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioService{
    Usuario guardarUsuario(Usuario usuario);
    Rol guardarRol (Rol rol);
    void agregarRolAUsuario(String username, String nombre);
    Usuario getUsuario(String username);
    List<Usuario> getUsuarios();
    Page<Usuario> getUsuarioPaginacion (int page, int size, String sort);
}
