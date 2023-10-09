package com.cormus.architecture.app.controller;

import com.cormus.architecture.app.dto.UsuarioAtualizacaoDto;
import com.cormus.architecture.app.dto.UsuarioCadastroDto;
import com.cormus.architecture.app.dto.UsuarioListaDto;
import com.cormus.architecture.app.entity.Usuario;
import com.cormus.architecture.app.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(value = "api/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public List<UsuarioListaDto> listar(){
        return repository.findAll().stream().map(UsuarioListaDto::new).toList();
    }

    @GetMapping("/paginacao")
    public Page<UsuarioListaDto> paginacao(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByDeletedAtNull(paginacao).map(UsuarioListaDto::new);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioCadastroDto usuario){
        Usuario usuarioEnt = new Usuario(usuario);
        repository.save(usuarioEnt);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid UsuarioAtualizacaoDto usuario){
        Usuario usuarioEnt = repository.getReferenceById(usuario.getId());
        usuarioEnt.atualizar(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        Usuario usuarioEnt = repository.getReferenceById(id);
        usuarioEnt.excluir();
    }
}
