CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topico_id BIGINT NOT NULL,
    data_criacao DATETIME NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);