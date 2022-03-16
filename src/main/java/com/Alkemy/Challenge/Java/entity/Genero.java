package com.Alkemy.Challenge.Java.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Entity
@Table
public class Genero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String imagen;
    @Getter @Setter @Column(unique = true)
    @NotBlank(message = "no debe estar en blanco.")
    private String nombre;

    public Genero(String imagen, String nombre){
        this.imagen = imagen;
        this.nombre = nombre;
    }
    public Genero() {
    }
}
