-- Migration para criar a tabela principal de usuários
CREATE TABLE IF NOT EXISTS usuario_entity (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    matricula BIGINT NOT NULL,
    biografia TEXT,
    img_perfil VARCHAR(500),
    user_type VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT FALSE
);

-- Tabela para roles dos usuários
CREATE TABLE IF NOT EXISTS usuario_entity_roles (
    usuario_entity_id BIGINT NOT NULL,
    roles VARCHAR(50) NOT NULL,
    FOREIGN KEY (usuario_entity_id) REFERENCES usuario_entity(id)
);