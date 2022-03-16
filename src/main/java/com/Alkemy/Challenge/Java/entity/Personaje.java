package com.Alkemy.Challenge.Java.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table @Data
public class Personaje implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    @NotBlank(message = "no debe estar en blanco.")
    @NotNull
    private String nombre;
    @Getter @Setter
    private float peso;
    @Getter @Setter
    private int edad;
    @Getter @Setter
    @NotBlank(message = "no debe estar en blanco.")
    private String historia;
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @Setter @Getter
    @ManyToMany(mappedBy = "personajesAsociados")
    private Collection<Pelicula> peliculas = new ArrayList<>();

}
