package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.entity.Genero;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.exception.BadRequestException;
import com.Alkemy.Challenge.Java.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generos")
public class GeneroController {
    @Autowired
    private GeneroService generoService;
    @PostMapping
    public ResponseEntity<?> crearGenero(@Valid @RequestBody Genero genero){
        try{
            generoService.guardarGenero(genero);
            return ResponseEntity.status(HttpStatus.CREATED).body(genero);
        }catch(Exception e){
            return new ResponseEntity<>("Hubo un error al crear el genero: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerPeliculas(){
        List<Genero> generos = generoService.getAllGeneros();
        if(!generos.isEmpty()) {
            return ResponseEntity.ok(generos);
        }
        return new ResponseEntity<>("No existe nigun genero agregado", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<?> generoPorId(@PathVariable(value = "id") @Valid Long id){
        Optional<Genero> genero = generoService.buscarGeneroPorId(id);
        if(!genero.isPresent()){return new ResponseEntity<>("No se encuentra ningún genero con el id: " + id, HttpStatus.NOT_FOUND);}
        return ResponseEntity.ok(genero);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminarGenero(@PathVariable(value = "id") Long id){
        if(!generoService.buscarGeneroPorId(id).isPresent()){
            return new ResponseEntity<>("No se encuentra el genero con id: " + id, HttpStatus.NOT_FOUND);
        }
        String genero = generoService.buscarGeneroPorId(id).get().getNombre();
        generoService.borrarGenero(id);
        return ResponseEntity.ok("el genero " +  genero + " fue borrado exitosamente");
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> modifiacarGenero(@Valid @RequestBody Genero genero, @PathVariable(value = "id") @Valid Long idGenero) {
        Optional<Genero> gen = generoService.buscarGeneroPorId(idGenero);
        if (gen.isEmpty()) {return new ResponseEntity<>("Error: no se cuentra ningun genero con el id ingresado: " + idGenero, HttpStatus.NOT_FOUND);}
        gen.get().setNombre(genero.getNombre());
        gen.get().setImagen(genero.getImagen());

        try{
            generoService.guardarGenero(gen.get());
            return new ResponseEntity<>(gen.get(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Error al intentar modificar el genero", HttpStatus.BAD_REQUEST);
        }
    }
}
