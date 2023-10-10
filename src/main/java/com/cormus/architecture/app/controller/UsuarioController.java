package com.cormus.architecture.app.controller;

import com.cormus.architecture.app.dto.UsuarioAtualizacaoDto;
import com.cormus.architecture.app.dto.UsuarioCadastroDto;
import com.cormus.architecture.app.dto.UsuarioDetalhamentoDto;
import com.cormus.architecture.app.dto.UsuarioListaDto;
import com.cormus.architecture.app.entity.Usuario;
import com.cormus.architecture.app.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(value = "api/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<UsuarioListaDto>> listar(){
        List<UsuarioListaDto> usuarios = repository.findAll().stream().map(UsuarioListaDto::new).toList();
        //Code return code 200 OK
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<UsuarioListaDto>> paginacao(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        Page<UsuarioListaDto> usuarios = repository.findAllByDeletedAtNull(paginacao).map(UsuarioListaDto::new);
        //Code return code 200 OK
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioCadastroDto usuario, UriComponentsBuilder uriBuilder){
        Usuario usuarioEnt = new Usuario(usuario);
        repository.save(usuarioEnt);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(usuarioEnt.getId()).toUri();

        //Code return code 201 created
        return ResponseEntity.created(uri).body(new UsuarioDetalhamentoDto(usuarioEnt));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UsuarioAtualizacaoDto usuario){
        Usuario usuarioEnt = repository.getReferenceById(usuario.getId());
        usuarioEnt.atualizar(usuario);
        //Code return code 200 OK
        return ResponseEntity.ok(new UsuarioDetalhamentoDto(usuarioEnt));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Usuario usuarioEnt = repository.getReferenceById(id);
        usuarioEnt.excluir();
        //Code return code 204 no content
        return ResponseEntity.noContent().build();
    }
}
