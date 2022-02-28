package com.Alkemy.Challenge.Java.service;

import com.Alkemy.Challenge.Java.entity.Genero;
import com.Alkemy.Challenge.Java.repository.GeneroRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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

//    anotación Disable: para que no corran.
    @Test
    @Disabled
    void borrarGenero() {

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

}