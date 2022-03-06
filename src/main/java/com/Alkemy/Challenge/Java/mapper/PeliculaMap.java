package com.Alkemy.Challenge.Java.mapper;

import com.Alkemy.Challenge.Java.dtos.PeliculaDto;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
//        (componentModel = "spring")
@Mapper(componentModel = "spring")
public interface PeliculaMap {
    PeliculaMap INSTANCE = Mappers.getMapper(PeliculaMap.class);

    //    @Mappings({
    //                @Mapping(target = "titulo", source = "titulo"),
    //                @Mapping(target = "imagen", source = "imagen"),
    //                @Mapping(target = "fechaDeCreacion", source = "fechaDeCreacion")})
    PeliculaDto toPeliculaDto(Pelicula pelicula);

//    @Mappings({
//            @Mapping(target = "titulo", source = "titulo"),
//            @Mapping(target = "imagen", source = "imagen"),
//            @Mapping(target = "fechaDeCreacion", source = "fechaDeCreacion")})
    List<PeliculaDto> listPeliculaDto(List<Pelicula> peliculaList);

//    @Mappings({
//            @Mapping(target = "id", ignore = true),
//            @Mapping(target = "personajesAsociados", ignore = true),
//            @Mapping(target = "titulo", source = "titulo"),
//            @Mapping(target = "imagen", source = "imagen"),
//            @Mapping(target = "fechaDeCreacion", source = "fechaDeCreacion")})
    Pelicula toPelicula(PeliculaDto peliculaDto);
}
