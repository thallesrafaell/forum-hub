package dev.thallesrafael.forumhub.domain.DTO;

public record LoginResponse(String token, Long expiresIn) {
}
