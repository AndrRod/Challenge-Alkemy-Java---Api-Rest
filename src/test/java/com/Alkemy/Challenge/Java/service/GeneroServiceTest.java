package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Genero;
import com.Alkemy.Challenge.Java.exception.BadRequestException;
import com.Alkemy.Challenge.Java.repository.GeneroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeneroServiceTest {

//    Mockito: de esta manera burlamos al Repository para no usar sus metodos, porque sabemos que funciona segun la prueba que se hizo a nivel repository
    @Mock
    private GeneroRepository generoRepository;
    private GeneroService underTest;

    @BeforeEach
    void setUp(){
        underTest = new GeneroService(generoRepository);
    }

    @Test
    void getAllGeneros() {
//        when
        underTest.getAllGeneros();
//        then
        verify(generoRepository).findAll();
//        por ejemplo si queremos realizar un delete, la terminal nos dira que fue querido y no invocado
//        porque el mock fue invocado en findAll()
//        verify(generoRepository).deleteAll();
    }
    @Test
    void crearGenero() {
        //        given
        Genero genero = new Genero(
                "http//dirimagen.com", "terror");

//        when
        underTest.guardarGenero(genero);
//        then
        ArgumentCaptor<Genero> generoArgumentCaptor =
                ArgumentCaptor.forClass(Genero.class);

//        en esta instancia vamos a repository lo verificamos (verify) y capturamos el valor guardado (.save(caputre()))
        verify(generoRepository)
                .save(generoArgumentCaptor.capture());

        Genero capturaGenero = generoArgumentCaptor.getValue();

        assertThat(capturaGenero).isEqualTo(genero);
//        hace una comparacion entre el genero ingresado al service y el ingresado al repository.save()
    }

    @Test
    void trhowWhenGeneroExists() {
//        given
        Genero genero = new Genero(
                "http//dirimagen.com", "terror");

//        when(generoRepository.existsByNombre(genero.getNombre())).thenReturn(true);
//        given toma el valor del repository y lo retorna en true para poder recrear la excepcion
        given(generoRepository.existsByNombre(genero.getNombre()))
                .willReturn(true);

//        when
//        then
//        lanza la excepción, el tipo de excepcion (instanciaOf) y el tipo de mensaje (hasMessagecontaing) el cual tiene que ser exacto al que se muestra en capa Service
        assertThatThrownBy(()-> underTest.guardarGenero(genero))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Genero "+ genero.getNombre() + " ya existe en la base de datos");

//        aca estamos pidiendo que se verifique en el repositorio que nunca (never) se guarde nada (save(any()))
        verify(generoRepository, never()).save(any());
    }

    //    anotación Disable: para que no corran.
    @Test
    void borrarGenero() {
//        given
        Genero genero = new Genero(
                "http//dirimagen.com", "terror");
//        when
        underTest.borrarGenero(genero.getId());
        generoRepository.delete(genero);
//        then
        ArgumentCaptor<Genero> generoArgumentCaptor =
                ArgumentCaptor.forClass(Genero.class);
        verify(generoRepository)
                .delete(generoArgumentCaptor.capture());

        Genero capturaGenero = generoArgumentCaptor.getValue();

        assertThat(capturaGenero).isEqualTo(genero);
    }

    @Test
    void buscarGeneroPorId() {
//        give
        Long id = 1L;
//        when
        underTest.buscarGeneroPorId(id);
//        then
        verify(generoRepository).findById(id);
    }
}