package dev.thallesrafael.forumhub.domain.DTO;

import dev.thallesrafael.forumhub.domain.Curso;
import dev.thallesrafael.forumhub.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoCadastroDTO(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull Long idCurso) {
}
