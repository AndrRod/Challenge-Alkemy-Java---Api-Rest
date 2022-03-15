package com.Alkemy.Challenge.Java.entity;
import com.Alkemy.Challenge.Java.enums.RolEnum;
import lombok.*;
import javax.persistence.*;

@Entity
@Table @Data @NoArgsConstructor @AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
//     @Enumerated(EnumType.STRING)
//     @Setter @Getter
//     public RolEnum nombre;
    @Setter @Getter
    @Column(unique = true)
    private String nombre;
}
