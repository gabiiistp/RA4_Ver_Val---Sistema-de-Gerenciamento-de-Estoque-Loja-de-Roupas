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

  # RN07 - várias saídas se acumulam
  Cenário: Registrar duas saídas seguidas no mesmo produto
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 4 unidades do produto de código "001"
    E dou saída de 3 unidades do produto de código "001"
    Então o produto de código "001" deve ficar com 3 unidades em estoque

  # RN05 - a segunda saída considera o que a primeira já retirou
  Cenário: Recusar a segunda saída que passa do que sobrou
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 6 unidades do produto de código "001"
    E dou saída de 5 unidades do produto de código "001"
    Então a saída deve resultar em "Estoque insuficiente para a saída"
    E o produto de código "001" deve ficar com 4 unidades em estoque

  # RN06 + RN07 - a entrada aumenta o que pode sair
  Cenário: Registrar saída maior que o estoque inicial depois de uma entrada
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou entrada de 5 unidades no produto de código "001"
    E dou saída de 12 unidades do produto de código "001"
    Então a saída deve resultar em "Saída registrada com sucesso"
    E o produto de código "001" deve ficar com 3 unidades em estoque

  # RN05 - técnica de valor limite: a fronteira está entre o disponível (10) e o disponível + 1 (11)
  Cenário: Aceitar saída de 9 unidades com 10 em estoque
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 9 unidades do produto de código "001"
    Então a saída deve resultar em "Saída registrada com sucesso"
    E o produto de código "001" deve ficar com 1 unidades em estoque

  Cenário: Aceitar saída de 10 unidades com 10 em estoque
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 10 unidades do produto de código "001"
    Então a saída deve resultar em "Saída registrada com sucesso"
    E o produto de código "001" deve ficar com 0 unidades em estoque

  Cenário: Recusar saída de 11 unidades com 10 em estoque
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 11 unidades do produto de código "001"
    Então a saída deve resultar em "Estoque insuficiente para a saída"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  # Técnica de valor limite: a fronteira está entre 0 e 1
  Cenário: Recusar saída de -10 unidades
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de -10 unidades do produto de código "001"
    Então a saída deve resultar em "A quantidade da saída deve ser maior que zero"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  Cenário: Recusar saída de -1 unidade
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de -1 unidades do produto de código "001"
    Então a saída deve resultar em "A quantidade da saída deve ser maior que zero"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  Cenário: Recusar saída de 0 unidades
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 0 unidades do produto de código "001"
    Então a saída deve resultar em "A quantidade da saída deve ser maior que zero"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  Cenário: Aceitar saída da menor quantidade válida, 1 unidade
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 1 unidades do produto de código "001"
    Então a saída deve resultar em "Saída registrada com sucesso"
    E o produto de código "001" deve ficar com 9 unidades em estoque

  # Tabela de decisão: produto existe? x quantidade suficiente?
  Cenário: Decisão: produto existe e estoque suficiente
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 5 unidades do produto de código "001"
    Então a saída deve resultar em "Saída registrada com sucesso"
    E o produto de código "001" deve ficar com 5 unidades em estoque

  Cenário: Decisão: produto existe e estoque insuficiente
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 11 unidades do produto de código "001"
    Então a saída deve resultar em "Estoque insuficiente para a saída"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  Cenário: Decisão: produto não existe e a quantidade seria suficiente
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 5 unidades do produto de código "999"
    Então a saída deve resultar em "Produto não encontrado"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  Cenário: Decisão: produto não existe e a quantidade é grande
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou saída de 11 unidades do produto de código "999"
    Então a saída deve resultar em "Produto não encontrado"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  # RN11 - produto que foi excluído
  Cenário: Registrar saída em produto já excluído
    Dado que o produto de código "001" tem 10 unidades em estoque
    E excluo o produto de código "001"
    Quando dou saída de 5 unidades do produto de código "001"
    Então a saída deve resultar em "Produto não encontrado"