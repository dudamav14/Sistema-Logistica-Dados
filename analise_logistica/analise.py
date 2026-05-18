import pandas as pd
import psycopg2
import matplotlib.pyplot as plt

DB_HOST = "localhost"
DB_NAME = "logistica_db"
DB_USER = "postgres"
DB_PASS = "1234" 

def carregar_dados():
    try:
        # Abre a porta de conexão com o PostgreSQL
        print("Conectando ao banco de dados...")
        conexao = psycopg2.connect(
            host=DB_HOST,
            database=DB_NAME,
            user=DB_USER,
            password=DB_PASS
        )
        
        # O comando SQL para buscar TUDO da tabela
        comando_sql = "SELECT * FROM tb_encomendas;"
        df_encomendas = pd.read_sql_query(comando_sql, conexao)
        # Fecha a conexão para não sobrecarregar o banco
        conexao.close() 
        return df_encomendas

    except Exception as erro:
        print(f"Ocorreu um erro ao conectar: {erro}")
        return None


# Chama a função e guarda os dados na variável 'dados'
dados = carregar_dados()

if dados is not None:
    print("\n--- DADOS CARREGADOS COM SUCESSO ---")
    
    # O comando .head() mostra as primeiras 5 linhas da planilha
    print(dados.head())
    
    print("\n--- ESTATÍSTICAS BÁSICAS ---")
    # O comando .describe() calcula média, mínimo e máximo das colunas numéricas
    plt.figure(figsize=(8, 6))

    # Cria um gráfico de dispersão (pontos)
    plt.scatter(dados['distancia_km'], dados['custo_frete'], color='purple', alpha=0.7, edgecolors='black')

    # Títulos e rótulos
    plt.title('Impacto da Distância no Custo do Frete', fontsize=14)
    plt.xlabel('Distância (Km)', fontsize=12)
    plt.ylabel('Custo Final (R$)', fontsize=12)
    
    # Adiciona uma grade de fundo para facilitar a leitura
    plt.grid(True, linestyle='--', alpha=0.5)

    plt.show()