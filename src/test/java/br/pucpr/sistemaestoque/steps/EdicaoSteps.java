package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.model.Produto;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdicaoSteps {

    private static final String MSG_SUCESSO = "Produto editado com sucesso";

    private final ContextoSistema contexto;

    public EdicaoSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Quando("edito o produto de código {string} com nome {string}, categoria {string}, tamanho {string}, cor {string}, preço {string} e estoque mínimo {int}")
    public void editoOProdutoDeCodigo(String codigo, String nome, String categoria, String tamanho,
                                      String cor, String preco, int estoqueMinimo) {
        // a quantidade enviada (0) não importa: editar nunca altera a quantidade
        Produto dados = new Produto(codigo, nome, categoria, tamanho, cor, Double.parseDouble(preco), 0, estoqueMinimo);
        contexto.tentar(() -> contexto.produtoService.editar(dados), MSG_SUCESSO);
    }

    @Então("a edição deve resultar em {string}")
    public void aEdicaoDeveResultarEm(String esperado) {
        assertEquals(esperado, contexto.resultado);
    }

    @E("o produto de código {string} deve ter nome {string} e preço {string}")
    public void oProdutoDeveTerNomeEPreco(String codigo, String nome, String preco) {
        Produto produto = contexto.repository.buscarPorCodigo(codigo).get();
        assertEquals(nome, produto.getNome());
        assertEquals(Double.parseDouble(preco), produto.getPreco());
    }

}
