package com.Alkemy.Challenge.Java.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table @Data
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String imagen;

    @Getter @Setter
    @Column(nullable = false)
    @NotBlank(message = "no debe estar en blanco.")
    private String titulo;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion")
    private Date fechaDeCreacion;

    @Min(value= 1, message = "el minimo de calificacion es 1") @Max(value= 5 ,message = "el máximo de calificacion es 5")
    @Getter @Setter
    private int calificacion;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @Setter @Getter
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private Collection<Personaje> personajesAsociados = new ArrayList<>();

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Genero> generos = new ArrayList<>();
}
