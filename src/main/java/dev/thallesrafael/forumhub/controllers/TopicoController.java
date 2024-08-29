package dev.thallesrafael.forumhub.controllers;


import dev.thallesrafael.forumhub.domain.DTO.TopicoCadastroDTO;
import dev.thallesrafael.forumhub.domain.Topico;
import dev.thallesrafael.forumhub.services.CursoService;
import dev.thallesrafael.forumhub.services.TopicoService;
import dev.thallesrafael.forumhub.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @Autowired
    private UsuarioService serviceUsuario;

    @Autowired
    private CursoService serviceCurso;

    @PostMapping
    public ResponseEntity<Topico> cadastrar(@RequestBody @Valid TopicoCadastroDTO dados, UriComponentsBuilder uriBuilder){
       var topico =  service.cadastrar(dados);
       var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listar(){
        List<Topico> list = service.listarTodos();
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> listarPorId(@PathVariable Long id){
       var topico = service.listarPorId(id);
       return ResponseEntity.ok(topico);
    }
}
