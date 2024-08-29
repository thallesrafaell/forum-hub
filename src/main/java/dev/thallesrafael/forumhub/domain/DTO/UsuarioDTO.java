package dev.thallesrafael.forumhub.domain.DTO;

import dev.thallesrafael.forumhub.domain.Usuario;

public record UsuarioDTO(Long id, String nome, String email) {
    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());

    }
}
