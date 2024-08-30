package dev.thallesrafael.forumhub.validations.topicos;

import dev.thallesrafael.forumhub.domain.DTO.RespostaAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.RespostaDTO;
import dev.thallesrafael.forumhub.repositories.RespostaRepository;
import dev.thallesrafael.forumhub.services.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarRespostas {

    @Autowired
    private RespostaRepository repository;

    public void validarDuplicata(RespostaDTO dados){
        var list = repository.findAll().stream().toList();

        list.forEach(
                resposta -> {
                    var msg = dados.mensagem().equals(resposta.getMensagem());
                    var idAutor = dados.idAutor().equals(resposta.getAutor().getId());
                    var idTopico = dados.idTopico().equals(resposta.getTopico().getId());
                    if(msg && idAutor && idTopico) throw new RuntimeException("Seu comentário esta duplicado!");
                }
        );
    }

    public void validarDuplicataAtt(RespostaAttDTO dados){
        var list = repository.findAll().stream().toList();

        list.forEach(
                resposta -> {
                    var msg = dados.mensagem().equals(resposta.getMensagem());
                    var idAutor = dados.idAutor().equals(resposta.getAutor().getId());
                    var idTopico = dados.idTopico().equals(resposta.getTopico().getId());
                    if(msg && idAutor && idTopico) throw new RuntimeException("Seu comentário esta duplicado!");
                }
        );
    }
}
