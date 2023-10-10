package com.cormus.architecture.app.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioCadastroDto(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @Valid
        @NotNull
        EnderecoDto endereco
) {



}
