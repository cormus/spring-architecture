package com.cormus.architecture.app.repository;

import com.cormus.architecture.app.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAllByDeletedAtNull(Pageable paginacao);

}
