package com.Alkemy.Challenge.Java.dtos;
import com.Alkemy.Challenge.Java.entity.Usuario;
import lombok.*;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
public class UsuarioDto implements Serializable {
    @Getter @Setter     
    private String email;
    @Getter @Setter     
    private String username;
    @Getter @Setter     
    private String roles;    
    
    public static UsuarioDto UsuarioDto(Usuario usuario){
        UsuarioDto dto = new UsuarioDto();
        dto.setEmail(usuario.getEmail());
        dto.setUsername(usuario.getUsername());
        return dto;
    }
}