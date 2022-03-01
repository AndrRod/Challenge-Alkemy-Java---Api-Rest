package com.Alkemy.Challenge.Java.repository;

import com.Alkemy.Challenge.Java.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Genero findByNombre(String nombre);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Genero c WHERE c.nombre = :nombre")
    Boolean existsByNombre(@Param("nombre") String nombre);

}
