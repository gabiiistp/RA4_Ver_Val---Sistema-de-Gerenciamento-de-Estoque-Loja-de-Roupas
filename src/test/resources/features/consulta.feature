# language: pt
Funcionalidade: Consulta de produtos
  Como responsável pelo estoque da loja
  Quero buscar produtos pelo nome e identificar os que estão com estoque baixo
  Para encontrar peças rapidamente e saber o que precisa ser reposto

  # RN09 · Buscar produto pelo nome

  # RN09 - cenário positivo
  Cenário: Buscar produtos que contêm o nome informado
    Dado que o catálogo tem o produto "Blusa Floral" de código "001" com 10 em estoque e mínimo 2
    E que o catálogo tem o produto "Blusa Lisa" de código "002" com 5 em estoque e mínimo 2
    E que o catálogo tem o produto "Calça Jeans" de código "003" com 8 em estoque e mínimo 3
    Quando busco por produtos com o nome "Blusa"
    Então a busca deve retornar 2 produtos

  # RN09 - busca por parte do nome
  Cenário: Buscar por parte do nome
    Dado que o catálogo tem o produto "Blusa Floral" de código "001" com 10 em estoque e mínimo 2
    E que o catálogo tem o produto "Blusa Lisa" de código "002" com 5 em estoque e mínimo 2
    Quando busco por produtos com o nome "Floral"
    Então a busca deve retornar 1 produtos
    E a busca deve conter o produto de código "001"

  # RN09 - a busca não diferencia maiúsculas de minúsculas
  Cenário: Buscar ignorando maiúsculas e minúsculas
    Dado que o catálogo tem o produto "Blusa Floral" de código "001" com 10 em estoque e mínimo 2
    E que o catálogo tem o produto "Blusa Lisa" de código "002" com 5 em estoque e mínimo 2
    Quando busco por produtos com o nome "blUSa"
    Então a busca deve retornar 2 produtos

  # RN09 - cenário negativo: nenhum produto corresponde
  Cenário: Buscar um nome que não existe no catálogo
    Dado que o catálogo tem o produto "Blusa Floral" de código "001" com 10 em estoque e mínimo 2
    Quando busco por produtos com o nome "Vestido"
    Então a busca deve retornar 0 produtos

  # RN09 - cenário negativo: nome de busca vazio
  Cenário: Buscar sem informar o nome
    Dado que o catálogo tem o produto "Blusa Floral" de código "001" com 10 em estoque e mínimo 2
    Quando busco por produtos com o nome ""
    Então a busca deve ser recusada com a mensagem "O nome para busca não pode estar vazio"

  # ============================================================
  # RN10 · Estoque baixo (quantidade <= estoque mínimo)
  # ============================================================

  # RN10 - cenário positivo
  Cenário: Identificar produto com quantidade abaixo do mínimo
    Dado que o catálogo tem o produto "Blusa Lisa" de código "001" com 1 em estoque e mínimo 5
    Quando consulto os produtos com estoque baixo
    Então a consulta de estoque baixo deve retornar 1 produtos
    E o alerta de estoque baixo deve conter o produto de código "001"

  # RN10 - lista apenas os produtos que estão no limite ou abaixo
  Cenário: Listar apenas os produtos com estoque baixo
    Dado que o catálogo tem o produto "Blusa Baixa" de código "001" com 1 em estoque e mínimo 5
    E que o catálogo tem o produto "Blusa Cheia" de código "002" com 20 em estoque e mínimo 5
    E que o catálogo tem o produto "Blusa Limite" de código "003" com 5 em estoque e mínimo 5
    Quando consulto os produtos com estoque baixo
    Então a consulta de estoque baixo deve retornar 2 produtos

  # RN10 - cenário negativo: ninguém está com estoque baixo
  Cenário: Nenhum produto com estoque baixo
    Dado que o catálogo tem o produto "Blusa Floral" de código "001" com 10 em estoque e mínimo 2
    E que o catálogo tem o produto "Calça Jeans" de código "002" com 8 em estoque e mínimo 3
    Quando consulto os produtos com estoque baixo
    Então a consulta de estoque baixo deve retornar 0 produtos

  # RN10 - Técnica de valor limite: a fronteira está entre o mínimo e o mínimo + 1
  Esquema do Cenário: Validar o limite do estoque mínimo
    Dado que o catálogo tem o produto "Blusa" de código "001" com <quantidade> em estoque e mínimo 5
    Quando consulto os produtos com estoque baixo
    Então a consulta de estoque baixo deve retornar <resultado> produtos

    Exemplos:
      | quantidade | resultado |
      | 4          | 1         |
      | 5          | 1         |
      | 6          | 0         |
