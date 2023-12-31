package com.cormus.architecture.app.controller;

import com.cormus.architecture.app.domain.dto.UsuarioAtualizacaoDto;
import com.cormus.architecture.app.domain.dto.UsuarioCadastroDto;
import com.cormus.architecture.app.domain.dto.UsuarioDetalhamentoDto;
import com.cormus.architecture.app.domain.dto.UsuarioListaDto;
import com.cormus.architecture.app.domain.entity.Usuario;
import com.cormus.architecture.app.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "api/v1/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder ;

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

        String password = passwordEncoder.encode(usuario.senha());
        usuarioEnt.setSenha(password);

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


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        Usuario usuarioEnt = repository.getReferenceById(id);
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
