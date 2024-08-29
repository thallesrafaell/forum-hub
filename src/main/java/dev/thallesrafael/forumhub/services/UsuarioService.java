package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.UsuarioCadastroDTO;
import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(UsuarioCadastroDTO dados){
        var usuario = new Usuario(dados);
        repository.save(usuario);
        return usuario;
    }


}
