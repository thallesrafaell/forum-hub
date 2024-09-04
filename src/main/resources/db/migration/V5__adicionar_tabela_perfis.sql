-- Criação da tabela 'perfis'
CREATE TABLE perfis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Criação da tabela de associação 'perfis_usuarios' para mapear o relacionamento Many-to-Many entre 'usuarios' e 'perfis'
CREATE TABLE perfis_usuarios (
    usuario_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, role_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (role_id) REFERENCES perfis(id)
);

-- Inserção de valores iniciais na tabela 'perfis'
INSERT INTO perfis (id, nome) VALUES
(1, 'ADMIN'),
(2, 'MODERADOR'),
(3, 'PADRAO');
