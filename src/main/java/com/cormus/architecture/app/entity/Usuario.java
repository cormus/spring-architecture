package com.cormus.architecture.app.entity;

import com.cormus.architecture.app.dto.UsuarioCadastroDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Entity(name = "Usuário")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;

    @Embedded
    private Endereco endereco;

    public Usuario(UsuarioCadastroDto usuario) {
        this.nome = usuario.nome();
        this.telefone = usuario.telefone();
        this.email = usuario.email();
        this.endereco = new Endereco(usuario.endereco());
    }
}
