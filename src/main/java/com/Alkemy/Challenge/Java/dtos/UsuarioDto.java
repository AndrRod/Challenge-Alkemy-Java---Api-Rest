package com.Alkemy.Challenge.Java.dtos;
import com.Alkemy.Challenge.Java.entity.Rol;
import com.Alkemy.Challenge.Java.entity.Usuario;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class UsuarioDto implements Serializable {
    @Getter @Setter     
    private String email;
    @Getter @Setter     
    private String username;
    @Getter @Setter     
    private Collection<Rol> roles = new ArrayList<>();
    
    public static UsuarioDto UsuarioDto(Usuario usuario){
        UsuarioDto dto = new UsuarioDto();
        dto.setEmail(usuario.getEmail());
        dto.setUsername(usuario.getUsername());
        dto.setRoles(usuario.getRoles());
        return dto;
    }
    public static List<UsuarioDto> ListaUsuarioDto(List<Usuario> usuarios){
        List<UsuarioDto> usuarioDtoList = new ArrayList<>();
        usuarios.forEach(usuario -> usuarioDtoList.add(UsuarioDto.UsuarioDto(usuario)));
        return usuarioDtoList;
    }
}