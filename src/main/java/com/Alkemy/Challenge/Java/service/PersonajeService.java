package com.Alkemy.Challenge.Java.service;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonajeService {
    @Autowired
    PersonajeRepository personajeRepository;
    @Transactional(readOnly = true)
    public List<Personaje> obtenerPersonajes(){ return personajeRepository.findAll();}
    @Transactional
    public Personaje crearPersonaje(Personaje personaje){return personajeRepository.save(personaje);}
    @Transactional
    public void borrarPersonaje(Long id){personajeRepository.deleteById(id);}
    @Transactional(readOnly = true)
    public Optional<Personaje> buscarPersonajePorId(Long id){ return personajeRepository.findById(id);}
    @Transactional(readOnly = true)
    public List<Personaje> buscarPersonajePorNombre(String nombre){ return personajeRepository.findAll().stream().filter(p-> {return p.getNombre().toLowerCase(Locale.ROOT).contains(nombre.toLowerCase(Locale.ROOT));}).collect(Collectors.toList());}
    @Transactional(readOnly = true)
    public List<Personaje> buscarPersonajePorEdad(int edad){ return personajeRepository.findAll().stream().filter(p-> {return p.getEdad() == edad;}).collect(Collectors.toList());}
    @Transactional(readOnly = true)
    public List<Personaje> buscarPersonajePorPeso(float peso) { return personajeRepository.findAll().stream().filter(p-> {return p.getPeso() == peso;}).collect(Collectors.toList());}
}
