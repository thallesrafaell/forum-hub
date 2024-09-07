package dev.thallesrafael.forumhub.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaAttDTO(
        @NotNull Long id,
        @NotBlank String mensagem,
        @NotNull Long idTopico) {
}
