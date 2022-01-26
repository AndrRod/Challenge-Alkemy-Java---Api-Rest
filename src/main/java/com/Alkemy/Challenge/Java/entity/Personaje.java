package com.Alkemy.Challenge.Java.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    //asociar de muchos a muchos con pelicula
//    @Getter @Setter
//    @ManyToMany(cascade = CascadeType.ALL)
//    private Collection<Pelicula> peliculas = new ArrayList<>();


//    @Getter @Setter
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "pelicula_id")
//    private Collection<Pelicula> peliculas = new ArrayList<>();
//
//    @Getter @Setter
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JoinColumn(name = "pelicula_id")
//    private Pelicula peliculas;


}
