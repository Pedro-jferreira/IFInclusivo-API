-- Migration para criar a tabela de publicações
CREATE TABLE IF NOT EXISTS publicacoes (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255),
    texto TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    status VARCHAR(50) DEFAULT 'PENDENTE',
    resposta_escolhida_id BIGINT,
    usuario_id BIGINT NOT NULL,
    parent_id BIGINT,
    FOREIGN KEY (resposta_escolhida_id) REFERENCES publicacoes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario_entity(id),
    FOREIGN KEY (parent_id) REFERENCES publicacoes(id)
);

-- Tabela para categorias das publicações
CREATE TABLE IF NOT EXISTS publicacao_categorias (
    publicacao_id BIGINT NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    FOREIGN KEY (publicacao_id) REFERENCES publicacoes(id)
);

-- Tabela para likes das publicações
CREATE TABLE IF NOT EXISTS usuario_likes_publicacao (
    usuario_id BIGINT NOT NULL,
    publicacao_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, publicacao_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario_entity(id),
    FOREIGN KEY (publicacao_id) REFERENCES publicacoes(id)
);