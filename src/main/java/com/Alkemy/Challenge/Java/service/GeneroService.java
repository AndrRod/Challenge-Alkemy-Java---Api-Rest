package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Genero;
import com.Alkemy.Challenge.Java.exception.BadRequestException;
import com.Alkemy.Challenge.Java.repository.GeneroRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public void borrarGenero(Long id){generoRepository.deleteById(id);}
    public Optional<Genero> buscarGeneroPorId(Long id){return generoRepository.findById(id);}
    public List<Genero> getAllGeneros(){return generoRepository.findAll();}
    public Genero guardarGenero(Genero genero) {
        if(generoRepository.existsByNombre(genero.getNombre())){
            throw new BadRequestException("Genero "+ genero.getNombre() + " ya existe en la base de datos");
        }
        log.info("guardado nuevo genero {} en la base de datos", genero.getNombre());
        return generoRepository.save(genero);
    }
}
//    FUNCION ALTERNATIVA PARA DEVOLVER BOOLEANO SI EXISTE GENERO EN BD
//    public Boolean generoExistente(String genero){
//        return generoRepository.findAll().stream()
//                .anyMatch(genero1 -> genero1.getNombre().equals(genero));
//    }
