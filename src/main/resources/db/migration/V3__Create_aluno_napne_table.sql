-- Migration para criar a tabela de alunos NAPNE
CREATE TABLE IF NOT EXISTS aluno_napne (
    id BIGINT PRIMARY KEY,
    condicao VARCHAR(255) NOT NULL,
    laudo VARCHAR(255) NOT NULL,
    necessidade_especial VARCHAR(255) NOT NULL,
    necessidade_escolar VARCHAR(255) NOT NULL,
    acompanhamento VARCHAR(255) NOT NULL,
    situacao VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES aluno(id)
);