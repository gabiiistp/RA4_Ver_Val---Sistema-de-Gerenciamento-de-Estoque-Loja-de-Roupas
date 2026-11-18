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

  # editar não mexe na quantidade
  Cenário: Editar um produto não altera a quantidade em estoque
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "79.90" e estoque mínimo 4
    Então o produto de código "001" deve ficar com 10 unidades em estoque

  # RN11 - códigos que não existem
  Cenário: Editar produto com código que nunca foi cadastrado
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "999" com nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "89.90" e estoque mínimo 1
    Então a edição deve resultar em "Produto não encontrado"
    E o produto "001" deve continuar com o nome "Blusa"

  Cenário: Editar produto com código de formato diferente
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "abc" com nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "89.90" e estoque mínimo 1
    Então a edição deve resultar em "Produto não encontrado"
    E o produto "001" deve continuar com o nome "Blusa"

  Cenário: Editar produto com código parecido com um existente
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "0001" com nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "89.90" e estoque mínimo 1
    Então a edição deve resultar em "Produto não encontrado"
    E o produto "001" deve continuar com o nome "Blusa"

  # RN11 - produto que foi excluído
  Cenário: Editar um produto que já foi excluído
    Dado que o estoque possui o produto de código "001"
    E excluo o produto de código "001"
    Quando edito o produto de código "001" com nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "89.90" e estoque mínimo 1
    Então a edição deve resultar em "Produto não encontrado"

  # RN01 - um campo obrigatório vazio por vez
  Cenário: Recusar edição com nome vazio
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "", categoria "Blusa", tamanho "G", cor "Azul", preço "79.90" e estoque mínimo 4
    Então a edição deve resultar em "Todos os campos obrigatórios devem ser preenchidos"
    E o produto "001" deve continuar com o nome "Blusa"

  Cenário: Recusar edição com categoria vazia
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "", tamanho "G", cor "Azul", preço "79.90" e estoque mínimo 4
    Então a edição deve resultar em "Todos os campos obrigatórios devem ser preenchidos"
    E o produto "001" deve continuar com o nome "Blusa"

  Cenário: Recusar edição com tamanho vazio
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "", cor "Azul", preço "79.90" e estoque mínimo 4
    Então a edição deve resultar em "Todos os campos obrigatórios devem ser preenchidos"
    E o produto "001" deve continuar com o nome "Blusa"

  Cenário: Recusar edição com cor vazia
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "", preço "79.90" e estoque mínimo 4
    Então a edição deve resultar em "Todos os campos obrigatórios devem ser preenchidos"
    E o produto "001" deve continuar com o nome "Blusa"

  # RN03 - valor limite: a fronteira está entre 0 e 0.01
  Cenário: Recusar edição com preço -1
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "-1" e estoque mínimo 4
    Então a edição deve resultar em "O preço deve ser maior que zero"

  Cenário: Recusar edição com preço -0.01
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "-0.01" e estoque mínimo 4
    Então a edição deve resultar em "O preço deve ser maior que zero"

  Cenário: Recusar edição com preço 0
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "0" e estoque mínimo 4
    Então a edição deve resultar em "O preço deve ser maior que zero"

  Cenário: Aceitar edição com o menor preço válido 0.01
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "0.01" e estoque mínimo 4
    Então a edição deve resultar em "Produto editado com sucesso"

  Cenário: Aceitar edição com preço 79.90
    Dado que o estoque possui o produto de código "001"
    Quando edito o produto de código "001" com nome "Blusa Floral", categoria "Blusa", tamanho "G", cor "Azul", preço "79.90" e estoque mínimo 4
    Então a edição deve resultar em "Produto editado com sucesso"