# language: pt
Funcionalidade: Cadastro de produtos
  Como responsável pelo estoque da loja
  Quero cadastrar produtos
  Para controlar o que a loja tem disponível

  Contexto:
    Dado que estou no sistema de estoque

  # Cenário positivo (RN01 a RN04 atendidas)

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


  # RN03 - preço maior que zero (valor limite: -1, -0.01, 0, 0.01)

  Cenário: Recusar cadastro com preço -1
    Quando cadastro um produto com código "002", nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "-1" e quantidade "5"
    Então o resultado deve ser "O preço deve ser maior que zero"

  Cenário: Recusar cadastro com preço -0.01
    Quando cadastro um produto com código "002", nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "-0.01" e quantidade "5"
    Então o resultado deve ser "O preço deve ser maior que zero"

  Cenário: Recusar cadastro com preço 0
    Quando cadastro um produto com código "002", nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "0" e quantidade "5"
    Então o resultado deve ser "O preço deve ser maior que zero"

  Cenário: Aceitar cadastro com o menor preço válido 0.01
    Quando cadastro um produto com código "002", nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "0.01" e quantidade "5"
    Então o resultado deve ser "Produto cadastrado com sucesso"
    E o produto "002" deve estar cadastrado

  Cenário: Aceitar cadastro com preço 59.90
    Quando cadastro um produto com código "002", nome "Saia", categoria "Saia", tamanho "P", cor "Verde", preço "59.90" e quantidade "5"
    Então o resultado deve ser "Produto cadastrado com sucesso"
    E o produto "002" deve estar cadastrado


  # RN04 - quantidade inicial não negativa (valor limite: -10, -1, 0, 1)

  Cenário: Recusar cadastro com quantidade inicial -10
    Quando cadastro um produto com código "003", nome "Short", categoria "Short", tamanho "M", cor "Azul", preço "49.90" e quantidade "-10"
    Então o resultado deve ser "A quantidade inicial não pode ser negativa"

  Cenário: Recusar cadastro com quantidade inicial -1
    Quando cadastro um produto com código "003", nome "Short", categoria "Short", tamanho "M", cor "Azul", preço "49.90" e quantidade "-1"
    Então o resultado deve ser "A quantidade inicial não pode ser negativa"

  Cenário: Aceitar cadastro com quantidade inicial 0
    Quando cadastro um produto com código "003", nome "Short", categoria "Short", tamanho "M", cor "Azul", preço "49.90" e quantidade "0"
    Então o resultado deve ser "Produto cadastrado com sucesso"
    E o produto "003" deve estar cadastrado

  Cenário: Aceitar cadastro com quantidade inicial 1
    Quando cadastro um produto com código "003", nome "Short", categoria "Short", tamanho "M", cor "Azul", preço "49.90" e quantidade "1"
    Então o resultado deve ser "Produto cadastrado com sucesso"
    E o produto "003" deve estar cadastrado

  # RN01 - campos obrigatórios (um campo vazio por vez)

  Cenário: Recusar cadastro com código vazio
    Quando cadastro um produto com código "", nome "Blusa", categoria "Blusa", tamanho "M", cor "Preta", preço "59.90" e quantidade "10"
    Então o resultado deve ser "Todos os campos obrigatórios devem ser preenchidos"

  Cenário: Recusar cadastro com nome vazio
    Quando cadastro um produto com código "006", nome "", categoria "Blusa", tamanho "M", cor "Preta", preço "59.90" e quantidade "10"
    Então o resultado deve ser "Todos os campos obrigatórios devem ser preenchidos"

  Cenário: Recusar cadastro com categoria vazia
    Quando cadastro um produto com código "006", nome "Blusa", categoria "", tamanho "M", cor "Preta", preço "59.90" e quantidade "10"
    Então o resultado deve ser "Todos os campos obrigatórios devem ser preenchidos"

  Cenário: Recusar cadastro com tamanho vazio
    Quando cadastro um produto com código "006", nome "Blusa", categoria "Blusa", tamanho "", cor "Preta", preço "59.90" e quantidade "10"
    Então o resultado deve ser "Todos os campos obrigatórios devem ser preenchidos"

  Cenário: Recusar cadastro com cor vazia
    Quando cadastro um produto com código "006", nome "Blusa", categoria "Blusa", tamanho "M", cor "", preço "59.90" e quantidade "10"
    Então o resultado deve ser "Todos os campos obrigatórios devem ser preenchidos"
