package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.model.Produto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntradaSteps {

    private static final String MSG_SUCESSO = "Entrada registrada com sucesso";

    private final ContextoSistema contexto;

    public EntradaSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Dado("que o produto de código {string} tem {int} unidades em estoque")
    public void queOProdutoTemUnidadesEmEstoque(String codigo, int unidades) {
        contexto.produtoService.cadastrar(
                new Produto(codigo, "Blusa", "Blusa", "M", "Preta", 59.90, unidades, 2));
    }

    @Quando("dou entrada de {int} unidades no produto de código {string}")
    public void douEntradaDeUnidadesNoProduto(int quantidade, String codigo) {
        contexto.tentar(() -> contexto.estoqueService.registrarEntrada(codigo, quantidade), MSG_SUCESSO);
    }

    @Então("a entrada deve resultar em {string}")
    public void aEntradaDeveResultarEm(String esperado) {
        assertEquals(esperado, contexto.resultado);
    }

    @E("o produto de código {string} deve ficar com {int} unidades em estoque")
    public void oProdutoDeveFicarComUnidadesEmEstoque(String codigo, int esperado) {
        assertEquals(esperado, contexto.repository.buscarPorCodigo(codigo).get().getQuantidade());
    }
}