package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.*;
import com.Alkemy.Challenge.Java.repository.GeneroRepository;
import com.Alkemy.Challenge.Java.repository.PeliculaRepository;
import com.Alkemy.Challenge.Java.repository.PersonajeRepository;
import com.Alkemy.Challenge.Java.repository.RolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Transactional
@Service
@Slf4j
public class PeliculaService {
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    GeneroRepository generoRepository;
    @Autowired
    PersonajeRepository personajeRepository;


    public List<Pelicula> listadoPeliculas (){return peliculaRepository.findAll();}

    public Optional<Pelicula> detallePelicula(Long idPelicula){return peliculaRepository.findById(idPelicula);}

    public Pelicula crearPelicula (Pelicula pelicula){return peliculaRepository.save(pelicula);}

    public void borrarPelicula(Long id){ peliculaRepository.deleteById(id);}

    public Optional<Pelicula> buscarPeliculaPorId(Long id){return peliculaRepository.findById(id);}

    public List<Pelicula> buscarPeliculaPorNombre(String nombre){ return peliculaRepository.findAll().stream().filter(p-> {return p.getTitulo().toLowerCase(Locale.ROOT).contains(nombre.toLowerCase(Locale.ROOT));}).collect(Collectors.toList());}

    public List<Pelicula> ordenarPeliculasAsc(){
        List<Pelicula> peliAsc = peliculaRepository.findAll();
        Collections.sort(peliAsc, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula o1, Pelicula o2) {
                return o1.getFechaDeCreacion().compareTo(o2.getFechaDeCreacion());
            }
        });
        return peliAsc;
    }


    public List<Pelicula> ordenarPeliculasDesc(){
        List<Pelicula> peliDesc = peliculaRepository.findAll();
        return peliDesc.stream().sorted(Comparator.comparing(Pelicula::getFechaDeCreacion).reversed())
                .collect(Collectors.toList());
    }

    public Genero guardarGenero(Genero genero) {
        log.info("guardado nuevo genero {} en la base de datos", genero.getNombre());
        return generoRepository.save(genero);
    }

    public void agregarGeneroAPelicula(String titulo, String nombre) {
        log.info("Agregando Genero {} a la pelicula {}.", nombre, titulo);
        Pelicula pelicula = peliculaRepository.findByTitulo(titulo);
        Genero genero = generoRepository.findByNombre(nombre);
        pelicula.getGeneros().add(genero);
    }

//    ver despues
    public void agregarPersonajes(String titulo, String nombre) {
        log.info("Agregando personaje {} a la pelicula {}.", nombre, titulo);
        Pelicula pelicula = peliculaRepository.findByTitulo(titulo);
        Personaje personaje = personajeRepository.findByNombre(nombre);
        pelicula.getPersonajesAsociados().add(personaje);
    }



}