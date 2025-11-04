-- Migration para criar a tabela de configurações de acessibilidade
CREATE TABLE IF NOT EXISTS config_acessibilidade_entity (
    id BIGSERIAL PRIMARY KEY,
    audicao VARCHAR(100),
    tema VARCHAR(50),
    zoom VARCHAR(50)
);

-- Adicionar coluna de referência na tabela usuario_entity se não existir
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='usuario_entity' AND column_name='config_acessibilidade_entity_id') THEN
        ALTER TABLE usuario_entity ADD COLUMN config_acessibilidade_entity_id BIGINT;
        ALTER TABLE usuario_entity ADD CONSTRAINT fk_usuario_config_acessibilidade 
            FOREIGN KEY (config_acessibilidade_entity_id) REFERENCES config_acessibilidade_entity(id);
    END IF;
END $$;