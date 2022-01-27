package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Genero;
import com.Alkemy.Challenge.Java.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class GeneroService {
    @Autowired
    GeneroRepository generoRepository;
    public Genero crearGenero(Genero genero){ return generoRepository.save(genero);}
    public void borrarGenero(Long id){generoRepository.deleteById(id);}
    public Optional<Genero> buscarGeneroPorId(Long id){return generoRepository.findById(id);}
    public List<Genero> getAllGeneros(){return generoRepository.findAll();}
}
