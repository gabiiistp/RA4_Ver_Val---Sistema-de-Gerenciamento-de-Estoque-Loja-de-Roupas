package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.model.Produto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Steps da feature de listagem  ·  Gabi
 *
 * O Dado "que o estoque possui o produto de código ..." e o Quando "excluo o produto ..."
 * vêm do ExclusaoSteps (mesmo estoque).
 */
public class ListagemSteps {

    private final ContextoSistema contexto;

    // resultado da última listagem feita no cenário
    private List<Produto> listagem = new ArrayList<>();

    public ListagemSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Dado("que o estoque possui {int} produtos cadastrados")
    public void queOEstoquePossuiProdutosCadastrados(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            String codigo = String.format("%03d", i);
            contexto.produtoService.cadastrar(
                    new Produto(codigo, "Blusa " + codigo, "Blusa", "M", "Preta", 59.90, 10, 2));
        }
    }

    @Quando("listo todos os produtos do estoque")
    public void listoTodosOsProdutosDoEstoque() {
        listagem = contexto.produtoService.listar();
    }

    @Então("a listagem deve conter {int} produto(s)")
    public void aListagemDeveConterProdutos(int esperado) {
        assertEquals(esperado, listagem.size());
    }

    @E("a listagem deve conter o produto de código {string}")
    public void aListagemDeveConterOProduto(String codigo) {
        assertTrue(listagem.stream().anyMatch(produto -> produto.getCodigo().equals(codigo)));
    }

    @E("a listagem não deve conter o produto de código {string}")
    public void aListagemNaoDeveConterOProduto(String codigo) {
        assertFalse(listagem.stream().anyMatch(produto -> produto.getCodigo().equals(codigo)));
    }
}
