# 📦 API Microsserviços com Java Spring Boot

Bem-vindo ao projeto **API Microsserviços**! Este projeto implementa um conjunto de microsserviços utilizando **Spring Boot**, com suporte a segurança via OAuth 2.0 e um API Gateway para gerenciamento de rotas.

## 🛠️ Como iniciar?

Siga os passos abaixo para configurar e executar o projeto.

### 1️⃣ Faça o clone do repositório

Utilize o comando abaixo para clonar o repositório na sua máquina local:
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd api-microsservicos
```

### 2️⃣ Configure os containers no Docker

Certifique-se de que o Docker esteja instalado na sua máquina. Suba os containers necessários utilizando o comando:
```bash
docker-compose up -d
```
Isso iniciará os serviços **Keycloak** e o banco de dados PostgreSQL.

### 3️⃣ Execute as aplicações Spring Boot

Em cada um dos projetos (`inventory-service`, `order-service`, `product-service`, `api-gateway`):
1. Abra o projeto em sua IDE favorita (ex.: IntelliJ IDEA ou Eclipse).
2. Execute a classe principal `Application` de cada serviço.
3. Certifique-se de que as portas configuradas no `application.yml` ou `application.properties` estejam disponíveis.

### 4️⃣ Teste as requisições de API

#### 🛒 `order-service`
- **POST**: Endpoint para criar um pedido.
- **GET**: Endpoint para buscar os pedidos criados.

#### 📦 `inventory-service`
- **GET**: Endpoint para verificar a disponibilidade de estoque.

#### 🛍️ `product-service`
- **POST**: Criar um novo produto.
- **GET**: Buscar todos os produtos cadastrados.

Utilize uma ferramenta como **Postman** ou **Insomnia** para realizar as requisições nos endpoints descritos.

### 5️⃣ Configure o Keycloak

O Keycloak gerencia a autenticação e autorização via OAuth 2.0. Acesse o painel do Keycloak em:
```http
http://localhost:8181/
```

1. **Registrar Realm**: Crie um novo *Realm* chamado `spring-microservices-security-realm`.
2. **Criar Clientes**: Adicione um cliente chamado `spring-client-credentials-id` com suporte a *Client Credentials*.

### 6️⃣ Teste as APIs no Postman ou Insomnia

Adicione os seguintes headers em suas requisições:
- `Authorization: Bearer <seu-token>`
- `Content-Type: application/json`

### 🔍 Documentação

O **Swagger UI** está disponível no API Gateway em:
```http
http://localhost:9000/swagger-ui.html
```
Aqui você encontra a documentação consolidada de todos os microsserviços.

### 🚀 Finalize e teste tudo
Teste os serviços integrados e garanta que as requisições estão funcionando conforme esperado. Para problemas ou dúvidas, sinta-se à vontade para abrir uma issue no repositório.

---

Esperamos que este guia tenha sido útil! Boa sorte e happy coding! 💻✨
