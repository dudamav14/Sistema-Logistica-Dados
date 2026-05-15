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
    print(dados.describe())

    print("\nGerando gráfico de custo médio por região...")
    # Agrupa (groupby) os dados pela 'regiao_destino' e calcular a média (mean) do 'custo_frete'
    media_por_regiao = dados.groupby('regiao_destino')['custo_frete'].mean()

    # Cria um gráfico do tipo 'bar' (barras) com uma cor bacana
    media_por_regiao.plot(kind='bar', color='royalblue', edgecolor='black')

    # Adiciona títulos
    plt.title('Custo Médio de Frete por Região', fontsize=14)
    plt.xlabel('Região de Destino', fontsize=12)
    plt.ylabel('Custo Médio (R$)', fontsize=12)

    # Rotaciona os nomes do eixo X (Sudeste, Sul, etc) para ficarem retos (0 graus)
    plt.xticks(rotation=0)

    # Exibe a janela com o gráfico pronto
    plt.show()