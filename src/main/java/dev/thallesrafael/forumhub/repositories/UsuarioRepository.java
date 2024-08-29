package dev.thallesrafael.forumhub.repositories;

import dev.thallesrafael.forumhub.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
