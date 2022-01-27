package com.Alkemy.Challenge.Java.service;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.repository.PeliculaRepository;
import com.Alkemy.Challenge.Java.repository.PersonajeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Transactional
@Service
@Slf4j
public class PersonajeService {
    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    PeliculaRepository peliculaRepository;

    public List<Personaje> obtenerPersonajes(){ return personajeRepository.findAll();}

    public Personaje crearPersonaje(Personaje personaje){return personajeRepository.save(personaje);}

    public void borrarPersonaje(Long id){personajeRepository.deleteById(id);}

    public Optional<Personaje> buscarPersonajePorId(Long id){ return personajeRepository.findById(id);}

    public List<Personaje> buscarPersonajePorNombre(String nombre){ return personajeRepository.findAll().stream().filter(p-> {return p.getNombre().toLowerCase(Locale.ROOT).contains(nombre.toLowerCase(Locale.ROOT));}).collect(Collectors.toList());}

    public List<Personaje> buscarPersonajePorEdad(int edad){ return personajeRepository.findAll().stream().filter(p-> {return p.getEdad() == edad;}).collect(Collectors.toList());}

    public List<Personaje> buscarPersonajePorPeso(float peso) { return personajeRepository.findAll().stream().filter(p-> {return p.getPeso() == peso;}).collect(Collectors.toList());}

//    public List<Personaje> findByPeliculasAsociadas(String pelicula)
    public void agregarPeliculaRelacionada(String nombre, String titulo) {
    log.info("Agregando pelicula {} y relacionarla al personaje {}.", nombre, titulo);
    Pelicula pelicula = peliculaRepository.findByTitulo(titulo);
    Personaje personaje = personajeRepository.findByNombre(nombre);
    pelicula.getPersonajesAsociados().add(personaje);
}
}
