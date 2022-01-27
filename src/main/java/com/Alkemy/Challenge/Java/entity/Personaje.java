package com.Alkemy.Challenge.Java.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Personaje {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private float peso;
    @Getter @Setter
    private int edad;
    @Getter @Setter
    private String historia;
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @Setter @Getter
    @ManyToMany(mappedBy = "personajesAsociados")
    private Collection<Pelicula> peliculas = new ArrayList<>();

}
