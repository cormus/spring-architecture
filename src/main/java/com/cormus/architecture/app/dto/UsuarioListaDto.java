package com.cormus.architecture.app.dto;

import com.cormus.architecture.app.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UsuarioListaDto {

    private Long id;
    private String nome;

    public UsuarioListaDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }
}
