package com.cormus.architecture.app.controller;

import com.cormus.architecture.app.dto.UsuarioCadastroDto;
import com.cormus.architecture.app.entity.Usuario;
import com.cormus.architecture.app.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(value = "api/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public String teste(){
        return "testes";
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid UsuarioCadastroDto usuario){
        Usuario usuarioEnt = new Usuario(usuario);
        repository.save(usuarioEnt);
    }

}
