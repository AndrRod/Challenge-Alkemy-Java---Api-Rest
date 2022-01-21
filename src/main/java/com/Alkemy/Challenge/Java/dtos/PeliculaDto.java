package com.Alkemy.Challenge.Java.dtos;

import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

public class PeliculaDto {

    @Getter @Setter
    private String imagen;

    @Getter @Setter
    private String titulo;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Getter @Setter @Column(name = "fechaCreacion")
    private Date fechaDeCreación;

    public PeliculaDto(String imagen, Date fechaDeCreación, String titulo){
        this.imagen = imagen;
        this.fechaDeCreación = fechaDeCreación;
        this.titulo = titulo;
    }
    public PeliculaDto(){}

    public static PeliculaDto peliculaADto(Pelicula pelicula){
        PeliculaDto dto = new PeliculaDto();
        dto.setImagen(pelicula.getImagen());
        dto.setTitulo(pelicula.getTitulo());
        dto.setFechaDeCreación(pelicula.getFechaDeCreacion());
        return dto;
    }
}
