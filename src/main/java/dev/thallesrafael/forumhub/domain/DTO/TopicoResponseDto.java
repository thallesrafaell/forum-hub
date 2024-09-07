package dev.thallesrafael.forumhub.domain.DTO;

import dev.thallesrafael.forumhub.domain.Topico;

import java.time.LocalDateTime;

public record TopicoResponseDto(Long id,String titulo, String mensagem, String curso, String autor, LocalDateTime dataCriacao) {

    public TopicoResponseDto(Topico topico){
        this (topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso().getNome(),topico.getAutor().getNome(),topico.getDataCriacao());
    }
}
