-- Script para inserir dados iniciais de intérpretes e libras

-- Inserir 3 intérpretes (verificar se já existem)

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


INSERT INTO usuario_entity (nome, login, senha, matricula, biografia, user_type, data_criacao, is_active)
SELECT 'Maria Silva Santos', 'maria.interprete', '$2a$10$N.zmdr9k7uOgdlLxbYqrh.93UahQJSjpOWlReabWong4lPG2aIwSq', 20231001, 'Intérprete de Libras especializada em tecnologia', 'interprete', NOW(), true
    WHERE NOT EXISTS (SELECT 1 FROM usuario_entity WHERE login = 'maria.interprete');

INSERT INTO usuario_entity (nome, login, senha, matricula, biografia, user_type, data_criacao, is_active)
SELECT 'João Pedro Costa', 'joao.interprete', '$2a$10$N.zmdr9k7uOgdlLxbYqrh.93UahQJSjpOWlReabWong4lPG2aIwSq', 20231002, 'Intérprete com foco em programação e desenvolvimento', 'interprete', NOW(), true
    WHERE NOT EXISTS (SELECT 1 FROM usuario_entity WHERE login = 'joao.interprete');

INSERT INTO usuario_entity (nome, login, senha, matricula, biografia, user_type, data_criacao, is_active)
SELECT 'Ana Carolina Lima', 'ana.interprete', '$2a$10$N.zmdr9k7uOgdlLxbYqrh.93UahQJSjpOWlReabWong4lPG2aIwSq', 20231003, 'Especialista em sinais técnicos de informática', 'interprete', NOW(), true
    WHERE NOT EXISTS (SELECT 1 FROM usuario_entity WHERE login = 'ana.interprete');

-- Inserir dados específicos do tutor (herança)
INSERT INTO tutor (id, especialidade)
SELECT u.id, 'Tecnologia da Informação' FROM usuario_entity u
WHERE u.login = 'maria.interprete' AND NOT EXISTS (SELECT 1 FROM tutor t WHERE t.id = u.id);

INSERT INTO tutor (id, especialidade)
SELECT u.id, 'Desenvolvimento de Software' FROM usuario_entity u
WHERE u.login = 'joao.interprete' AND NOT EXISTS (SELECT 1 FROM tutor t WHERE t.id = u.id);

INSERT INTO tutor (id, especialidade)
SELECT u.id, 'Redes e Sistemas' FROM usuario_entity u
WHERE u.login = 'ana.interprete' AND NOT EXISTS (SELECT 1 FROM tutor t WHERE t.id = u.id);

-- Inserir dados específicos do intérprete (herança)
INSERT INTO interprete (id, salary)
SELECT u.id, 3500.00 FROM usuario_entity u
WHERE u.login = 'maria.interprete' AND NOT EXISTS (SELECT 1 FROM interprete i WHERE i.id = u.id);

INSERT INTO interprete (id, salary)
SELECT u.id, 3800.00 FROM usuario_entity u
WHERE u.login = 'joao.interprete' AND NOT EXISTS (SELECT 1 FROM interprete i WHERE i.id = u.id);

INSERT INTO interprete (id, salary)
SELECT u.id, 3600.00 FROM usuario_entity u
WHERE u.login = 'ana.interprete' AND NOT EXISTS (SELECT 1 FROM interprete i WHERE i.id = u.id);

-- Inserir roles para os intérpretes
INSERT INTO usuario_entity_roles (usuario_entity_id, roles)
SELECT u.id, 'ROLE_INTERPRETE' FROM usuario_entity u
WHERE u.login = 'maria.interprete' AND NOT EXISTS (SELECT 1 FROM usuario_entity_roles r WHERE r.usuario_entity_id = u.id AND r.roles = 'ROLE_INTERPRETE');

INSERT INTO usuario_entity_roles (usuario_entity_id, roles)
SELECT u.id, 'ROLE_INTERPRETE' FROM usuario_entity u
WHERE u.login = 'joao.interprete' AND NOT EXISTS (SELECT 1 FROM usuario_entity_roles r WHERE r.usuario_entity_id = u.id AND r.roles = 'ROLE_INTERPRETE');

INSERT INTO usuario_entity_roles (usuario_entity_id, roles)
SELECT u.id, 'ROLE_INTERPRETE' FROM usuario_entity u
WHERE u.login = 'ana.interprete' AND NOT EXISTS (SELECT 1 FROM usuario_entity_roles r WHERE r.usuario_entity_id = u.id AND r.roles = 'ROLE_INTERPRETE');

-- Inserir 20 registros de libras com diferentes categorias
-- Status: APROVADO=0, REPROVADO=1, EMANALISE=2
-- Categorias: REDES=0, BANCO_DE_DADOS=1, PROGRAMACAO=2, WEB=3, ESTRUTURA_DE_DADOS=4, ARQUITETURA_DE_COMPUTADORES=5
INSERT INTO libras (palavra, descricao, url, justificativa, status, categorias) VALUES
-- PROGRAMACAO (2)
('JAVA', 'Linguagem de programação orientada a objetos', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Termo fundamental em programação', 0, 2),
('PYTHON', 'Linguagem de programação de alto nível', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Muito utilizada em desenvolvimento', 0, 2),
('VARIAVEL', 'Espaço na memória para armazenar dados', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Conceito básico de programação', 0, 2),
('FUNCAO', 'Bloco de código reutilizável', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Fundamental para organização do código', 0, 2),
('LOOP', 'Estrutura de repetição', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Conceito essencial em algoritmos', 2, 2),

-- BANCO_DE_DADOS (1)
('SQL', 'Linguagem de consulta estruturada', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Padrão para bancos relacionais', 0, 1),
('TABELA', 'Estrutura para organizar dados', 'https://youtu.be/ApuB8dT8tk8', 'Elemento básico de banco de dados', 0, 1),
('CHAVE_PRIMARIA', 'Identificador único de registro', 'https://youtu.be/ApuB8dT8tk8', 'Conceito fundamental em BD', 0, 1),
('SELECT', 'Comando para consultar dados', 'https://youtu.be/ApuB8dT8tk8', 'Comando mais usado em SQL', 2, 1),

-- REDES (0)
('PROTOCOLO', 'Regras de comunicação em rede', 'https://youtu.be/ApuB8dT8tk8', 'Base da comunicação de dados', 0, 0),
('TCP_IP', 'Protocolo de comunicação da internet', 'https://youtu.be/ApuB8dT8tk8', 'Protocolo fundamental da web', 0, 0),
('ROTEADOR', 'Equipamento que direciona dados', 'https://youtu.be/ApuB8dT8tk8', 'Dispositivo essencial em redes', 0, 0),
('FIREWALL', 'Sistema de segurança de rede', 'https://youtu.be/ApuB8dT8tk8', 'Proteção contra ameaças', 2, 0),

-- WEB (3)
('HTML', 'Linguagem de marcação para web', 'https://youtu.be/ApuB8dT8tk8', 'Base do desenvolvimento web', 0, 3),
('CSS', 'Linguagem para estilização web', 'https://youtu.be/ApuB8dT8tk8', 'Essencial para design web', 0, 3),
('JAVASCRIPT', 'Linguagem de programação web', 'https://youtu.be/ApuB8dT8tk8', 'Interatividade em páginas web', 0, 3),
('NAVEGADOR', 'Software para acessar a web', 'https://youtu.be/ApuB8dT8tk8', 'Interface para internet', 2, 3),

-- ESTRUTURA_DE_DADOS (4)
('ARRAY', 'Estrutura de dados indexada', 'https://youtu.be/ApuB8dT8tk8', 'Estrutura básica de programação', 0, 4),
('LISTA', 'Coleção ordenada de elementos', 'https://youtu.be/ApuB8dT8tk8', 'Estrutura dinâmica importante', 0, 4),

-- ARQUITETURA_DE_COMPUTADORES (5)
('CPU', 'Unidade central de processamento', 'https://youtu.be/ApuB8dT8tk8', 'Componente principal do computador', 0, 5),
('MEMORIA_RAM', 'Memória de acesso aleatório', 'https://youtu.be/ApuB8dT8tk8', 'Armazenamento temporário de dados', 0, 5);

-- Associar libras aos intérpretes (relacionamento many-to-many)
-- Maria - especialista em tecnologia
INSERT INTO interprete_libras (interprete_id, libras_id)
SELECT u.id, l.id FROM usuario_entity u, libras l
WHERE u.login = 'maria.interprete' AND l.palavra IN ('JAVA', 'PYTHON', 'HTML', 'CSS', 'JAVASCRIPT', 'CPU', 'MEMORIA_RAM')
  AND NOT EXISTS (SELECT 1 FROM interprete_libras il WHERE il.interprete_id = u.id AND il.libras_id = l.id);

-- João - foco em programação
INSERT INTO interprete_libras (interprete_id, libras_id)
SELECT u.id, l.id FROM usuario_entity u, libras l
WHERE u.login = 'joao.interprete' AND l.palavra IN ('VARIAVEL', 'FUNCAO', 'LOOP', 'ARRAY', 'LISTA', 'SQL', 'SELECT')
  AND NOT EXISTS (SELECT 1 FROM interprete_libras il WHERE il.interprete_id = u.id AND il.libras_id = l.id);

-- Ana - especialista em redes
INSERT INTO interprete_libras (interprete_id, libras_id)
SELECT u.id, l.id FROM usuario_entity u, libras l
WHERE u.login = 'ana.interprete' AND l.palavra IN ('PROTOCOLO', 'TCP_IP', 'ROTEADOR', 'FIREWALL', 'TABELA', 'CHAVE_PRIMARIA', 'NAVEGADOR')
  AND NOT EXISTS (SELECT 1 FROM interprete_libras il WHERE il.interprete_id = u.id AND il.libras_id = l.id);