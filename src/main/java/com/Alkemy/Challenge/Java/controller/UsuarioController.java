package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.entity.Rol;
import com.Alkemy.Challenge.Java.entity.Usuario;

import com.Alkemy.Challenge.Java.service.UsuarioService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok().body(usuarioService.getUsuarios());
    }

    @PostMapping("/usuarios/guardar")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/usuarios/guardar").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.guardarUsuario(usuario));
    }

    @PostMapping("/rol/guardar")
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/rol/guardar").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.guardarRol(rol));
    }
    @PostMapping("rol/agregarAUsuario")
    public ResponseEntity<?> agregarRolAUsuario(@RequestBody RoleToUserForm form){
        usuarioService.agregarRolAUsuario(form.getUsername(), form.getRoleNombre());
        return  ResponseEntity.ok().build();

    }
    @Data
    class RoleToUserForm{
        private String username;
        private String roleNombre;
    }
}
