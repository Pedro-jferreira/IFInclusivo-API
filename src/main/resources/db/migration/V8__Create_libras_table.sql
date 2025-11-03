-- Migration para criar a tabela de Libras
CREATE TABLE libras (
    id BIGSERIAL PRIMARY KEY,
    palavra VARCHAR(255),
    descricao VARCHAR(800),
    url VARCHAR(500),
    justificativa TEXT,
    fileUrl VARCHAR(100),
    status VARCHAR(50),
    categorias VARCHAR(50)
);

-- Tabela de relacionamento entre usuários e sugestões de Libras
CREATE TABLE sugeriu_libras (
    libras_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (libras_id, usuario_id),
    FOREIGN KEY (libras_id) REFERENCES libras(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario_entity(id)
);

-- Tabela de relacionamento entre intérpretes e Libras
CREATE TABLE interprete_libras (
    interprete_id BIGINT NOT NULL,
    libras_id BIGINT NOT NULL,
    PRIMARY KEY (interprete_id, libras_id),
    FOREIGN KEY (interprete_id) REFERENCES interprete(id),
    FOREIGN KEY (libras_id) REFERENCES libras(id)
);