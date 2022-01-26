package com.Alkemy.Challenge.Java.entity;

import lombok.*;

import javax.persistence.*;
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
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String contrasenia;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Rol> roles = new ArrayList<>();


}
