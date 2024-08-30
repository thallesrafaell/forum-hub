package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.DTO.RespostaDTO;
import dev.thallesrafael.forumhub.domain.Resposta;
import dev.thallesrafael.forumhub.services.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaService service;

    @PostMapping
    public ResponseEntity<Resposta> cadastrar(@RequestBody @Valid RespostaDTO dados, UriComponentsBuilder uriBuilder){
       var resposta =  service.cadastrar(dados);
       var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(resposta.getId()).toUri();
       return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<Resposta>> listar(){
        var list = service.listar();
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resposta> listarPorId(@PathVariable Long id){
        var resposta = service.listarPorId(id);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping
    public ResponseEntity<Resposta> atualizar(@RequestBody RespostaAttDTO dados){
        var resposta = service.atualizar(dados);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
