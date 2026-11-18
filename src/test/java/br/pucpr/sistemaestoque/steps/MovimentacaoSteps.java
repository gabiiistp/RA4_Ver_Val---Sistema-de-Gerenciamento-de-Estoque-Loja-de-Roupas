package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.model.Movimentacao;
import br.pucpr.sistemaestoque.model.TipoMovimentacao;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovimentacaoSteps {

    private static final String MSG_SUCESSO = "Movimentação registrada com sucesso";

    private final ContextoSistema contexto;

    public MovimentacaoSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    private List<Movimentacao> historico() {
        return contexto.movimentacaoService.listarHistorico();
    }

    @Dado("que o histórico de movimentações está vazio")
    public void queOHistoricoDeMovimentacoesEstaVazio() {
        assertTrue(historico().isEmpty());
    }

    @Quando("registro no histórico uma entrada de {int} unidades do produto de código {string}")
    public void registroNoHistoricoUmaEntrada(int quantidade, String codigo) {
        contexto.tentar(() -> contexto.movimentacaoService.registrarEntrada(codigo, quantidade), MSG_SUCESSO);
    }

    @Quando("registro no histórico uma saída de {int} unidades do produto de código {string}")
    public void registroNoHistoricoUmaSaida(int quantidade, String codigo) {
        contexto.tentar(() -> contexto.movimentacaoService.registrarSaida(codigo, quantidade), MSG_SUCESSO);
    }

    @Então("o registro no histórico deve resultar em {string}")
    public void oRegistroNoHistoricoDeveResultarEm(String esperado) {
        assertEquals(esperado, contexto.resultado);
    }

    @E("o total de registros no histórico deve ser {int}")
    public void oTotalDeRegistrosNoHistoricoDeveSer(int esperado) {
        assertEquals(esperado, historico().size());
    }

    @E("o registro {int} do histórico deve ser do tipo {string}, com {int} unidades do produto de código {string}")
    public void oRegistroDoHistoricoDeveSer(int posicao, String tipo, int quantidade, String codigo) {
        Movimentacao movimentacao = historico().get(posicao - 1);
        assertEquals(TipoMovimentacao.valueOf(tipo), movimentacao.getTipo());
        assertEquals(quantidade, movimentacao.getQuantidade());
        assertEquals(codigo, movimentacao.getCodigoProduto());
    }

    @E("o total de registros do produto de código {string} no histórico deve ser {int}")
    public void oTotalDeRegistrosDoProdutoDeveSer(String codigo, int esperado) {
        assertEquals(esperado, contexto.movimentacaoService.listarHistoricoDoProduto(codigo).size());
    }
}
