package com.Alkemy.Challenge.Java.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

//implements Serializable
@Table
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombreCompleto;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message="no cumple con los requisitos")
    @Getter @Setter @Column(unique = true)
    @NotBlank(message = "no debe estar en blanco.")
    private String email;
    @Getter @Setter @Column(unique = true)
    @NotBlank(message = "no debe estar en blanco.")
    private String username;
    @Getter @Setter
    @NotBlank(message = "no debe estar en blanco.")
    private String contrasenia;
     
    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Rol> roles = new ArrayList<>();
}
