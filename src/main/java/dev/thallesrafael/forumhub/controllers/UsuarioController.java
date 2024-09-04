package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.DTO.UsuarioAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioCadastroDTO;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioDTO;
import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioCadastroDTO dados, UriComponentsBuilder builerUri){
        var usuario = usuarioService.cadastrar(dados);
        var uri = builerUri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }


    @PutMapping
    public ResponseEntity<UsuarioDTO> alterarUsuario(@RequestBody @Valid UsuarioAttDTO dados){
        var usuario = usuarioService.atualizar(dados);

       return ResponseEntity.ok(new UsuarioDTO(usuario));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> usuarioPorId(@PathVariable Long id){
        var usuario = usuarioService.usuarioPorId(id);
        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<Usuario>> listar(){
        var list = usuarioService.listar();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable Long id){
        usuarioService.deletaPorID(id);
        return ResponseEntity.noContent().build();
    }
}
