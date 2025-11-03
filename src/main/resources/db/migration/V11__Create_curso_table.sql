-- Migration para criar a tabela de cursos
CREATE TABLE curso_entity (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255)
);

-- Adicionar foreign key para curso na tabela aluno
ALTER TABLE aluno ADD COLUMN curso_id BIGINT;
ALTER TABLE aluno ADD FOREIGN KEY (curso_id) REFERENCES curso_entity(id);