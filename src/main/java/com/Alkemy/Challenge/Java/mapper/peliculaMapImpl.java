package com.Alkemy.Challenge.Java.mapper;

import com.Alkemy.Challenge.Java.dtos.PeliculaDto;
import com.Alkemy.Challenge.Java.entity.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class peliculaMapImpl implements PeliculaMap {

    @Override
    public PeliculaDto toPeliculaDto(Pelicula pelicula) {
        PeliculaDto peliculaDto = new PeliculaDto();
        peliculaDto.setImagen(pelicula.getImagen());
        peliculaDto.setFechaDeCreacion(pelicula.getFechaDeCreacion());
        peliculaDto.setTitulo(pelicula.getTitulo());
        return peliculaDto;
    }
    @Override
    public List<PeliculaDto> listPeliculaDto(List<Pelicula> peliculaList) {
        List<PeliculaDto> peliculaDtos = new ArrayList<>();
        peliculaList.forEach(pelicula -> peliculaDtos.add(toPeliculaDto(pelicula)));
        return peliculaDtos;
    }
    @Override
    public Pelicula toPelicula(PeliculaDto peliculaDto) {
        Pelicula
                pelicula  = new Pelicula ();
        pelicula.setImagen(peliculaDto.getImagen());
        pelicula.setFechaDeCreacion(peliculaDto.getFechaDeCreacion());
        pelicula.setTitulo(peliculaDto.getTitulo());
        return pelicula;
    }


}
