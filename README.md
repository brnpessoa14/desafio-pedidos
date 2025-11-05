# Desafio Técnico  Sistema de Pedidos por String Posicional

Mini sistema composto por dois serviços Spring Boot e um frontend Angular. O objetivo é demonstrar raciocínio, decisões técnicas e evolução por commits incrementais, além da entrega final.

## Escopo funcional

1. Módulo A  (Entrada/Gateway)  
   1.1 Endpoint POST  /pedidos/posicional  
   1.2 Recebe no corpo uma linha posicional com exatamente 40 caracteres e Content-Type text/plain  
   1.3 Validações  
       A  campos alfanuméricos devem ser preenchidos e completados com espaços à direita  
       N  campos numéricos devem ser preenchidos e completados com zeros à esquerda  
       quantidade entre 01 e 99  
   1.4 Conversão para entidade Pedido  
       id, tipoLanche, proteina, acompanhamento, quantidade, bebida, valor, status, criadoEm  
   1.5 Cálculo de valor  
       preço base por combinação e eventual desconto de 10 por cento para HAMBURGUER  CARNE  SALADA  
   1.6 Persistência  
       salva com status RECEBIDO  
   1.7 Mensageria  
       publica na fila pedidos.recebidos um JSON contendo pedidoId  
   1.8 Resposta  
       retorna 201 Created com JSON do pedido salvo

2. Módulo B  (Entrega/Processor)  
   2.1 Consome mensagens da fila pedidos.recebidos  
   2.2 Para cada pedidoId, atualiza o status do pedido para ENTREGUE e persiste  
   2.3 Disponibiliza consultas  
       GET  /pedidos  
       GET  /pedidos/{id}

3. Frontend Angular  
   3.1 Tela Novo Pedido  
       textarea para a linha de 40 caracteres, botão Enviar chamando POST /pedidos/posicional, exibição de sucesso ou erro  
   3.2 Tela Pedidos  
       tabela com id, tipoLanche, proteina, acompanhamento, quantidade, bebida, valor, status e ação de atualizar

## Layout da string posicional  tamanho total  40

1. tipoLanche  posições 1 a 10  tamanho 10  A  
2. proteina  posições 11 a 20  tamanho 10  A  
3. acompanhamento  posições 21 a 30  tamanho 10  A  
4. quantidade  posições 31 a 32  tamanho 2  N  valores válidos de 01 a 99  
5. bebida  posições 33 a 40  tamanho 8  A

Observação  
A  preenche com espaços à direita até o tamanho do campo  
N  preenche com zeros à esquerda até o tamanho do campo

## Exemplos para teste manual

1. HAMBURGUER  CARNE      SALADA     01COCA    (aplica desconto de 10 por cento)
2. PASTEL     FRANGO     BACON      02SUCO    

*** Os espaços são parte da string, seu comprimento total deve ser EXATAMENTE 40.

## Arquitetura do repositório

1. Monorepo com três pastas na raiz  
   1.1 gateway  serviço Spring Boot do módulo A  
   1.2 processor  serviço Spring Boot do módulo B  
   1.3 frontend  aplicativo Angular

2. Banco de dados relacional para a tabela pedidos  
3. Fila de mensageria pedidos.recebidos para integração entre serviços  
4. Testes com foco no analisador da string e nas regras de preço  
5. Containers para infraestrutura local durante o desenvolvimento

## Padrões de qualidade

1. Clean Code e nomenclatura descritiva  
2. Testes unitários cobrindo parser e precificação  
3. Logs úteis e mensagens claras de validação  
4. Documentação concisa no repositório
5. Commits incrementais para controle de versão do código

