package com.Alkemy.Challenge.Java.repository;

import com.Alkemy.Challenge.Java.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
