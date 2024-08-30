package dev.thallesrafael.forumhub.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaAttDTO(
        @NotNull Long id,
        @NotBlank String mensagem,
        @NotNull Long idAutor) {
}
