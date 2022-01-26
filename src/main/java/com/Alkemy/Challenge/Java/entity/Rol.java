package com.Alkemy.Challenge.Java.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table @Data @NoArgsConstructor @AllArgsConstructor
public class Rol {

//    @Column(name = "id")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String nombre;

}
