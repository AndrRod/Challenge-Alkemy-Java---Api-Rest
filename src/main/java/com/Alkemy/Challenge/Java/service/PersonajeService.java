package com.Alkemy.Challenge.Java.service;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.repository.PeliculaRepository;
import com.Alkemy.Challenge.Java.repository.PersonajeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class PersonajeService {
    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    PeliculaRepository peliculaRepository;

    public List<Personaje> obtenerPersonajes(){ return personajeRepository.findAll();}
    public Page<Personaje> listadoPersonajesPaginacion (int page, int size, String sort){return personajeRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(sort)));}

    public Personaje crearPersonaje(Personaje personaje){return personajeRepository.save(personaje);}

    public void borrarPersonaje(Long id){personajeRepository.deleteById(id);}

    public Optional<Personaje> buscarPersonajePorId(Long id){ return personajeRepository.findById(id);}

    public List<Personaje> buscarPersonajePorNombre(String nombre){ return personajeRepository.findAll().stream().filter(p-> {return p.getNombre().toLowerCase(Locale.ROOT).contains(nombre.toLowerCase(Locale.ROOT));}).collect(Collectors.toList());}

    public List<Personaje> buscarPersonajePorEdad(int edad){ return personajeRepository.findAll().stream().filter(p-> {return p.getEdad() == edad;}).collect(Collectors.toList());}

    public List<Personaje> buscarPersonajePorPeso(float peso) { return personajeRepository.findAll().stream().filter(p-> {return p.getPeso() == peso;}).collect(Collectors.toList());}

    public List<Personaje> buscarPorPelicula(Long idPel) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(idPel);
        List<Personaje> personajes = new ArrayList<>();
        for (Personaje x : personajeRepository.findAll()) {
            for (Pelicula g : x.getPeliculas()) {
                if (g.equals(pelicula.get())) {
                    personajes.add(x);
                }
            }
        }
        return personajes;
    }
//    public void agregarPeliculaRelacionada(String nombre, String titulo) {
//    log.info("Agregando pelicula {} y relacionarla al personaje {}.", nombre, titulo);
//    Pelicula pelicula = peliculaRepository.findByTitulo(titulo);
//    Personaje personaje = personajeRepository.findByNombre(nombre);
//    personaje.getPeliculas().add(pelicula);
//}

}
