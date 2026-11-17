# language: pt
Funcionalidade: Listagem de produtos
  Como responsável pelo estoque da loja
  Quero listar todos os produtos cadastrados
  Para ter uma visão geral do que a loja tem

  # Cenário positivo
  Cenário: Listar os produtos cadastrados
    Dado que o estoque possui o produto de código "001"
    E que o estoque possui o produto de código "002"
    E que o estoque possui o produto de código "003"
    Quando listo todos os produtos do estoque
    Então a listagem deve conter 3 produtos
    E a listagem deve conter o produto de código "001"
    E a listagem deve conter o produto de código "002"
    E a listagem deve conter o produto de código "003"

  # Partição de equivalência / valor limite: nenhum, um e vários produtos
  Esquema do Cenário: Listar conforme a quantidade de produtos cadastrados
    Dado que o estoque possui <cadastrados> produtos cadastrados
    Quando listo todos os produtos do estoque
    Então a listagem deve conter <listados> produtos

    Exemplos:
      | cadastrados | listados |
      | 0           | 0        |
      | 1           | 1        |
      | 2           | 2        |
      | 5           | 5        |

  # Cenário negativo: produto excluído não aparece
  Cenário: Não listar o produto que foi excluído
    Dado que o estoque possui o produto de código "001"
    E que o estoque possui o produto de código "002"
    Quando excluo o produto de código "001"
    E listo todos os produtos do estoque
    Então a listagem deve conter 1 produtos
    E a listagem não deve conter o produto de código "001"
    E a listagem deve conter o produto de código "002"

  # Transição de estado: a listagem reflete o estoque no momento da consulta
  Cenário: Listar depois de excluir todos os produtos
    Dado que o estoque possui o produto de código "001"
    Quando excluo o produto de código "001"
    E listo todos os produtos do estoque
    Então a listagem deve conter 0 produtos
