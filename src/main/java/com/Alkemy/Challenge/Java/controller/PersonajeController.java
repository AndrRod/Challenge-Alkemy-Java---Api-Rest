package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.dtos.PersonajeDto;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.service.PersonajeService;
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
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;

    @GetMapping(value = "/characters/")
    public ResponseEntity<?> obtenerTodosPersonajes(){
        List<Personaje> personajes = personajeService.obtenerPersonajes();
        List<PersonajeDto> listaDtosPersonaje = new ArrayList<>();
        if(!personajes.isEmpty()) {
            for (Personaje s : personajes) listaDtosPersonaje.add(PersonajeDto.personajeADto(s));
            return ResponseEntity.ok(listaDtosPersonaje);
        }
        //return ResponseEntity.ok(personajes);
        return new ResponseEntity<>("No existe ningún personaje agregado", HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/crearPersonaje")
    public ResponseEntity<?> crearPersonaje(@Valid @RequestBody Personaje personaje){
        try{
            personajeService.crearPersonaje(personaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
        }catch(Exception e){
            return new ResponseEntity<>("Hubo un error al crear el usuario", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value= "/borrarPersonaje/{id}")
    public ResponseEntity<?> borrarPersonaje(@Valid @PathVariable(value = "id") Long idPersonaje){
        if(!personajeService.buscarPersonajePorId(idPersonaje).isPresent()){
            return new ResponseEntity<>("No se encuentra el personaje con id: " + idPersonaje, HttpStatus.NOT_FOUND);
        }
        personajeService.borrarPersonaje(idPersonaje);
        return ResponseEntity.ok("usuario borrado exitosamente");
    }
    @PutMapping(value = "/modificarPersonaje/{id}")
    public ResponseEntity<?> modificarPersonaje(@Valid @RequestBody Personaje personajeModif, @PathVariable(value = "id") @Valid Long idPersonaje) {
        Optional<Personaje> personaje = personajeService.buscarPersonajePorId(idPersonaje);
    if (!personaje.isPresent()) {return new ResponseEntity<>("Error: no se cuentra ningun usuario con el id ingresado: " + idPersonaje, HttpStatus.NOT_FOUND);}
         personaje.get().setEdad(personajeModif.getEdad());
        personaje.get().setHistoria(personajeModif.getHistoria());
        personaje.get().setImagen(personajeModif.getImagen());
        personaje.get().setPeliculas(personajeModif.getPeliculas());
        personaje.get().setNombre(personajeModif.getNombre());
    try{
        personajeService.crearPersonaje(personaje.get());
        return new ResponseEntity<>(personaje.get(), HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>("Error al intentar modificar al personaje", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/detallePersonaje/{id}")
    ResponseEntity<?> detallesPersonajeId(@PathVariable (value = "id") @Valid Long idPersonaje){
        Optional<Personaje> personaje = personajeService.buscarPersonajePorId(idPersonaje);
        if(personaje.isEmpty()){return new ResponseEntity<>("No se encuentra ningún personaje con el id: " + idPersonaje, HttpStatus.NOT_FOUND);}
        return ResponseEntity.ok(personaje);
    }
    @GetMapping(value = "/characters")
    ResponseEntity<?> buscarPorNombre(@RequestParam(value = "name", required = false) String nombre,
                                      @RequestParam(value = "edad", required = false, defaultValue = "0") int edad,
                                      @RequestParam(value = "peso", required = false, defaultValue = "0") float peso){
        List<Personaje> personajes = null;
        try {
            if (nombre != null) {
                personajes = personajeService.buscarPersonajePorNombre(nombre);
                if (personajes.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ningun personaje con el nombre: " + nombre, HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>("Error: hubo un error al ingresar los datos, reveer los datos ingresados", HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>("No se encuentra ningún usuario registrado con el dato ingresado", HttpStatus.BAD_REQUEST);
    }
    /*
    @GetMapping(value = "/characters/edad")
    ResponseEntity<?> buscarPorEdad(@RequestParam(value = "edad") int edad) {
        List<Personaje> personajes = personajeService.buscarPersonajePorEdad(edad);
        if (personajes.isEmpty()) {
            return new ResponseEntity<>("No se encuentra ningun personaje con el nombre: " + edad, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(personajes);
    }

     */
}



