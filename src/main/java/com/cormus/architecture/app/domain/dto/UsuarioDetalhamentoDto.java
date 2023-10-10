package com.cormus.architecture.app.domain.dto;

import com.cormus.architecture.app.domain.entity.Usuario;

public record UsuarioDetalhamentoDto(Long id, String nome) {

    public UsuarioDetalhamentoDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }

}
