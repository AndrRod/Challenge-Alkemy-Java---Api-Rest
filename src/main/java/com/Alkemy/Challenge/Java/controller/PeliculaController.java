package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.dtos.PeliculaDto;
import com.Alkemy.Challenge.Java.dtos.PersonajeDto;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping(value = "/movies/")
    public ResponseEntity<?> obtenerPeliculas(){
        List<Pelicula> peliculas = peliculaService.listadoPeliculas();
        List<PeliculaDto> listaDtosPeliculas = new ArrayList<>();
        if(!peliculas.isEmpty()) {
            for (Pelicula p : peliculas) listaDtosPeliculas.add(PeliculaDto.peliculaADto(p));
            return ResponseEntity.ok(listaDtosPeliculas);
        }
        //return ResponseEntity.ok(personajes);
        return new ResponseEntity<>("No existe ninguna pelicula agregada", HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/detallePelicula/{id}")
    ResponseEntity<?> detallesPeliculaId(@PathVariable(value = "id") @Valid Long idPelicula){
        Optional<Pelicula> pelicula = peliculaService.detallePelicula(idPelicula);
        if(pelicula.isEmpty()){return new ResponseEntity<>("No se encuentra ningún personaje con el id: " + idPelicula, HttpStatus.NOT_FOUND);}
        return ResponseEntity.ok(pelicula);
    }
    @PostMapping(value = "/crearPelicula/")
    public ResponseEntity<?> crearPelicula(@Valid @RequestBody Pelicula pelicula){
        try{
            peliculaService.crearPelicula(pelicula);
            return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
        }catch(Exception e){
            return new ResponseEntity<>("Hubo un error al crear la pelicula: " + e, HttpStatus.BAD_REQUEST);
        }
    }


}
