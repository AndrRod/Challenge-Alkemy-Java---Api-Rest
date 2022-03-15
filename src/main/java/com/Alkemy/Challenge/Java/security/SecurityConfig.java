package com.Alkemy.Challenge.Java.security;

import com.Alkemy.Challenge.Java.filter.ConfigAutenticacionFilter;
import com.Alkemy.Challenge.Java.filter.ConfigAutorizacionFilter;
import com.Alkemy.Challenge.Java.repository.UsuarioRepository;
import com.Alkemy.Challenge.Java.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        llamamos a la clase configuracionAutenticacionFilter creada en package filter y la funcion authenticationManagerBean llamada al final
        ConfigAutenticacionFilter configAutenticacionFilter = new ConfigAutenticacionFilter(authenticationManagerBean());
        configAutenticacionFilter.setFilterProcessesUrl("/auth/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/auth/login", "/auth/register").permitAll();
        //        FALTA IMPLEMENTAR ROLES:
//        http.authorizeRequests().antMatchers(GET    , "/api/user/**").hasAnyAuthority("ROL_USUARIO");
//        http.authorizeRequests().antMatchers(POST,  "/auth/rol/guardar", "/auth/rol/agregarAUsuario", "/auth/register").hasAnyAuthority("ROL_ADMIN");
//        http.authorizeRequests().antMatchers(DELETE, "/eliminarPelicula/**", "/eliminarPelicula/**", "/eliminarPelicula/**").hasAnyAuthority("ROL_ADMIN");
//        http.authorizeRequests().antMatchers(GET, "/auth/usuarios/", "/eliminarPelicula/**", "/eliminarPelicula/**").hasAnyAuthority("ROL_ADMIN");
        http.authorizeRequests().anyRequest()
//                 .authenticated();
                .permitAll();
        http.addFilter(configAutenticacionFilter);
//        userpasswordAutentication es rellenado cuando es rellenado el token (en clase ConfigAutenticationFilter)
//         http.addFilterBefore(new ConfigAutorizacionFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
