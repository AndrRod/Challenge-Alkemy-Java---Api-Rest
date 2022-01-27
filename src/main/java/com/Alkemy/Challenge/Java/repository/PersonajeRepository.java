package com.Alkemy.Challenge.Java.repository;

import com.Alkemy.Challenge.Java.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    Optional<Personaje> findAllById(Long id);

    Personaje findByNombre(String nombre);

}
