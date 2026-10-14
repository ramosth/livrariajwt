# 📖 API de Livraria com Spring Boot + JWT

![Java](https://img.shields.io/badge/Java-11-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=flat-square&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)

API REST de uma livraria online, desenvolvida na disciplina de **API REST com Java** da residência em software do **Serratec (2021)**.

A API cobre o fluxo completo de uma loja: cadastro de livros, autores e editoras, carrinho de compras, finalização da venda e notificação por e-mail. Tudo protegido por autenticação **JWT**.

## Funcionalidades

- 🔐 **Autenticação JWT:** login em `/auth` gera um token Bearer, validado por um filtro do Spring Security
- 📚 **Catálogo:** CRUD de livros, autores, editoras e classificações, além de busca por categoria
- 🖼️ **Arquivos:** download da capa (JPEG) e do PDF de cada livro
- 👤 **Leitores e endereços:** cadastro com preenchimento automático do endereço pela API do **ViaCEP**
- 🛒 **Carrinho e vendas:** adicionar e atualizar itens, finalizar a compra e acompanhar o status da venda
- ✉️ **E-mail:** envio de notificação com Spring Mail
- 📦 **DTOs** para separar as entidades do que é exposto na API

## Principais endpoints

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/auth` | Autentica e retorna o token JWT |
| `GET` | `/livro/todos` | Lista todos os livros |
| `GET` | `/livro/categoria/{categoria}` | Livros por categoria |
| `GET` | `/livro/capa/{codigo}` | Capa do livro (JPEG) |
| `POST` | `/leitor` | Cadastra um leitor |
| `GET` | `/carrinho/{email}` | Carrinho do leitor |
| `PUT` | `/carrinho` | Atualiza um item do carrinho |
| `POST` | `/carrinho/finalizar` | Finaliza a compra |
| `PUT` | `/venda/status` | Atualiza o status da venda |

## Estrutura

```
src/main/java/org/serratec/
├── dto/          # objetos de entrada e saída da API
├── models/       # entidades JPA
├── repository/   # acesso a dados (Spring Data JPA)
├── resources/    # controllers REST
├── security/     # configuração, filtro e serviço de token JWT
└── services/     # regras de negócio e envio de e-mail
```

## Como executar

**Pré-requisitos:** Java 11 e PostgreSQL.

1. Crie o banco `livrariaJWT` no PostgreSQL
2. Ajuste usuário e senha do banco (e do e-mail, se quiser) em `src/main/resources/application.properties`
3. Rode o projeto:

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

---

Desenvolvido por [Thamires Ramos](https://github.com/ramosth) · [LinkedIn](https://linkedin.com/in/thamires-ramos/)
