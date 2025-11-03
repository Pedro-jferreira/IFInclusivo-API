-- Migration para criar a tabela de intérpretes
CREATE TABLE interprete (
    id BIGINT PRIMARY KEY,
    salary DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id) REFERENCES tutor(id)
);