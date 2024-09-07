package dev.thallesrafael.forumhub.domain.DTO;

import dev.thallesrafael.forumhub.domain.Topico;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDetalhamentoDto(Long id, String titulo, String mensagem, String curso, String autor, LocalDateTime dataCriacao, List<RespostaResponseDto> repostas) {
    public TopicoDetalhamentoDto(Topico topico, List<RespostaResponseDto> listaRespostas){
        this (topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso().getNome(),topico.getAutor().getNome(),topico.getDataCriacao(), listaRespostas);
    }
}
