package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.dtos.PersonajeDto;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.exception.ResponseInfo;
import com.Alkemy.Challenge.Java.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;

    private PersonajeDto personajeDto;

    @GetMapping(value = "/characters/")
    public ResponseEntity<?> obtenerTodosPersonajes(){
            return ResponseEntity.ok(personajeDto.ListaPersonajeDto(personajeService.obtenerPersonajes()));
    }
    @GetMapping(value = "/characters/{page}/{size}/{sort}")
    public ResponseEntity<?> obtenerTodosPersonajesPaginacion(@PathVariable int page, @PathVariable int size, @PathVariable String sort){
            Page<Personaje> personajes = personajeService.listadoPersonajesPaginacion(page, size, sort);
            return ResponseEntity.ok(personajeDto.ListaPersonajeDto(personajes.getContent()));
    }
    @PostMapping(value = "/crearPersonaje")
    public ResponseEntity<?> crearPersonaje(@Valid @RequestBody Personaje personaje){
            personajeService.crearPersonaje(personaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
    }
    @DeleteMapping(value= "/borrarPersonaje/{id}")
    public ResponseEntity<?> borrarPersonaje(@Valid @PathVariable(value = "id") Long idPersonaje, WebRequest webRequest){
        Optional<Personaje> personaje = personajeService.buscarPersonajePorId(idPersonaje);
        personajeService.borrarPersonaje(idPersonaje);
        return ResponseEntity.ok().body(new ResponseInfo(personaje.get().getId(), ((ServletWebRequest)webRequest).getRequest().getRequestURI(), "Personaje borrado exitosamente"));
    }
    @PutMapping(value = "/modificarPersonaje/{id}")
    public ResponseEntity<?> modificarPersonaje(@Valid @RequestBody Personaje personajeModif, @PathVariable(value = "id") @Valid Long idPersonaje) {//
        return new ResponseEntity<>(personajeService.modifPersonaje(idPersonaje, personajeModif), HttpStatus.OK);
    }
    @GetMapping(value = "/detallePersonaje/{id}")
    public ResponseEntity<?> detallesPersonajeId(@PathVariable (value = "id") @Valid Long idPersonaje){
        Optional<Personaje> personaje = personajeService.buscarPersonajePorId(idPersonaje);
        return ResponseEntity.ok(personaje);
    }
    @GetMapping(value = "/characters")
    public ResponseEntity<?> buscarPorNombre(@RequestParam(value = "name", required = false) String nombre,
                                     @RequestParam(value = "movies", required = false, defaultValue = "0") Long idPel,
                                      @RequestParam(value = "age", required = false, defaultValue = "0") int edad,
                                      @RequestParam(value = "weight", required = false, defaultValue = "0") float peso){
        List<Personaje> personajes = null;
        try {
            if (nombre != null) {
                personajes = personajeService.buscarPersonajePorNombre(nombre);
                if (personajes.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ningun personaje con el nombre: " + nombre, HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok(personajes);
            }
            if (idPel != 0) {
                personajes = personajeService.buscarPorPelicula(idPel);
                if (personajes.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ningun personaje asociado a una pelicula con el id: " + idPel, HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok(personajes);
            }
            if (edad != 0) {
                personajes = personajeService.buscarPersonajePorEdad(edad);
                if (personajes.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ningun personaje con la edad de: " + edad, HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok(personajes);
            }
            if (peso != 0) {
                personajes = personajeService.buscarPersonajePorPeso(peso);
                if (personajes.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ningun personaje con el peso de: " + peso, HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok(personajes);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("Error: " + e.getMessage() , HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>("No se encuentra ningún usuario registrado con el dato ingresado", HttpStatus.BAD_REQUEST);
    }
    @GetMapping(value = "/characters/p")
    public ResponseEntity<?> buscarPorNombre(@RequestParam(value = "movies", required = false, defaultValue = "0") Long idPel){
        return ResponseEntity.ok(personajeService.buscarPorPelicula(idPel));
    }
}



