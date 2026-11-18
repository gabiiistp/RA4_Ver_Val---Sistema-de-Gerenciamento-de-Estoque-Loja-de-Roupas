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

  # nenhum, um e vários produtos
  Cenário: Listar quando não há nenhum produto cadastrado
    Dado que o estoque possui 0 produtos cadastrados
    Quando listo todos os produtos do estoque
    Então a listagem deve conter 0 produtos

  Cenário: Listar quando há apenas 1 produto cadastrado
    Dado que o estoque possui o produto de código "001"
    Quando listo todos os produtos do estoque
    Então a listagem deve conter 1 produtos

  Cenário: Listar quando há 2 produtos cadastrados
    Dado que o estoque possui o produto de código "001"
    E que o estoque possui o produto de código "002"
    Quando listo todos os produtos do estoque
    Então a listagem deve conter 2 produtos

  Cenário: Listar quando há 5 produtos cadastrados
    Dado que o estoque possui o produto de código "001"
    E que o estoque possui o produto de código "002"
    E que o estoque possui o produto de código "003"
    E que o estoque possui o produto de código "004"
    E que o estoque possui o produto de código "005"
    Quando listo todos os produtos do estoque
    Então a listagem deve conter 5 produtos

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