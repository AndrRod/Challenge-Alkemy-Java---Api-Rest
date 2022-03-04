package com.Alkemy.Challenge.Java.repository;

import com.Alkemy.Challenge.Java.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Usuario c WHERE c.email = :email")
    Boolean existByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(c" +
            ") > 0 THEN true ELSE false END FROM Usuario c WHERE c.username = :username")
    Boolean existByUsername(@Param("username") String username);


}
