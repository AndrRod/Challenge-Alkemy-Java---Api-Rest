package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeliculaService {
    @Autowired
    PeliculaRepository peliculaRepository;

    @Transactional
    public List<Pelicula> listadoPeliculas (){return peliculaRepository.findAll();}
    @Transactional
    public Optional<Pelicula> detallePelicula(Long idPelicula){return peliculaRepository.findById(idPelicula);}
    @Transactional
    public Pelicula crearPelicula (Pelicula pelicula){return peliculaRepository.save(pelicula);}
    @Transactional
    public void borrarPelicula(Long id){ peliculaRepository.deleteById(id);}
    @Transactional(readOnly = true)
    public List<Pelicula> buscarPeliculaPorNombre(String nombre){ return peliculaRepository.findAll().stream().filter(p-> {return p.getTitulo().toLowerCase(Locale.ROOT).contains(nombre.toLowerCase(Locale.ROOT));}).collect(Collectors.toList());}
    @Transactional(readOnly = true)
    public List<Pelicula> ordenarPeliculasAsc(){ return (List<Pelicula>) peliculaRepository.findAll().stream().sorted();}




}
