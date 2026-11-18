# language: pt
Funcionalidade: Histórico de movimentações do estoque (log de entrada e saída)
  Como responsável pelo estoque da loja
  Quero que toda entrada e saída de mercadoria fique registrada
  Para poder conferir o histórico de movimentações de cada produto

  # RN08 - log de entrada (cenário positivo)
  Cenário: Registrar uma entrada no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma entrada de 5 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "Movimentação registrada com sucesso"
    E o total de registros no histórico deve ser 1
    E o registro 1 do histórico deve ser do tipo "ENTRADA", com 5 unidades do produto de código "001"

  # RN08 - log de saída (cenário positivo)
  Cenário: Registrar uma saída no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma saída de 3 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "Movimentação registrada com sucesso"
    E o total de registros no histórico deve ser 1
    E o registro 1 do histórico deve ser do tipo "SAIDA", com 3 unidades do produto de código "001"

  # Transição de estado: o histórico cresce na ordem das movimentações
  Cenário: Entradas e saídas ficam no histórico na ordem em que aconteceram
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma entrada de 10 unidades do produto de código "001"
    E registro no histórico uma saída de 3 unidades do produto de código "001"
    E registro no histórico uma entrada de 4 unidades do produto de código "002"
    Então o total de registros no histórico deve ser 3
    E o registro 1 do histórico deve ser do tipo "ENTRADA", com 10 unidades do produto de código "001"
    E o registro 2 do histórico deve ser do tipo "SAIDA", com 3 unidades do produto de código "001"
    E o registro 3 do histórico deve ser do tipo "ENTRADA", com 4 unidades do produto de código "002"
    E o total de registros do produto de código "001" no histórico deve ser 2

  # Valor limite (log de entrada): a fronteira está entre 0 e 1
  Cenário: Recusar entrada de -1 unidade no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma entrada de -1 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "A quantidade da movimentação deve ser maior que zero"
    E o total de registros no histórico deve ser 0

  Cenário: Recusar entrada de 0 unidades no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma entrada de 0 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "A quantidade da movimentação deve ser maior que zero"
    E o total de registros no histórico deve ser 0

  Cenário: Aceitar entrada de 1 unidade no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma entrada de 1 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "Movimentação registrada com sucesso"
    E o total de registros no histórico deve ser 1

  # Valor limite (log de saída): a fronteira está entre 0 e 1
  Cenário: Recusar saída de -1 unidade no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma saída de -1 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "A quantidade da movimentação deve ser maior que zero"
    E o total de registros no histórico deve ser 0

  Cenário: Recusar saída de 0 unidades no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma saída de 0 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "A quantidade da movimentação deve ser maior que zero"
    E o total de registros no histórico deve ser 0

  Cenário: Aceitar saída de 1 unidade no histórico
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma saída de 1 unidades do produto de código "001"
    Então o registro no histórico deve resultar em "Movimentação registrada com sucesso"
    E o total de registros no histórico deve ser 1

  # código do produto preenchido x vazio
  Cenário: Não registrar entrada sem o código do produto
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma entrada de 5 unidades do produto de código ""
    Então o registro no histórico deve resultar em "O código do produto deve ser informado"
    E o total de registros no histórico deve ser 0

  Cenário: Não registrar saída sem o código do produto
    Dado que o histórico de movimentações está vazio
    Quando registro no histórico uma saída de 5 unidades do produto de código ""
    Então o registro no histórico deve resultar em "O código do produto deve ser informado"
    E o total de registros no histórico deve ser 0