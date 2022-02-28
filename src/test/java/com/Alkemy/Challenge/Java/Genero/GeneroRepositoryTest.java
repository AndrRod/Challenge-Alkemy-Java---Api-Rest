package com.Alkemy.Challenge.Java.Genero;

import com.Alkemy.Challenge.Java.entity.Genero;
import com.Alkemy.Challenge.Java.repository.GeneroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@DataJpaTest
class GeneroRepositoryTest {

    @Autowired
    private GeneroRepository underTest;

    //    despues de cada test borrar
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void existByNombre() {
//        give
        Genero genero = new Genero(
                "http//dirimagen.com", "terror");
        underTest.save(genero);
        String nombre = "terror";
//        when
         Genero resultados = underTest.findByNombre(nombre);
         System.out.println("RESULTADO: usuario por nombre de GENERO ---" + resultados.getNombre());
         Boolean existe = resultados != null;
//        then
        assertThat(existe).isTrue();
    }
    @Test
    void notExistByNombre() {
//        give
        String nombre = "terror";
//        when
        Genero resultados = underTest.findByNombre(nombre);
        System.out.println("RESULTADO: no se hay usuario por nombre de GENERO y es igual a null (" + resultados + ")");
        Boolean existe = resultados != null;
//        then
        assertThat(existe).isFalse();
    }
}