package com.Alkemy.Challenge.Java.controller;

import com.Alkemy.Challenge.Java.dtos.PeliculaDto;
import com.Alkemy.Challenge.Java.dtos.PersonajeDto;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.service.GeneroService;
import com.Alkemy.Challenge.Java.service.PeliculaService;
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
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PersonajeService personajeService;

    @Autowired
    private GeneroService generoService;

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
        if(pelicula.isEmpty()){return new ResponseEntity<>("No se encuentra ningúna pelicula con el id: " + idPelicula, HttpStatus.NOT_FOUND);}
        return ResponseEntity.ok(pelicula);
    }
    @PostMapping(value = "/crearPelicula/")
    public ResponseEntity<?> crearPelicula(@Valid @RequestBody Pelicula pelicula){
        try{
            peliculaService.crearPelicula(pelicula);
            return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
        }catch(Exception e){
            return new ResponseEntity<>("Hubo un error al crear la pelicula: ", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/eliminarPelicula/{id}")
    public ResponseEntity<?> eliminarPelicula(@PathVariable(value = "id") Long idPelicula){
        if(!peliculaService.buscarPeliculaPorId(idPelicula).isPresent()){
            return new ResponseEntity<>("No se encuentra la pelicula con id: " + idPelicula, HttpStatus.NOT_FOUND);
        }
        String nombrePelicula = peliculaService.buscarPeliculaPorId(idPelicula).get().getTitulo();
        peliculaService.borrarPelicula(idPelicula);
        return ResponseEntity.ok("la pelicula " +  nombrePelicula + " fue borrada exitosamente");
    }
    @PutMapping(value = "/modificarPelicula/{id}")
    public ResponseEntity<?> modifiacarPelicula(@Valid @RequestBody Pelicula peliculaModif, @PathVariable(value = "id") @Valid Long idPeli) {
        Optional<Pelicula> pel = peliculaService.buscarPeliculaPorId(idPeli);
        if (!pel.isPresent()) {return new ResponseEntity<>("Error: no se cuentra ninguna pelicula con el id ingresado: " + idPeli, HttpStatus.NOT_FOUND);}
        pel.get().setCalificacion(peliculaModif.getCalificacion());
        pel.get().setTitulo(peliculaModif.getTitulo());
        pel.get().setImagen(peliculaModif.getImagen());
        pel.get().setGeneros(peliculaModif.getGeneros());
        pel.get().setPersonajesAsociados(peliculaModif.getPersonajesAsociados());
        try{
            peliculaService.crearPelicula(pel.get());
            return new ResponseEntity<>(pel.get(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Error al intentar modificar la pelicula", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<?> buscarPeliculasPor(@RequestParam(value = "name", required = false) String nombre,
                                                @RequestParam(value = "order", required = false) String order){
        List<Pelicula> peliculas;
        try {
            if (nombre != null) {
                peliculas = peliculaService.buscarPeliculaPorNombre(nombre);
                if (peliculas.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ninguna pelicula con el nombre: " + nombre, HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok(peliculas);
            }
            if(order.equals("ASC")) {
                peliculas = peliculaService.ordenarPeliculasAsc();
                if (peliculas.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ninguna pelicula agregada", HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok(peliculas);
            }
            if(order.equals("DESC")) {
                peliculas = peliculaService.ordenarPeliculasDesc();
                if (peliculas.isEmpty()) {
                    return new ResponseEntity<>("No se encuentra ninguna pelicula agregada", HttpStatus.NOT_FOUND);
                }
                return ResponseEntity.ok(peliculas);
            }
        }
        catch (Exception e){
         return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Error: Ingreso un valor no deseado", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("movies/idPelicula/{idPelic}/idPersonaje/{idPers}")
    public ResponseEntity<?> agregarPersonajeAPelicula(@PathVariable(value = "idPers") Long idNombre,
                                                       @PathVariable(value = "idPelic") Long idTitulo){

        String nombre = personajeService.buscarPersonajePorId(idNombre).get().getNombre();
        String titulo = peliculaService.buscarPeliculaPorId(idTitulo).get().getTitulo();
        try{
            peliculaService.agregarPersonajes(titulo, nombre);
            return new ResponseEntity<>(peliculaService.buscarPeliculaPorId(idNombre), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Error al intentar agregar personaje" +nombre+ " a la pelicula " + titulo, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("movies/idPelicula/{idPelic}/idGenero/{idGen}")
    public ResponseEntity<?> agregarGeneroAPelicula(@PathVariable(value = "idGen") Long id,
                                                       @PathVariable(value = "idPelic") Long idTitulo){

        String nombre = generoService.buscarGeneroPorId(id).get().getNombre();
        String titulo = peliculaService.buscarPeliculaPorId(idTitulo).get().getTitulo();
        try{
            peliculaService.agregarGeneroAPelicula(titulo, nombre);
            return new ResponseEntity<>(peliculaService.buscarPeliculaPorId(id), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Error al intentar agregar genero " +nombre+ " a la pelicula " + titulo, HttpStatus.BAD_REQUEST);
        }
    }
}
