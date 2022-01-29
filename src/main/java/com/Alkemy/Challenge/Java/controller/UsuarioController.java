package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.entity.Rol;
import com.Alkemy.Challenge.Java.entity.Usuario;

import com.Alkemy.Challenge.Java.service.EmailService;
import com.Alkemy.Challenge.Java.service.UsuarioService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final EmailService emailService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok().body(usuarioService.getUsuarios());
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/guardar").toUriString());
        emailService.sendEmail(usuario.getEmail());
        return ResponseEntity.created(uri).body(usuarioService.guardarUsuario(usuario));
    }

    @PostMapping("/rol/guardar")
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/rol/guardar").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.guardarRol(rol));
    }
    @PostMapping("rol/agregarAUsuario")
    public ResponseEntity<?> agregarRolAUsuario(@RequestBody RoleToUserForm form){
        usuarioService.agregarRolAUsuario(form.getUsername(), form.getRoleNombre());
        return  ResponseEntity.ok().build();
    }
    //todavia falta
    @GetMapping("rol/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        String autorizacionHeader = request.getHeader(AUTHORIZATION);

//        if(autorizacionHeader != null && autorizacionHeader.startsWith("Token ")){
//            try {
//                String token = autorizacionHeader.substring("Token ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(token);
//                String username = decodedJWT.getSubject();
//                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
//                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                stream(roles).forEach(role-> {
//                    authorities.add(new SimpleGrantedAuthority(role));
//                });
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                filterChain.doFilter(request, response);
//            }catch (Exception exception){
//                log.error("Error de login en: {}", exception.getMessage());
//                response.setHeader("error", exception.getMessage());
//                response.setStatus(FORBIDDEN.value());
////                response.sendError(FORBIDDEN.value());
//                Map<String, String> error = new HashMap<>();
//                error.put("error mensaje: ", exception.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//        }else {
//            throw RuntimeException()
//
//        }
    }
    @Data
    class RoleToUserForm{
        private String username;
        private String roleNombre;
    }
}
