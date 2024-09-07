package dev.thallesrafael.forumhub.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioAttDTO(
                 String nome,
                 String senha) {
}
