package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.DTO.RespostaAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.RespostaDTO;
import dev.thallesrafael.forumhub.domain.DTO.RespostaResponseDto;
import dev.thallesrafael.forumhub.domain.Resposta;
import dev.thallesrafael.forumhub.services.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaService service;

    @PostMapping
    public ResponseEntity<RespostaResponseDto> cadastrar(@RequestBody @Valid RespostaDTO dados, UriComponentsBuilder uriBuilder, JwtAuthenticationToken token){
       var resposta =  service.cadastrar(dados,token);
       var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(resposta.getId()).toUri();
       return ResponseEntity.created(uri).body(new RespostaResponseDto(resposta));
    }

    @GetMapping
    public ResponseEntity<List<RespostaResponseDto>> listar(JwtAuthenticationToken token){
        var list = service.listar(token);
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaResponseDto> listarPorId(@PathVariable Long id, JwtAuthenticationToken token){
        var resposta = service.listarPorId(id,token);
        return ResponseEntity.ok(new RespostaResponseDto(resposta));
    }

    @PutMapping
    public ResponseEntity<RespostaResponseDto> atualizar(@RequestBody RespostaAttDTO dados, JwtAuthenticationToken token){
        var resposta = service.atualizar(dados, token);
        return ResponseEntity.ok(new RespostaResponseDto(resposta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, JwtAuthenticationToken token){
        service.deletar(id, token);
        return ResponseEntity.noContent().build();
    }
}
