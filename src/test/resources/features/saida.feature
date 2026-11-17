# language: pt
Funcionalidade: Saída de produtos do estoque
  Como responsável pelo estoque da loja
  Quero registrar a saída de mercadoria
  Para manter a quantidade de cada produto atualizada e nunca vender o que não tenho

  # RN07 - cenário positivo
  Cenário: Registrar saída em produto existente
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 3 unidades do produto de código "001"
    Então a saída deve resultar em "Saída registrada com sucesso"
    E o produto de código "001" deve ficar com 7 unidades em estoque

  # RN07 - transição de estado: várias saídas se acumulam
  Cenário: Registrar duas saídas seguidas no mesmo produto
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 4 unidades do produto de código "001"
    E dou saída de 3 unidades do produto de código "001"
    Então o produto de código "001" deve ficar com 3 unidades em estoque

  # RN05 - transição de estado: a segunda saída considera o que a primeira já retirou
  Cenário: Recusar a segunda saída que passa do que sobrou
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 6 unidades do produto de código "001"
    E dou saída de 5 unidades do produto de código "001"
    Então a saída deve resultar em "Estoque insuficiente para a saída"
    E o produto de código "001" deve ficar com 4 unidades em estoque

  # RN06 + RN07 - transição de estado: a entrada aumenta o que pode sair
  Cenário: Registrar saída maior que o estoque inicial depois de uma entrada
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou entrada de 5 unidades no produto de código "001"
    E dou saída de 12 unidades do produto de código "001"
    Então a saída deve resultar em "Saída registrada com sucesso"
    E o produto de código "001" deve ficar com 3 unidades em estoque

  # RN05 - técnica de valor limite: a fronteira está entre o disponível (10) e o disponível + 1 (11)
  Esquema do Cenário: Validar a saída em relação ao estoque disponível
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de <quantidade> unidades do produto de código "001"
    Então a saída deve resultar em "<resultado>"
    E o produto de código "001" deve ficar com <estoque_final> unidades em estoque

    Exemplos:
      | quantidade | resultado                         | estoque_final |
      | 9          | Saída registrada com sucesso      | 1             |
      | 10         | Saída registrada com sucesso      | 0             |
      | 11         | Estoque insuficiente para a saída | 10            |

  # Técnica de valor limite: a fronteira está entre 0 e 1
  Esquema do Cenário: Validar a quantidade da saída
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de <quantidade> unidades do produto de código "001"
    Então a saída deve resultar em "<resultado>"
    E o produto de código "001" deve ficar com <estoque_final> unidades em estoque

    Exemplos:
      | quantidade | resultado                                     | estoque_final |
      | -10        | A quantidade da saída deve ser maior que zero | 10            |
      | -1         | A quantidade da saída deve ser maior que zero | 10            |
      | 0          | A quantidade da saída deve ser maior que zero | 10            |
      | 1          | Saída registrada com sucesso                  | 9             |

  # Tabela de decisão: produto existe? x quantidade suficiente?
  Esquema do Cenário: Decidir o resultado da saída (produto existe x estoque suficiente)
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de <quantidade> unidades do produto de código "<codigo>"
    Então a saída deve resultar em "<resultado>"
    E o produto de código "001" deve ficar com <estoque_001> unidades em estoque

    Exemplos:
      | codigo | quantidade | resultado                         | estoque_001 | situação                           |
      | 001    | 5          | Saída registrada com sucesso      | 5           | existe e estoque suficiente        |
      | 001    | 11         | Estoque insuficiente para a saída | 10          | existe e estoque insuficiente      |
      | 999    | 5          | Produto não encontrado            | 10          | não existe (quantidade suficiente) |
      | 999    | 11         | Produto não encontrado            | 10          | não existe (quantidade grande)     |

  # RN11 - produto que foi excluído
  Cenário: Registrar saída em produto já excluído
    Dado que o produto de código "001" tem 10 unidades em estoque
    E excluo o produto de código "001"
    Quando dou saída de 5 unidades do produto de código "001"
    Então a saída deve resultar em "Produto não encontrado"
