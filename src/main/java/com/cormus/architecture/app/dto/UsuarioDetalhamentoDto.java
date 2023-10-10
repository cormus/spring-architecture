package com.cormus.architecture.app.dto;

import com.cormus.architecture.app.entity.Usuario;

public record UsuarioDetalhamentoDto(Long id, String nome) {

    public UsuarioDetalhamentoDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }

}
