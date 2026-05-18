# Sistema Integrado de Gestão e Análise de Fretes com IA (Full Stack)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Gemini AI](https://img.shields.io/badge/Google%20Gemini-8E75B2?style=for-the-badge&logo=googlebard&logoColor=white)

## Sobre o Projeto
Este é um projeto Full Stack com foco em engenharia de software e inteligência artificial. O sistema gerencia o registro de encomendas logísticas, calcula dinamicamente os custos de frete baseados na distância e região, e utiliza IA generativa para atuar como um analista de dados automatizado.

O projeto demonstra a capacidade de integrar múltiplas camadas tecnológicas em uma única solução coesa, aplicando o conceito de **RAG (Retrieval-Augmented Generation)**.

## Arquitetura da Solução

* **Front-End:** Interface responsiva e reativa construída nativamente com HTML5, CSS3 e Vanilla JavaScript, utilizando a *Fetch API* para comunicação assíncrona.
* **Back-End (API REST):** Construído em Java com Spring Boot, gerenciando endpoints de cadastro, listagem e integração com serviços externos.
* **Regra de Negócio Dinâmica:** Cálculo inteligente do valor de frete baseado em uma fórmula que considera: `Taxa Base + (Distância em Km × Valor do Km Regional)`.
* **Integração com IA:** Serviço nativo no Java que se comunica via HTTP (REST) com a API do **Google Gemini (2.5 Flash)**. O sistema extrai os dados do banco e injeta o contexto no prompt para a IA gerar relatórios executivos sob demanda.
* **Persistência de Dados:** Modelagem relacional no PostgreSQL, operada através do Spring Data JPA (Hibernate).

## Tecnologias Utilizadas
* **Back-End:** Java 17+, Spring Boot (Web, Data JPA).
* **Front-End:** HTML, CSS, JavaScript.
* **Inteligência Artificial:** Google Gemini API.
* **Banco de Dados:** PostgreSQL.
* **Ferramentas:** VS Code, pgAdmin.

## Como Executar

### 1. Preparando o Ambiente
1. Crie um banco de dados no PostgreSQL chamado `logistica_db`.
2. Configure suas credenciais (`username` e `password`) no arquivo `application.properties`.
3. Gere uma API Key no Google AI Studio e insira na propriedade `gemini.api.key`.

### 2. Rodando a Aplicação
1. Execute a classe principal `SistemaFretesApplication.java`.
2. O Spring Boot criará as tabelas automaticamente (`ddl-auto=create/update`).
3. Acesse o sistema pelo navegador na URL raiz: `http://localhost:8080/`.
4. Utilize a interface para cadastrar encomendas e gerar a análise executiva automatizada com a Inteligência Artificial.