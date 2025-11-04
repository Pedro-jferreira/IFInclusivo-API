-- Migration para criar a tabela de tutores
CREATE TABLE IF NOT EXISTS tutor (
    id BIGINT PRIMARY KEY,
    especialidade VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES usuario_entity(id)
);