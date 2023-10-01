# Simulação de Transação Bancária.

# Objetivo do Projeto:
- Simular a realização de uma transferência bancária 
entre dois usuários cadastrados na base de dados;

# Especificações do Projeto:
- Transferências entre mesma conta não são permitidas.

- Transferências entre mesmo usuário são permitidas, quando para contas diferentes.

- Autenticação do usuário para acesso a plataforma; 
(Atualmente implementado sem o conceito de sessão, com tratamento de erros temporário);

- Senha de autenticação com os requisitos: 
8 dígitos, caracteres especiais, letras maiúsculas e minúsculas;

- Limite de valor máximo de transferência definido de acordo com seu tipo:
PIX: R$ 5 mil;
TED: Valor maior que R$ 5 mil e menor ou igual a R$ 10 mil;
DOC: Valor maior que R$ 10 mil;

- Mensagem de conclusão: 
Transferência executada com sucesso ou Erro justificando o ocorrido.

# Andamento do Projeto:
- Front End básico implementado para acesso em desktop/notebook, sem responsividade para smartphone;
- Back End inicial implementado, temporáriamente sem elaboração do conceito de sessão e security;

# Tecnologias Utilizadas:
Front-End:
- HTML;
- CSS;
- Javascript;

Back-End:
- Java;
- Spring Boot;
- Thymeleaf;
- MySQL;
 
# Executando o Programa

Para executar o projeto "SimulacaoBancaria" desenvolvido com Spring Boot em sua máquina local, siga estas etapas simples:

**Pré-requisitos:**

- Certifique-se de ter o Java JDK instalado em sua máquina. Você pode verificar se o Java está instalado executando o seguinte comando no seu terminal:

  ```
  java -version
  ```

  Certifique-se de que a versão do Java seja 8 ou superior.

- Certifique-se de ter o Apache Maven instalado em sua máquina. Você pode verificar se o Maven está instalado com o seguinte comando:

  ```
  mvn -version
  ```

  O Maven é usado para compilar e construir o projeto.

**Passos para Execução:**

1. Clone o repositório:

   ```bash
   git clone https://github.com/klaynmolina/SimulacaoBancaria.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd SimulacaoBancaria
   ```

3. Compile o projeto usando o Maven:

   ```bash
   mvn clean install
   ```

   Isso compilará o projeto, baixará todas as dependências e construirá o arquivo JAR executável.

4. Após a conclusão da compilação, você pode executar o aplicativo Spring Boot com o seguinte comando:

   ```bash
   java -jar target/SimulacaoTransacaoBancaria-0.0.1-SNAPSHOT.jar
   ```

   Certifique-se de que o nome do arquivo JAR corresponda ao gerado no passo anterior.

5. O aplicativo Spring Boot será iniciado e estará disponível em `http://localhost:8080/pagina-inicial`.

Agora você pode acessar o aplicativo da simulação bancária em seu navegador ou usar ferramentas como o Postman para interagir com a API.

Lembre-se de que este é um projeto de exemplo e não contém dados reais de bancos ou transações financeiras. 
Certifique-se de não compartilhar informações confidenciais ao interagir com este aplicativo.
