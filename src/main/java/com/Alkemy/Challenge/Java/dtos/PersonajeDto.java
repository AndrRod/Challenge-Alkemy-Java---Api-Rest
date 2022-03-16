package com.Alkemy.Challenge.Java.dtos;

import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    public static List<PersonajeDto> ListaPersonajeDto(List<Personaje> personajes){
        List<PersonajeDto> personajeDtos = new ArrayList<>();
        personajes.forEach(personaje -> personajeDtos.add(PersonajeDto.personajeADto(personaje)));
        return personajeDtos;
    }
}
