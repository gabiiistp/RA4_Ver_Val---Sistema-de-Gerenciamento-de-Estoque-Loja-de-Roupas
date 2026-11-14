# language: pt
Funcionalidade: Entrada de produtos no estoque
  Como responsável pelo estoque da loja
  Quero registrar a chegada de mercadoria
  Para manter a quantidade de cada produto atualizada

  # RN06 - cenário positivo
  Cenário: Registrar entrada em produto existente
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou entrada de 5 unidades no produto de código "001"
    Então a entrada deve resultar em "Entrada registrada com sucesso"
    E o produto de código "001" deve ficar com 15 unidades em estoque

  # RN06 - várias entradas se acumulam
  Cenário: Registrar duas entradas seguidas no mesmo produto
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou entrada de 5 unidades no produto de código "001"
    E dou entrada de 3 unidades no produto de código "001"
    Então o produto de código "001" deve ficar com 18 unidades em estoque

  # Técnica de valor limite: a fronteira está entre 0 e 1
  Esquema do Cenário: Validar a quantidade da entrada
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou entrada de <quantidade> unidades no produto de código "001"
    Então a entrada deve resultar em "<resultado>"
    E o produto de código "001" deve ficar com <estoque_final> unidades em estoque

    Exemplos:
      | quantidade | resultado                                      | estoque_final |
      | -10        | A quantidade da entrada deve ser maior que zero | 10            |
      | -1         | A quantidade da entrada deve ser maior que zero | 10            |
      | 0          | A quantidade da entrada deve ser maior que zero | 10            |
      | 1          | Entrada registrada com sucesso                  | 11            |

  # RN11 - produto que nunca foi cadastrado
  Cenário: Registrar entrada em produto que nunca foi cadastrado
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou entrada de 5 unidades no produto de código "999"
    Então a entrada deve resultar em "Produto não encontrado"
    E o produto de código "001" deve ficar com 10 unidades em estoque

  # RN11 - produto que foi excluído
  Cenário: Registrar entrada em produto já excluído
    Dado que o produto de código "001" tem 10 unidades em estoque
    E excluo o produto de código "001"
    Quando dou entrada de 5 unidades no produto de código "001"
    Então a entrada deve resultar em "Produto não encontrado"