package dev.thallesrafael.forumhub.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("aberto")
    ABERTO,
    @JsonProperty("fechado")
    FECHADO
}
