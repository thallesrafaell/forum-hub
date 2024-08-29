package dev.thallesrafael.forumhub.domain.DTO;

import dev.thallesrafael.forumhub.domain.Curso;
import dev.thallesrafael.forumhub.domain.Usuario;

import java.time.LocalDateTime;

public record DadosCadastroTopico(String titulo, String mensagem, LocalDateTime data, Usuario autor, Curso curso) {
}
