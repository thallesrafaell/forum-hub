package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.DTO.UsuarioCadastroDTO;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioDTO;
import dev.thallesrafael.forumhub.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioCadastroDTO dados, UriComponentsBuilder builerUri){
        var usuario = usuarioService.cadastrar(dados);
        var uri = builerUri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
}
