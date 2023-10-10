package com.cormus.architecture.app.domain.entity;

import com.cormus.architecture.app.domain.dto.UsuarioAtualizacaoDto;
import com.cormus.architecture.app.domain.dto.UsuarioCadastroDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Embedded
    private Endereco endereco;

    public Usuario(UsuarioCadastroDto usuario) {
        this.nome = usuario.nome();
        this.telefone = usuario.telefone();
        this.email = usuario.email();
        this.endereco = new Endereco(usuario.endereco());
    }

    public void atualizar(UsuarioAtualizacaoDto usuario){
        this.nome = usuario.getNome();
    }

    public void excluir(){
        this.deletedAt = LocalDateTime.now();
    }
}
