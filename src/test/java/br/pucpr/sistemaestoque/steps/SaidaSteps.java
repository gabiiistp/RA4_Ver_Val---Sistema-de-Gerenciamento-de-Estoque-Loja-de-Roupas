package br.pucpr.sistemaestoque.steps;

import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaidaSteps {
    private static final String MSG_SUCESSO = "Saída registrada com sucesso";

    private final ContextoSistema contexto;

    public SaidaSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Quando("dou saída de {int} unidades do produto de código {string}")
    public void douSaidaDeUnidadesDoProduto(int quantidade, String codigo) {
        contexto.tentar(
                () -> contexto.estoqueService.registrarSaida(codigo, quantidade),
                MSG_SUCESSO
        );
    }

    @Então("a saída deve resultar em {string}")
    public void aSaidaDeveResultarEm(String esperado) {
        assertEquals(esperado, contexto.resultado);
    }
}
