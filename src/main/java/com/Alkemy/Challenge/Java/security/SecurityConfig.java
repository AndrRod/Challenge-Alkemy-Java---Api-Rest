package com.Alkemy.Challenge.Java.security;

import com.Alkemy.Challenge.Java.filter.ConfigAutenticacionFilter;
import lombok.RequiredArgsConstructor;
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

import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ConfigAutenticacionFilter configAutenticacionFilter = new ConfigAutenticacionFilter(authenticationManagerBean());
        configAutenticacionFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers("/api/login/**", "api/usuarios/guardar").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/usuarios/**").hasAnyAuthority("ROL_USUARIO");
//        http.authorizeRequests().antMatchers(POST, "/api/usuarios/guardar/**").hasAnyAuthority("ROL_ADMIN");
        http.authorizeRequests().anyRequest()
//                .authenticated();
                .permitAll();
//        http.addFilter(new ConfigAutenticacionFilter(authenticationManagerBean()));
        http.addFilter(configAutenticacionFilter);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
