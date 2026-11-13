# language: pt
Funcionalidade: Cadastro de produtos
  Como responsável pelo estoque da loja
  Quero cadastrar produtos
  Para controlar o que a loja tem disponível

  Contexto:
    Dado que estou no sistema de estoque

  # RN01 a RN04 - cenário positivo
  Cenário: Cadastrar produto com sucesso
    Quando cadastro um produto com código "001", nome "Blusa", categoria "Blusa", tamanho "M", cor "Preta", preço "59.90" e quantidade "10"
    Então o resultado deve ser "Produto cadastrado com sucesso"
    E o produto "001" deve estar cadastrado

  # RN02 - código duplicado
  Cenário: Cadastrar produto com código duplicado
    Dado que já existe um produto com código "001"
    Quando tento cadastrar outro produto com o código "001"
    Então o resultado deve ser "Já existe um produto com este código"
    E o produto "001" deve continuar com o nome "Blusa"

  # RN03 - técnica de valor limite
  Esquema do Cenário: Validar o preço do produto
    Quando cadastro um produto com código "002", nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "<preco>" e quantidade "5"
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | preco | resultado                           |
      | -1    | O preço deve ser maior que zero     |
      | -0.01 | O preço deve ser maior que zero     |
      | 0     | O preço deve ser maior que zero     |
      | 0.01  | Produto cadastrado com sucesso      |
      | 59.90 | Produto cadastrado com sucesso      |

  # RN04 - técnica de valor limite
  Esquema do Cenário: Validar a quantidade inicial do produto
    Quando cadastro um produto com código "003", nome "Short", categoria "Short", tamanho "M", cor "Azul", preço "49.90" e quantidade "<quantidade>"
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | quantidade | resultado                                 |
      | -10        | A quantidade inicial não pode ser negativa |
      | -1         | A quantidade inicial não pode ser negativa |
      | 0          | Produto cadastrado com sucesso            |
      | 1          | Produto cadastrado com sucesso            |

  # RN01 - técnica de partição de equivalência (um campo vazio por vez)
  Esquema do Cenário: Exigir os campos obrigatórios
    Quando cadastro um produto com código "<codigo>", nome "<nome>", categoria "<categoria>", tamanho "<tamanho>", cor "<cor>", preço "59.90" e quantidade "10"
    Então o resultado deve ser "Todos os campos obrigatórios devem ser preenchidos"

    Exemplos:
      | codigo | nome  | categoria | tamanho | cor   |
      |        | Blusa | Blusa     | M       | Preta |
      | 006    |       | Blusa     | M       | Preta |
      | 006    | Blusa |           | M       | Preta |
      | 006    | Blusa | Blusa     |         | Preta |
      | 006    | Blusa | Blusa     | M       |       |