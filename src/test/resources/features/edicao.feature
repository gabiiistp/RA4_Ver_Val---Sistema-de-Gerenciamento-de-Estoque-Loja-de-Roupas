# language: pt
Funcionalidade: Edição de produtos
  Como responsável pelo estoque da loja
  Quero editar os dados de um produto já cadastrado
  Para corrigir informações como nome, tamanho, cor e preço sem perder o histórico do estoque

  # Cenário positivo
  Cenário: Editar os dados de um produto existente
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "79.90" e estoque mínimo 4
    Então a edição deve resultar em "Produto editado com sucesso"
    E o produto de código "001" deve ter nome "Blusa Floral" e preço "79.90"

  # Transição de estado: editar não mexe na quantidade (ela só muda por entrada e saída)
  Cenário: Editar um produto não altera a quantidade em estoque
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "79.90" e estoque mínimo 4
    Então o produto de código "001" deve ficar com 10 unidades em estoque

  # RN11 - partição de equivalência: códigos que não existem
  Esquema do Cenário: Editar produto inexistente
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "<codigo>" com nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "89.90" e estoque mínimo 1
    Então a edição deve resultar em "Produto não encontrado"
    E o produto "001" deve continuar com o nome "Blusa"

    Exemplos:
      | codigo | situação                         |
      | 999    | código que nunca foi cadastrado  |
      | abc    | código com formato diferente     |
      | 0001   | código parecido com um existente |

  # RN11 - produto que foi excluído
  Cenário: Editar um produto que já foi excluído
    Dado que o estoque possui o produto de código "001"
    E excluo o produto de código "001"
    Quando edito o produto de código "001" com nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "89.90" e estoque mínimo 1
    Então a edição deve resultar em "Produto não encontrado"

  # RN01 - partição de equivalência: um campo obrigatório vazio por vez
  Esquema do Cenário: Exigir os campos obrigatórios na edição
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "<nome>", categoria "<categoria>", tamanho "<tamanho>", cor "<cor>", preço "79.90" e estoque mínimo 4
    Então a edição deve resultar em "Todos os campos obrigatórios devem ser preenchidos"
    E o produto "001" deve continuar com o nome "Blusa"

    Exemplos:
      | nome         | categoria | tamanho | cor  |
      |              | Blusa     | G       | Azul |
      | Blusa Floral |           | G       | Azul |
      | Blusa Floral | Blusa     |         | Azul |
      | Blusa Floral | Blusa     | G       |      |

  # RN03 - técnica de valor limite: a fronteira está entre 0 e 0.01
  Esquema do Cenário: Validar o preço na edição
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "<preco>" e estoque mínimo 4
    Então a edição deve resultar em "<resultado>"

    Exemplos:
      | preco | resultado                       |
      | -1    | O preço deve ser maior que zero |
      | -0.01 | O preço deve ser maior que zero |
      | 0     | O preço deve ser maior que zero |
      | 0.01  | Produto editado com sucesso     |
      | 79.90 | Produto editado com sucesso     |
