package com.Alkemy.Challenge.Java.service;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.exception.NotFoundException;
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

    public List<Personaje> obtenerPersonajes(){
        List<Personaje> list = personajeRepository.findAll();
        if(list.isEmpty()) throw new NotFoundException("ningun personaje agregado");
        return list;
    }
    public Page<Personaje> listadoPersonajesPaginacion (int page, int size, String sort){
        Page<Personaje> page1 = personajeRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(sort)));
        if(page1.isEmpty()) throw new NotFoundException("ningun personaje agregado o encontrado");
        return page1;}

    public Personaje crearPersonaje(Personaje personaje){return personajeRepository.save(personaje);}

    public void borrarPersonaje(Long id){
        personajeRepository.deleteById(id);
    }
    public Optional<Personaje> buscarPersonajePorId(Long id){
        personajeRepository.findById(id).orElseThrow(() -> new NotFoundException("personaje inexsistente"));
        return personajeRepository.findById(id);}

    public List<Personaje> buscarPersonajePorNombre(String nombre){
        return ifIsEmpty(personajeRepository.findAll().stream()
                .filter(p-> {return p.getNombre().toLowerCase(Locale.ROOT)
                    .contains(nombre.toLowerCase(Locale.ROOT));})
                        .collect(Collectors.toList()));};

    public List<Personaje> buscarPersonajePorEdad(int edad){ return ifIsEmpty(personajeRepository.findAll().stream().filter(p-> {return p.getEdad() == edad;}).collect(Collectors.toList()));}

    public List<Personaje> buscarPersonajePorPeso(float peso) { return ifIsEmpty(personajeRepository.findAll().stream().filter(p-> {return p.getPeso() == peso;}).collect(Collectors.toList()));}

    public List<Personaje> buscarPorPelicula(String tituloPelicula) {
        Pelicula pelicula = peliculaRepository.findByTitulo(tituloPelicula);
        List<Personaje> personajes = new ArrayList<>();
        for (Personaje x : personajeRepository.findAll()) {
            for (Pelicula g : x.getPeliculas()) {
                if (g.equals(pelicula)) {
                    personajes.add(x);
                }
            }
        }
        return ifIsEmpty(personajes);
    }
//    public List<Personaje> buscarPorPelicula(Long idPel) {
//        Optional<Pelicula> pelicula = peliculaRepository.findById(idPel);
//        List<Personaje> personajes = new ArrayList<>();
//        for (Personaje x : personajeRepository.findAll()) {
//            for (Pelicula g : x.getPeliculas()) {
//                if (g.equals(pelicula.get())) {
//                    personajes.add(x);
//                }
//            }
//        }
//        return ifIsEmpty(personajes);
//    }

    public Optional<Personaje> modifPersonaje (Long idPersonaje, Personaje personajeModif){
        Optional<Personaje> personaje = buscarPersonajePorId(idPersonaje);
        personaje.get().setEdad(personajeModif.getEdad());
        personaje.get().setHistoria(personajeModif.getHistoria());
        personaje.get().setImagen(personajeModif.getImagen());
//        personaje.get().setPeliculas(personajeModif.getPeliculas());
        personaje.get().setNombre(personajeModif.getNombre());
        crearPersonaje(personaje.get());
        return personaje;
    }

    public List<Personaje> ifIsEmpty(List<Personaje> personajeList){
        if(personajeList.isEmpty()) throw new NotFoundException("ningun personaje agregado o encontrado");
        return personajeList;
    }
//    public void agregarPeliculaRelacionada(String nombre, String titulo) {
//    log.info("Agregando pelicula {} y relacionarla al personaje {}.", nombre, titulo);
//    Pelicula pelicula = peliculaRepository.findByTitulo(titulo);
//    Personaje personaje = personajeRepository.findByNombre(nombre);
//    personaje.getPeliculas().add(pelicula);
//}

}
