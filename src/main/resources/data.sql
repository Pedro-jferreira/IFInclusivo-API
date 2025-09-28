-- Script para inserir dados iniciais de intérpretes e libras

-- Inserir 3 intérpretes (verificar se já existem)
INSERT INTO usuario_entity (nome, login, senha, matricula, biografia, user_type, data_criacao, is_active)
SELECT 'Maria Silva Santos', 'maria.interprete', '$2a$10$N.zmdr9k7uOgdlLxbYqrh.93UahQJSjpOWlReabWong4lPG2aIwSq', 20231001, 'Intérprete de Libras especializada em tecnologia', 'INTERPRETE', NOW(), true
    WHERE NOT EXISTS (SELECT 1 FROM usuario_entity WHERE login = 'maria.interprete');

INSERT INTO usuario_entity (nome, login, senha, matricula, biografia, user_type, data_criacao, is_active)
SELECT 'João Pedro Costa', 'joao.interprete', '$2a$10$N.zmdr9k7uOgdlLxbYqrh.93UahQJSjpOWlReabWong4lPG2aIwSq', 20231002, 'Intérprete com foco em programação e desenvolvimento', 'INTERPRETE', NOW(), true
    WHERE NOT EXISTS (SELECT 1 FROM usuario_entity WHERE login = 'joao.interprete');

INSERT INTO usuario_entity (nome, login, senha, matricula, biografia, user_type, data_criacao, is_active)
SELECT 'Ana Carolina Lima', 'ana.interprete', '$2a$10$N.zmdr9k7uOgdlLxbYqrh.93UahQJSjpOWlReabWong4lPG2aIwSq', 20231003, 'Especialista em sinais técnicos de informática', 'INTERPRETE', NOW(), true
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
SELECT u.id, 'INTERPRETE' FROM usuario_entity u
WHERE u.login = 'maria.interprete' AND NOT EXISTS (SELECT 1 FROM usuario_entity_roles r WHERE r.usuario_entity_id = u.id AND r.roles = 'INTERPRETE');

INSERT INTO usuario_entity_roles (usuario_entity_id, roles)
SELECT u.id, 'INTERPRETE' FROM usuario_entity u
WHERE u.login = 'joao.interprete' AND NOT EXISTS (SELECT 1 FROM usuario_entity_roles r WHERE r.usuario_entity_id = u.id AND r.roles = 'INTERPRETE');

INSERT INTO usuario_entity_roles (usuario_entity_id, roles)
SELECT u.id, 'INTERPRETE' FROM usuario_entity u
WHERE u.login = 'ana.interprete' AND NOT EXISTS (SELECT 1 FROM usuario_entity_roles r WHERE r.usuario_entity_id = u.id AND r.roles = 'INTERPRETE');

-- Inserir 20 registros de libras com diferentes categorias
-- Status: APROVADO=1, REPROVADO=2, EMANALISE=3
-- Categorias: REDES=1, BANCO_DE_DADOS=2, PROGRAMACAO=3, WEB=4, ESTRUTURA_DE_DADOS=5, ARQUITETURA_DE_COMPUTADORES=6
INSERT INTO libras (palavra, descricao, url, justificativa, status, categorias) VALUES
-- PROGRAMACAO (3)
('JAVA', 'Linguagem de programação orientada a objetos', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Termo fundamental em programação', 1, 3),
('PYTHON', 'Linguagem de programação de alto nível', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Muito utilizada em desenvolvimento', 1, 3),
('VARIAVEL', 'Espaço na memória para armazenar dados', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Conceito básico de programação', 1, 3),
('FUNCAO', 'Bloco de código reutilizável', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Fundamental para organização do código', 1, 3),
('LOOP', 'Estrutura de repetição', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Conceito essencial em algoritmos', 3, 3),

-- BANCO_DE_DADOS (2)
('SQL', 'Linguagem de consulta estruturada', 'https://www.youtube.com/shorts/zWT6hEAk3h8?feature=share', 'Padrão para bancos relacionais', 1, 2),
('TABELA', 'Estrutura para organizar dados', 'https://youtu.be/ApuB8dT8tk8', 'Elemento básico de banco de dados', 1, 2),
('CHAVE_PRIMARIA', 'Identificador único de registro', 'https://youtu.be/ApuB8dT8tk8', 'Conceito fundamental em BD', 1, 2),
('SELECT', 'Comando para consultar dados', 'https://youtu.be/ApuB8dT8tk8', 'Comando mais usado em SQL', 3, 2),

-- REDES (1)
('PROTOCOLO', 'Regras de comunicação em rede', 'https://youtu.be/ApuB8dT8tk8', 'Base da comunicação de dados', 1, 1),
('TCP_IP', 'Protocolo de comunicação da internet', 'https://youtu.be/ApuB8dT8tk8', 'Protocolo fundamental da web', 1, 1),
('ROTEADOR', 'Equipamento que direciona dados', 'https://youtu.be/ApuB8dT8tk8', 'Dispositivo essencial em redes', 1, 1),
('FIREWALL', 'Sistema de segurança de rede', 'https://youtu.be/ApuB8dT8tk8', 'Proteção contra ameaças', 3, 1),

-- WEB (4)
('HTML', 'Linguagem de marcação para web', 'https://youtu.be/ApuB8dT8tk8', 'Base do desenvolvimento web', 1, 4),
('CSS', 'Linguagem para estilização web', 'https://youtu.be/ApuB8dT8tk8', 'Essencial para design web', 1, 4),
('JAVASCRIPT', 'Linguagem de programação web', 'https://youtu.be/ApuB8dT8tk8', 'Interatividade em páginas web', 1, 4),
('NAVEGADOR', 'Software para acessar a web', 'https://youtu.be/ApuB8dT8tk8', 'Interface para internet', 3, 4),

-- ESTRUTURA_DE_DADOS (5)
('ARRAY', 'Estrutura de dados indexada', 'https://youtu.be/ApuB8dT8tk8', 'Estrutura básica de programação', 1, 5),
('LISTA', 'Coleção ordenada de elementos', 'https://youtu.be/ApuB8dT8tk8', 'Estrutura dinâmica importante', 1, 5),

-- ARQUITETURA_DE_COMPUTADORES (6)
('CPU', 'Unidade central de processamento', 'https://youtu.be/ApuB8dT8tk8', 'Componente principal do computador', 1, 6),
('MEMORIA_RAM', 'Memória de acesso aleatório', 'https://youtu.be/ApuB8dT8tk8', 'Armazenamento temporário de dados', 1, 6);

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