package com.Alkemy.Challenge.Java.entity;
import com.Alkemy.Challenge.Java.enums.RolEnum;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table @Data @NoArgsConstructor @AllArgsConstructor
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
//     @Enumerated(EnumType.STRING)
//     @Setter @Getter
//     private RolEnum nombre;
    @Setter @Getter
    @Column(unique = true, name = "nombre")
    private String nombre;
}
