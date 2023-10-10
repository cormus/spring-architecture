package com.cormus.architecture.app.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UsuarioAtualizacaoDto {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

}
