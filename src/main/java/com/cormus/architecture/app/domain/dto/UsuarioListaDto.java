package com.cormus.architecture.app.domain.dto;

import com.cormus.architecture.app.domain.entity.Usuario;
import lombok.Getter;

@Getter
public class UsuarioListaDto {

    private Long id;
    private String nome;

    public UsuarioListaDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }
}
