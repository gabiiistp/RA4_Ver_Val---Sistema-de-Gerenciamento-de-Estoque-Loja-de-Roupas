package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.model.Produto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExclusaoSteps {

    private static final String MSG_SUCESSO = "Produto excluído com sucesso";

    private final ContextoSistema contexto;

    public ExclusaoSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Dado("que o estoque possui o produto de código {string}")
    public void queOEstoquePossuiOProdutoDeCodigo(String codigo) {
        contexto.produtoService.cadastrar(
                new Produto(codigo, "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
    }

    @Quando("excluo o produto de código {string}")
    public void excluoOProdutoDeCodigo(String codigo) {
        contexto.tentar(() -> contexto.produtoService.excluir(codigo), MSG_SUCESSO);
    }

    @Então("a exclusão deve resultar em {string}")
    public void aExclusaoDeveResultarEm(String esperado) {
        assertEquals(esperado, contexto.resultado);
    }

    @E("o produto de código {string} não deve mais existir no estoque")
    public void oProdutoNaoDeveMaisExistir(String codigo) {
        assertFalse(contexto.repository.existe(codigo));
    }

    @E("o produto de código {string} ainda deve existir no estoque")
    public void oProdutoAindaDeveExistir(String codigo) {
        assertTrue(contexto.repository.existe(codigo));
    }
}