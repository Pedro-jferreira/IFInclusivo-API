-- Migration para criar a tabela de intérpretes
CREATE TABLE IF NOT EXISTS interprete (
    id BIGINT PRIMARY KEY,
    salary DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id) REFERENCES tutor(id)
);