package com.Alkemy.Challenge.Java.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String imagen;

    @Getter @Setter
    private String nombre;

//    //asociar con otra tabla
//    @Getter @Setter
//    private String peliculas;


    public Genero(String imagen, String nombre){
        this.imagen = imagen;
        this.nombre = nombre;

    }
    public Genero() {
    }
}
