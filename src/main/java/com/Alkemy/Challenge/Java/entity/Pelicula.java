package com.Alkemy.Challenge.Java.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String imagen;

    @Getter @Setter
    @Column(nullable = false)
    private String titulo;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion")
    private Date fechaDeCreacion;

    @Min(value= 1, message = "el minimo de calificacion es 1") @Max(value= 5 ,message = "el máximo de calificacion es 5")
    @Getter @Setter
    private int calificacion;

    //relacion de muchos a muchos con personajes
//    @Getter @Setter
//    @JoinColumn(name = "personaje_nombre")
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Collection<Personaje> personajesAsociados = new ArrayList<>();

    @Getter @Setter
//    @JoinColumn(name = "genero_id")
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Genero> generos = new ArrayList<>();


}
