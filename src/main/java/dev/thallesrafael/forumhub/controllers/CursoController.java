package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.Curso;
import dev.thallesrafael.forumhub.domain.DTO.CusoCadastroDto;
import dev.thallesrafael.forumhub.services.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<Curso> cadastrar(@RequestBody @Valid CusoCadastroDto dados, UriComponentsBuilder uriBuilder, JwtAuthenticationToken token){
        var curso = service.cadastrar(dados, token);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(curso);
    }

    @PutMapping
    public ResponseEntity<Curso> atualizar(@RequestBody Curso dados, JwtAuthenticationToken token){
        var curso = service.atualizar(dados, token);
        return ResponseEntity.ok(curso);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> cursoPorId(@PathVariable Long id, JwtAuthenticationToken token){
        var curso = service.cursoPorId(id, token);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id, JwtAuthenticationToken token){
        service.deletarPorId(id, token);
        return ResponseEntity.noContent().build();
    }
}
