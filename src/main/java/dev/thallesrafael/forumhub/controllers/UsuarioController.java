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
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PutMapping
    public ResponseEntity<UsuarioDTO> alterarUsuario(@RequestBody @Valid UsuarioAttDTO dados, JwtAuthenticationToken token){
        var usuario = usuarioService.atualizar(dados,token);

       return ResponseEntity.ok(new UsuarioDTO(usuario));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> usuarioPorId(@PathVariable Long id, JwtAuthenticationToken token){
        var usuario = usuarioService.usuarioPorId(id,token);
        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listar(JwtAuthenticationToken token){
        var list = usuarioService.listar(token).stream().map(usuario -> new UsuarioDTO(usuario)).toList();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable Long id, JwtAuthenticationToken token){
        usuarioService.deletaPorID(id, token);
        return ResponseEntity.noContent().build();
    }
}
