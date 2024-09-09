package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.DTO.LoginRequest;
import dev.thallesrafael.forumhub.domain.DTO.LoginResponse;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioCadastroDTO;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioDTO;
import dev.thallesrafael.forumhub.services.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@SecurityRequirement(name = "bearer-key")
@CrossOrigin("*")
public class TokenController {


    private TokenService service;

    @Autowired
    public TokenController(TokenService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        var response = service.login(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioCadastroDTO dados, UriComponentsBuilder builerUri){
        var usuario = service.cadastrar(dados);
        var uri = builerUri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        // Extrai o token do cabeçalho Authorization
        String token = authorizationHeader.replace("Bearer ", "");

        // Adiciona o token ao Redis
        service.logout(token);

        return ResponseEntity.ok("Logout realizado com sucesso.");
    }
}
