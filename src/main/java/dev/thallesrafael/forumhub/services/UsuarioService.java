package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.UsuarioAttDTO;
import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.repositories.UsuarioRepository;
import dev.thallesrafael.forumhub.validations.ValidadorJwtInvalidado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ValidadorJwtInvalidado validadorJwtInvalidado;


    public Usuario atualizar(UsuarioAttDTO dados, JwtAuthenticationToken token){
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        var usuario = repository.getReferenceById((Long.parseLong(token.getName())));
        usuario.atualizarInformacoes(dados, passwordEncoder);
        repository.save(usuario);
        return usuario;
    }

    public Usuario usuarioPorId(Long id, JwtAuthenticationToken token){
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));

    }

    public List<Usuario> listar(JwtAuthenticationToken token){
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        return repository.findAll();
    }



    public void deletaPorID(Long id, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        repository.deleteById(id);
    }
}
