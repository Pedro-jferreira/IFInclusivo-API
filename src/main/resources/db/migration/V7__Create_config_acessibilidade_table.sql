-- Migration para criar a tabela de configurações de acessibilidade
CREATE TABLE config_acessibilidade_entity (
    id BIGSERIAL PRIMARY KEY,
    audicao VARCHAR(100),
    tema VARCHAR(50),
    zoom VARCHAR(50)
);

-- Adicionar coluna de referência na tabela usuario_entity
ALTER TABLE usuario_entity ADD COLUMN config_acessibilidade_entity_id BIGINT;
ALTER TABLE usuario_entity ADD CONSTRAINT fk_usuario_config_acessibilidade 
    FOREIGN KEY (config_acessibilidade_entity_id) REFERENCES config_acessibilidade_entity(id);