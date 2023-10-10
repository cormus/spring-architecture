package com.cormus.architecture.app.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDto(
    @NotBlank
    String login,

    @NotBlank
    String senha
) {

}
