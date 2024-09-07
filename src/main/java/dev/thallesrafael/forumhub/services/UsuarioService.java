package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.UsuarioAttDTO;
import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.repositories.UsuarioRepository;
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


    public Usuario atualizar(UsuarioAttDTO dados, JwtAuthenticationToken token){
        var usuario = repository.getReferenceById((Long.parseLong(token.getName())));
        usuario.atualizarInformacoes(dados, passwordEncoder);
        repository.save(usuario);
        return usuario;
    }

    public Usuario usuarioPorId(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));

    }

    public List<Usuario> listar(){
        return repository.findAll();
    }



    public void deletaPorID(Long id) {
        repository.deleteById(id);
    }
}
