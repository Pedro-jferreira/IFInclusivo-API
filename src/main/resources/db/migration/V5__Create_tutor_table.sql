-- Migration para criar a tabela de tutores
CREATE TABLE tutor (
    id BIGINT PRIMARY KEY,
    especialidade VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES usuario_entity(id)
);