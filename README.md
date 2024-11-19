# 🎓 **IF Inclusivo** 🌐

## 🛠️  **Ferramentas utilizadas**
<img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Java-Dark.svg" width="50px"> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Spring-Light.svg" width="50px"> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/PostgreSQL-Dark.svg" width="50px">  <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Git.svg" width="50px">  <img src="https://img.icons8.com/?size=100&id=rHpveptSuwDz&format=png&color=000000" width="50px"> <img src="https://github.com/tandpfun/skill-icons/blob/main/icons/Hibernate-Dark.svg" width="50px">
## 🎯 **Objetivo**

O projeto **IF Inclusivo** é uma iniciativa de extensão voltada para solucionar problemas enfrentados por pessoas com necessidades específicas. A aplicação que estamos desenvolvendo contará com as seguintes funcionalidades principais:

- 📚 **Fórum**: Um espaço similar ao Stack Overflow, onde os usuários poderão compartilhar informações e tirar dúvidas de maneira confortável e inclusiva.
- 💬 **Chat**: Professores, tutores e alunos Napne poderão se comunicar diretamente, e os professores terão acesso ao diagnóstico dos alunos Napne para melhor acompanhamento.
- 🤟 **Repositório de Libras**: Um acervo de vídeos em Língua Brasileira de Sinais (Libras), gravados pela tutora do IF Goiano, onde todos os usuários logados poderão sugerir novas palavras para inclusão.

## 👥 **Público-Alvo**

Nosso público-alvo inclui pessoas com:
- 🧠 TDAH (Transtorno de Déficit de Atenção com Hiperatividade)
- 🧑‍🏫 Deficiência intelectual
- 👂 Deficiência Auditiva 
- 👁️ Problemas de visão

A interface do site será especialmente adaptada para atender a essas necessidades, garantindo uma experiência acessível e inclusiva para todos.

## Como executa o projeto
### Pré-requisitos
- Java: 8 ou superior
- Maven
- PostgreSQL
  
### Passo a Passo para Execução
#### 1. Clone o repositorio
```bash
git clone https://github.com/Pedro-jferreira/IFInclusivo-API.git
cd IFInclusivo-API
```
### 2. Configure o banco de dados
Adicione as configurações do seu banco de dados no ```application-test.properties``` mas mantenha a linha ```spring.datasource.driverClassName```  a qual ja seta o banco de dados em memoria H2

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=seu_user
spring.datasource.password=sua_senha
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
```
### 3. Compile e Execute a Aplicação
A IDE fica a seu criterio sobre qual utilizar

### 4 Acesse a API 
A aplicação vai esta rodando na porta ```http://localhost:8080 ```

### 5 Acesse a API no Swagger 
Assim que starta a aplicação, a mesma estara disponivel no endereço
```http://localhost:8080/swagger-ui/index.html ```

## 🧑‍💻 **Equipe**

### Discentes
- Ariana Mesquita Gonçalves
- Ariane de Sá Soares
- Clarice Alves Pinto
- Davi Antônio Ferreira Félix
- Heloísa da Silva Nunes Ribeiro
- Isaías Valdemir Rodrigues Ribeiro de Souza
- Jhennyf Lima Santana
- Pedro Flávio Estrela Gomes
- Pedro Henrique de Jesus Ferreira
- Walmir Cardoso dos Santos Rosa

### Docentes
- **Projeto Integrador 1**: Amaury Walbert de Carvalho, Cristiane de Fátima dos Santos Cardoso, Junio Cesar de Lima
- **Projeto Integrador 2**: Jean Tomaz da Silva, Pedro Artur Ferreira dos Santos Costa, Cristiane de Fátima dos Santos Cardoso
- **Projeto Integrador 3**: Paulo Henrique Garcia Mansur, Junio Cesar de Lima, Gabriel da Silva Vieira

## 🚫 **Contribuição**

No momento, o projeto **não está aberto para contribuições externas**. Acompanhe o repositório para futuras atualizações!
