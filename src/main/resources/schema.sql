-- Schema para criar as tabelas necessárias antes do data.sql

-- Tabela principal de usuários
CREATE TABLE IF NOT EXISTS usuario_entity (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    matricula BIGINT NOT NULL,
    biografia TEXT,
    user_type VARCHAR(50),
    data_criacao TIMESTAMP,
    is_active BOOLEAN DEFAULT false
);

-- Tabela de roles dos usuários
CREATE TABLE IF NOT EXISTS usuario_entity_roles (
    usuario_entity_id BIGINT NOT NULL,
    roles VARCHAR(50) NOT NULL,
    FOREIGN KEY (usuario_entity_id) REFERENCES usuario_entity(id)
);

-- Tabela de tutores (herança)
CREATE TABLE IF NOT EXISTS tutor (
    id BIGINT PRIMARY KEY,
    especialidade VARCHAR(255),
    FOREIGN KEY (id) REFERENCES usuario_entity(id)
);

-- Tabela de intérpretes (herança)
CREATE TABLE IF NOT EXISTS interprete (
    id BIGINT PRIMARY KEY,
    salary DECIMAL(10,2),
    FOREIGN KEY (id) REFERENCES usuario_entity(id)
);

-- Tabela de libras
CREATE TABLE IF NOT EXISTS libras (
    id BIGSERIAL PRIMARY KEY,
    palavra VARCHAR(255) NOT NULL,
    descricao TEXT,
    url VARCHAR(500),
    justificativa TEXT,
    status INTEGER,
    categorias INTEGER
);

-- Tabela de relacionamento intérprete-libras
CREATE TABLE IF NOT EXISTS interprete_libras (
    interprete_id BIGINT NOT NULL,
    libras_id BIGINT NOT NULL,
    PRIMARY KEY (interprete_id, libras_id),
    FOREIGN KEY (interprete_id) REFERENCES interprete(id),
    FOREIGN KEY (libras_id) REFERENCES libras(id)
);