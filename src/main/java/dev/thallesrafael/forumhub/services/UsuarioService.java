package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.UsuarioAttDTO;
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

    public Usuario atualizar(UsuarioAttDTO dados){
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        return usuario;
    }

    public Usuario usuarioPorId(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));

    }


    public void deletaPorID(Long id) {
        repository.deleteById(id);
    }
}
