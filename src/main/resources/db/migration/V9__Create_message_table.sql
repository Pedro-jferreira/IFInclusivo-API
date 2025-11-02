-- Migration para criar a tabela de mensagens
CREATE TABLE message (
    id BIGSERIAL PRIMARY KEY,
    text TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    visualizado BOOLEAN,
    user_envia_id BIGINT,
    user_recebe_id BIGINT,
    FOREIGN KEY (user_envia_id) REFERENCES usuario_entity(id),
    FOREIGN KEY (user_recebe_id) REFERENCES usuario_entity(id)
);