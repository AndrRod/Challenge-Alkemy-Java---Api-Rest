package com.Alkemy.Challenge.Java.repository;

import com.Alkemy.Challenge.Java.entity.Personaje;
import com.Alkemy.Challenge.Java.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    Personaje findByNombre(String nombre);
    Page<Personaje> findAll(Pageable pageable);

}
