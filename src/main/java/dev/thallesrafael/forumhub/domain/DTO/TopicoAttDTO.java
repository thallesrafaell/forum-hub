package dev.thallesrafael.forumhub.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoAttDTO(
                           @NotNull Long id,
                           @NotBlank String titulo,
                           @NotBlank String mensagem
                           )
{
}
