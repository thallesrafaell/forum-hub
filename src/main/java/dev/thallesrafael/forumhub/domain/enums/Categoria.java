package dev.thallesrafael.forumhub.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Categoria {
    @JsonProperty("tecnologia")
    TECNOLOGIA,
    @JsonProperty("UIUX")
    UIUX,
    @JsonProperty("java")
    JAVA,
    @JsonProperty("C#")
    CSHARP


}
