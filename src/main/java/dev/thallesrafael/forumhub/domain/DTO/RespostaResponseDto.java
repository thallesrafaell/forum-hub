package dev.thallesrafael.forumhub.domain.DTO;

import dev.thallesrafael.forumhub.domain.Resposta;

import java.time.LocalDateTime;

public record RespostaResponseDto(Long id, String mensagem, Long topicoId, String autor, LocalDateTime dataCriacao) {

    public RespostaResponseDto(Resposta dados){
        this(dados.getId(), dados.getMensagem(), dados.getTopico().getId(), dados.getAutor().getNome(),dados.getDataCriacao());
    }
}
