# FórumHub

O FórumHub é um projeto de fórum online que permite criar, gerenciar e interagir com tópicos e respostas. A aplicação é construída com Java e utiliza Spring Boot, MySQL e Redis.

## Tecnologias Utilizadas
* Java 17
* Spring Boot 3.3.3
* MySQL
* Redis
* Docker
* Flyway (para migrações de banco de dados)
* Spring Security (com JWT para autenticação e autorização)
* Springdoc OpenAPI (para documentação da API)
## Funcionalidades
* CRUD de Tópicos: Criar, ler, atualizar e deletar tópicos.
* Gerenciamento de Respostas: Adicionar e visualizar respostas em tópicos.
* Autenticação e Autorização: Usando JWT e chaves RSA.
* Gerenciamento de Perfis de Usuário: Atribuição de perfis como ADMIN, MODERADOR e PADRAO.
* Integração com Redis: Para funções de cache e logout.

## Configuração

### Clone o repositório:

```bash
git clone https://github.com/usuario/forumhub.git
cd forumhub
```
### Construa o projeto com Maven:
```bash
Copiar código
mvn clean install
```

### Configure o Docker Compose:

O docker-compose.yml está configurado para criar e iniciar contêineres para a aplicação, Redis e MySQL. Você pode iniciar todos os serviços com:

```bash
Copiar código
docker-compose up
```
### Acesse a aplicação:
A aplicação estará disponível em http://localhost:8080.

## Deploy
A aplicação está disponível no Render. Você pode testá-la aqui. Note que a API pode demorar um pouco para responder a primeira requisição devido ao hibernamento após um certo tempo de inatividade.
<br>
<br>
[Deploy aqui!](https://forum-hub.onrender.com/swagger-ui/index.html)


## Documentação da API
A documentação da API está disponível em Swagger UI.
<br>
<br>
[Documentação aqui!](https://forum-hub.onrender.com/swagger-ui/index.html)

## Migrações de Banco de Dados
As migrações são gerenciadas pelo Flyway. As alterações no esquema do banco de dados serão aplicadas automaticamente quando a aplicação for iniciada.

## Contribuições
Sinta-se à vontade para abrir issues e enviar pull requests para melhorias e correções.
