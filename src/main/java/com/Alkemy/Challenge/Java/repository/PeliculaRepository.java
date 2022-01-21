package com.Alkemy.Challenge.Java.repository;
import com.Alkemy.Challenge.Java.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
}
