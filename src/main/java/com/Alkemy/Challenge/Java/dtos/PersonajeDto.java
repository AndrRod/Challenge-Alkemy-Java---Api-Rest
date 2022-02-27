package com.Alkemy.Challenge.Java.dtos;

import com.Alkemy.Challenge.Java.entity.Personaje;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class PersonajeDto implements Serializable {
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private String nombre;
    public PersonajeDto(String imagen, String nombre){
        this.imagen = imagen;
        this.nombre = nombre;
    }
    public PersonajeDto(){}
    public static PersonajeDto personajeADto(Personaje personaje){
        PersonajeDto dto = new PersonajeDto();
        dto.setNombre(personaje.getNombre());
        dto.setImagen(personaje.getImagen());
        return dto;
    }
}
