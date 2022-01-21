package com.Alkemy.Challenge.Java.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String imagen;

    @Getter @Setter
    private String titulo;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion")
    private Date fechaDeCreacion;

    @Min(value= 1, message = "el minimo de calificacion es 1") @Max(value= 5 ,message = "el máximo de calificacion es 5")
    @Getter @Setter
    private int calificacion;

    //relacion de muchos a muchos con personajes
    @Getter @Setter
    private String personajesAsociados;

    public Pelicula(String imagen, int calificacion, String personajesAsociados, String titulo){
        this.imagen = imagen;
        this.calificacion = calificacion;
        this.personajesAsociados = personajesAsociados;
        this.titulo = titulo;
    }
    public Pelicula() {
    }
}
