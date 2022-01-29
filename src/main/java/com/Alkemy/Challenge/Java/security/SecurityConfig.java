package com.Alkemy.Challenge.Java.security;

import com.Alkemy.Challenge.Java.filter.ConfigAutenticacionFilter;
import com.Alkemy.Challenge.Java.filter.ConfigAutorizacionFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

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
        configAutenticacionFilter.setFilterProcessesUrl("/auth/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/auth/login/**", "/auth/register/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROL_USUARIO");
//        http.authorizeRequests().antMatchers(POST, "/auth/usuarios", "/auth/rol/**").hasAnyAuthority("ROL_ADMIN");
//        http.authorizeRequests().antMatchers(GET, "/**").hasAnyAuthority("ROL_ADMIN");
        http.authorizeRequests().anyRequest()
//                .authenticated();
                .permitAll();
        http.addFilter(configAutenticacionFilter);
//        http.addFilterBefore(new ConfigAutorizacionFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
