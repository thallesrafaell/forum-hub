package dev.thallesrafael.forumhub.validations.topicos;

import dev.thallesrafael.forumhub.domain.DTO.TopicoAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.TopicoCadastroDTO;
import dev.thallesrafael.forumhub.domain.Topico;
import dev.thallesrafael.forumhub.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class ValidaTopicos {

    @Autowired
    private TopicoRepository repository;


    public void validarDuplicados(TopicoCadastroDTO dados) {
        List<Topico> list = repository.findAll().stream().toList();

        list.forEach(
                t -> {
                    var titulo = t.getTitulo().equals(dados.titulo());
                    var msg = t.getMensagem().equals(dados.mensagem());
                    if(titulo || msg){
                        throw new RuntimeException("O tópico é duplicado!");
                    }
                }
        );
    }

    public void validarAtualizacaoDuplicado(TopicoAttDTO dados){
        List<Topico> list = repository.findAll().stream().filter(
                t-> !t.getId().equals(dados.id())
        ).toList();

        list.forEach(
                t -> {
                    var titulo = t.getTitulo().equals(dados.titulo());
                    var msg = t.getMensagem().equals(dados.mensagem());
                    if(titulo || msg){
                        throw new RuntimeException("O tópico é duplicado!");
                    }
                }
        );
    }

    public void validarUsuarioTopico(JwtAuthenticationToken token,TopicoAttDTO dados) {
       var topico = repository.findById(dados.id());
       if(topico.get().getAutor().getId() != Long.parseLong(token.getName())){
           throw new RuntimeException("O usário não é o autor deste tópico");
       }
    }
}

