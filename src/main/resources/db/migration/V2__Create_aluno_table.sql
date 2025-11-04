-- Migration para criar a tabela de alunos
CREATE TABLE IF NOT EXISTS aluno (
    id BIGINT PRIMARY KEY,
    curso VARCHAR(100),
    FOREIGN KEY (id) REFERENCES usuario_entity(id)
);