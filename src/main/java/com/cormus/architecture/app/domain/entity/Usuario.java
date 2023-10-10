package com.cormus.architecture.app.domain.entity;

import com.cormus.architecture.app.domain.dto.UsuarioAtualizacaoDto;
import com.cormus.architecture.app.domain.dto.UsuarioCadastroDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuário")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;
    private String senha;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Embedded
    private Endereco endereco;

    public Usuario(UsuarioCadastroDto usuario) {
        this.nome = usuario.nome();
        this.telefone = usuario.telefone();
        this.email = usuario.email();
        this.senha = usuario.senha();
        this.endereco = new Endereco(usuario.endereco());
    }

    public void atualizar(UsuarioAtualizacaoDto usuario){
        this.nome = usuario.getNome();
    }

    public void excluir(){
        this.deletedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
