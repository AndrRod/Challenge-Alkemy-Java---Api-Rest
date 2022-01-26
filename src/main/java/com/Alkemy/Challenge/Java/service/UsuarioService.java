package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Rol;
import com.Alkemy.Challenge.Java.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioService{
    Usuario guardarUsuario(Usuario usuario);
    Rol guardarRol (Rol rol);
    void agregarRolAUsuario(String username, String nombre);
    Usuario getUsuario(String username);
    List<Usuario> getUsuarios();
}
