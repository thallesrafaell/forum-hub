package dev.thallesrafael.forumhub.domain.DTO;

import dev.thallesrafael.forumhub.domain.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CusoCadastroDto(
        @NotBlank String nome,
        @NotNull Categoria categoria) {
}
