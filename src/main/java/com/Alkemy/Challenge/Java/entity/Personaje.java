package com.Alkemy.Challenge.Java.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personaje")
public class Personaje {
    @Id
    @Column(name = "id", nullable = false)
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

    //asociar con otra tabla
    @Getter @Setter
    private String peliculas;

    public Personaje(String imagen, String nombre, float peso, int edad, String historia, String peliculas){
        this.imagen = imagen;
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
        this.historia = historia;
        this.peliculas = peliculas;
    }
    public Personaje() {
    }
}
