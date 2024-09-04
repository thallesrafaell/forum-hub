package dev.thallesrafael.forumhub.repositories;

import dev.thallesrafael.forumhub.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNome(String nome);
}
