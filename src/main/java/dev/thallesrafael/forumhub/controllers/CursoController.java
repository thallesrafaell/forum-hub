package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.Curso;
import dev.thallesrafael.forumhub.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<Curso> cadastrar(@RequestBody @Valid CusoCadastroDto dados, UriComponentsBuilder uriBuilder){
        var curso = service.cadastrar(dados);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(curso);
    }
}
