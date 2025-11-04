-- Migration para criar a tabela de professores
CREATE TABLE IF NOT EXISTS professor (
    id BIGINT PRIMARY KEY,
    formacao VARCHAR(255),
    FOREIGN KEY (id) REFERENCES usuario_entity(id)
);