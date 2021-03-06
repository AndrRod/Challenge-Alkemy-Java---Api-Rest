package com.Alkemy.Challenge.Java.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class ConfigAutenticacionFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;
    public ConfigAutenticacionFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username es: {}", username);
        log.info("Password es: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        log.warn("toquen", authenticationManager.authenticate(authenticationToken));
        return authenticationManager.authenticate(authenticationToken);
    }
// si la autenticacion que se recibe de arriba es exitosa se llama al sigte.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        usuario que viene de la autenticacion validada
        User user = (User) authentication.getPrincipal();
//        este es el algoritmo por donde se va a mandar el jwt
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//        token de acceso
        String acceso_token = JWT.create()
//                tiene que ser un elemento unico (username)
                .withSubject(user.getUsername())
//                configura la expiracion (ExpireAt)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutos
//                en esta parte se pasa la url de la aplicacion
                .withIssuer(request.getRequestURL().toString())
//                devuelve las autorizacion que tiene el usuario
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String actualizar_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minutos
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
//        response.setHeader("acceso_token", acceso_token);
//        response.setHeader("actualizar_token", actualizar_token);
//        mandamos al body el token en forma de json
        Map<String, String> tokens = new HashMap<>();
        tokens.put("acceso_token", acceso_token);
        tokens.put("actualizar_token", actualizar_token);
//        se realiza conversion a json
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
