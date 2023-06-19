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
 
# URL para iniciar a página inicial do projeto: 
http://localhost:8080/pagina-inicial