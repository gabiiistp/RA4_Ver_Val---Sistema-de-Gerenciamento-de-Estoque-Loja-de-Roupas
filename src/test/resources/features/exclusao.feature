# language: pt
Funcionalidade: Exclusão de produtos
  Como responsável pelo estoque da loja
  Quero excluir produtos
  Para manter o cadastro sem peças que a loja não vende mais

  # Cenário positivo
  Cenário: Excluir produto existente
    Dado que o estoque possui o produto de código "001"
    Quando excluo o produto de código "001"
    Então a exclusão deve resultar em "Produto excluído com sucesso"
    E o produto de código "001" não deve mais existir no estoque

  # RN11 - partição de equivalência: códigos que não existem
  Esquema do Cenário: Excluir produto inexistente
    Dado que o estoque possui o produto de código "001"
    Quando excluo o produto de código "<codigo>"
    Então a exclusão deve resultar em "Produto não encontrado"
    E o produto de código "001" ainda deve existir no estoque

    Exemplos:
      | codigo | situação                         |
      | 999    | código que nunca foi cadastrado  |
      | abc    | código com formato diferente     |
      | 0001   | código parecido com um existente |

  # RN11 - produto já excluído
  Cenário: Excluir o mesmo produto duas vezes
    Dado que o estoque possui o produto de código "001"
    Quando excluo o produto de código "001"
    E excluo o produto de código "001"
    Então a exclusão deve resultar em "Produto não encontrado"

  # Excluir não deve afetar os outros produtos
  Cenário: Excluir um produto mantém os outros
    Dado que o estoque possui o produto de código "001"
    E que o estoque possui o produto de código "002"
    Quando excluo o produto de código "001"
    Então o produto de código "002" ainda deve existir no estoque