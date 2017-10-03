## Nome do Aluno

Ricardo Furtado Alves de Paula

## Matricula

201576038

## Curso

Sistemas de Informação

## Cenário de Uso do Software

Esse sistema foi desenvolvido pensando em carrinhos de hot dog, que utilizam o conceito de "Comandas", algo que pode ser perdido, rasgado ou simplesmente ignorado pelos clientes. Com esse sistema, fica fácil e rápido gerenciar e atender todos os seus Clientes (comandas) ativos.

## Modelo de Dados Utilizados



## Levantamento dos Campos Utilizados 

Foram criados botões com as principais ações do sistema (inclusão/exclusão de comanda/pedido, encerrar conta). Foram criados painéis de Comandas e de Pedidos (relativos a cada comanda), exibindo todos os pedidos ativos e sua quantidade. Na listagem de comandas, é possível ver o nome da comanda e seu valor total atual.

Na tela de Criação de Comanda é necessário informar apenas o nome e ela será criada com o valor inicial de R$ 0,00.
Na tela de Novo Pedido, é possível fazer seu pedido personalizado selecionando as opções dos JComboBox's de "Pães", "Recheios" e "Queijos", além de informar a quantidade do pedido.

## Pontos importantes do funcionamento da interface

1. Não é possível excluir uma comanda, caso ela possua um pedido em aberto.
2. Não é possível incluir um pedido, caso não exista uma comanda selecionada.
3. Não é possível fechar uma comanda, caso ela não tenha sido selecionada.
4. Existe uma validação ("Sim/Não") feita para o usuário no momento de realizar um pedido e fechar a conta.
5. Não é possível inclui um pedido sem quantidade.

## Pontos de apresentaram maior dificuldade de implementação

Os pontos de maior dificuldade foi realizar os tratamentos de exceção e o controle nas entradas do usuário.

## Pontos onde podem ser realizadas melhorias futuras

Os próximos passos dessa aplicação é começar a fazer persistência de dados, fazendo uso de um banco de dados. Além, de melhores validações nas utilizações do usuário.
