# Sistema Integrado de Gestão e Análise de Fretes

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![Pandas](https://img.shields.io/badge/Pandas-150458?style=for-the-badge&logo=pandas&logoColor=white)

## Sobre o Projeto
Este é um projeto Full Stack focado em engenharia de software e ciência de dados. O objetivo do sistema é gerenciar o registro de encomendas logísticas, calcular automaticamente os custos de frete baseados no progresso da entrega e gerar inteligência visual a partir desses dados.

O projeto foi dividido em duas fases arquiteturais:
1. **Back-End (API REST):** Construído em Java com Spring Boot, responsável por receber requisições, aplicar regras de negócio (cálculo de frete) e persistir os dados.
2. **Análise de Dados:** Script em Python que se conecta diretamente ao banco de dados relacional para extrair, consolidar e visualizar métricas financeiras usando gráficos.

## Arquitetura

* **API REST:** Porta de entrada para cadastro das encomendas via método `POST` e listagem via `GET`.
* **Regra de Negócio:** Camada `Service` no Java calcula uma taxa base e adiciona custos de logística regional conforme a porcentagem de progresso da entrega.
* **Persistência:** PostgreSQL, acessado via Spring Data JPA (Hibernate).
* **Inteligência:** O Python atua como consumidor dos dados brutos, transformando-os em DataFrames para estatísticas e renderizando gráficos de barras com Matplotlib.

## Tecnologias Utilizadas
* **Back-End:** Java 17+, Spring Boot (Web, Data JPA).
* **Banco de Dados:** PostgreSQL.
* **Análise de Dados:** Python 3, Pandas, Matplotlib, Psycopg2.
* **Ferramentas:** VS Code, Thunder Client/Postman, pgAdmin.

## Como Executar

### 1. Banco de Dados
Crie um banco de dados no PostgreSQL chamado `logistica_db`.

### 2. Rodando a API (Java)
1. Navegue até a pasta `sistema-fretes`.
2. Configure suas credenciais do PostgreSQL no arquivo `application.properties`.
3. Execute o arquivo principal `SistemaFretesApplication.java`.
4. A API estará rodando em `http://localhost:8080/api/encomendas`.

### 3. Rodando a Análise (Python)
1. Navegue até a pasta `analise_logistica`.
2. Instale as dependências executando: `pip install pandas psycopg2-binary matplotlib`
3. Execute o script com o comando: `python analise.py`
4. Um gráfico será exibido demonstrando o Custo Médio de Frete por Região.

---
*Desenvolvido como projeto de portfólio para demonstrar integração entre Sistemas Web (Java) e Análise de Dados (Python).*