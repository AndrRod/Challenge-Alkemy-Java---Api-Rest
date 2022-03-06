package com.Alkemy.Challenge.Java.dtos;

import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;
import java.io.Serializable;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class PeliculaDto implements Serializable{
    @Getter
    @Setter
    private String imagen;
    @Getter
    @Setter
    private String titulo;
    @Getter
    @Setter
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaDeCreacion;
}
