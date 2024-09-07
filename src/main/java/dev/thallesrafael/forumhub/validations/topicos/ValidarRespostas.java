package dev.thallesrafael.forumhub.validations.topicos;

import dev.thallesrafael.forumhub.domain.DTO.RespostaAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.RespostaDTO;
import dev.thallesrafael.forumhub.repositories.RespostaRepository;
import dev.thallesrafael.forumhub.services.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class ValidarRespostas {

    @Autowired
    private RespostaRepository repository;

    public void validarDuplicata(RespostaDTO dados,JwtAuthenticationToken token){
        var list = repository.findAll().stream()
                .filter(resposta -> resposta.getTopico().getId().equals(dados.idTopico()))
                .toList();

        list.forEach(resposta ->  {
            var autor = resposta.getAutor().getId().equals(Long.parseLong(token.getName()));
            var mensagen = resposta.getMensagem().equals(dados.mensagem());
            if(autor && mensagen){
                throw new RuntimeException("Seu comentário está duplicado!");
            }
        });


    }

    public void validarDuplicataAtt(RespostaAttDTO dados, JwtAuthenticationToken token){
        var list = repository.findAll().stream()
                .filter(resposta -> resposta.getTopico().getId().equals(dados.idTopico()))
                .toList();

            list.forEach(resposta ->  {
                var autor = resposta.getAutor().getId().equals(Long.parseLong(token.getName()));
                var mensagen = resposta.getMensagem().equals(dados.mensagem());
                if(autor && mensagen){
                    throw new RuntimeException("Seu comentário está duplicado!");
                }
            });
    }

    public void validarAutorResposta(Long idResposta, JwtAuthenticationToken token) {
        var idAutor = repository.findById(idResposta).get().getAutor().getId();
            if(idAutor != Long.parseLong(token.getName())){
                throw new RuntimeException("O usuário nao corresponde ao autor da reposta!");
            }
    }
}
