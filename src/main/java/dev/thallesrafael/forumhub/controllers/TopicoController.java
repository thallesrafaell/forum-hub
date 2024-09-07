package dev.thallesrafael.forumhub.controllers;


import dev.thallesrafael.forumhub.domain.DTO.*;
import dev.thallesrafael.forumhub.services.CursoService;
import dev.thallesrafael.forumhub.services.TopicoService;
import dev.thallesrafael.forumhub.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
    public ResponseEntity<TopicoResponseDto> cadastrar(@RequestBody @Valid TopicoCadastroDTO dados, UriComponentsBuilder uriBuilder, JwtAuthenticationToken token){
       var topico =  service.cadastrar(dados,token);
       var resposta = new TopicoResponseDto(topico);
       var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<TopicoResponseDto>> listar(JwtAuthenticationToken token){
        List<TopicoResponseDto> list = service.listarTodos(token)
                .stream()
                .map(topico -> new TopicoResponseDto(topico))
                .toList();
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhamentoDto> listarPorId(@PathVariable Long id, JwtAuthenticationToken token){
       var topico = service.listarPorId(id,token);
       return ResponseEntity.ok(topico);
    }

    @PutMapping
    public ResponseEntity<TopicoResponseDto> atualizar(@RequestBody TopicoAttDTO dados, JwtAuthenticationToken token){
        var topico = service.atualizarTopico(dados, token);
        return ResponseEntity.ok(new TopicoResponseDto(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id, JwtAuthenticationToken token){
        service.deletar(id, token);
        return ResponseEntity.noContent().build();
    }
}
