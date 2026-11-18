package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Movimentacao;
import br.pucpr.sistemaestoque.model.TipoMovimentacao;
import br.pucpr.sistemaestoque.repository.MovimentacaoRepository;
import br.pucpr.sistemaestoque.service.MovimentacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistroSaidaTest {

    private static final String MSG_QUANTIDADE_INVALIDA = "A quantidade da movimentação deve ser maior que zero";
    private static final String MSG_CODIGO_OBRIGATORIO = "O código do produto deve ser informado";

    private MovimentacaoService service;

    @BeforeEach
    void setUp() {
        service = new MovimentacaoService(new MovimentacaoRepository());
    }

    private List<Movimentacao> historico() {
        return service.listarHistorico();
    }

    private List<TipoMovimentacao> tiposDe(List<Movimentacao> movimentacoes) {
        return movimentacoes.stream().map(Movimentacao::getTipo).collect(Collectors.toList());
    }

    private void assertSaidaRecusada(String codigo, int quantidade, String mensagemEsperada) {
        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.registrarSaida(codigo, quantidade));

        assertEquals(mensagemEsperada, erro.getMessage());
        assertTrue(historico().isEmpty());
    }

    @Test
    void deveRegistrarSaidaNoHistorico() {
        service.registrarSaida("001", 3);

        assertEquals(1, historico().size());
        Movimentacao movimentacao = historico().get(0);
        assertEquals("001", movimentacao.getCodigoProduto());
        assertEquals(TipoMovimentacao.SAIDA, movimentacao.getTipo());
        assertEquals(3, movimentacao.getQuantidade());
        assertEquals(LocalDate.now(), movimentacao.getData().toLocalDate());
    }

    @Test
    void deveManterEntradasESaidasNaOrdemEmQueAconteceram() {
        service.registrarEntrada("001", 10);
        service.registrarSaida("001", 3);

        assertEquals(List.of(TipoMovimentacao.ENTRADA, TipoMovimentacao.SAIDA), tiposDe(historico()));
        assertEquals(1, historico().get(0).getId());
        assertEquals(2, historico().get(1).getId());
    }

    @Test
    void deveConsultarSomenteOHistoricoDoProdutoInformado() {
        service.registrarEntrada("001", 10);
        service.registrarEntrada("002", 4);
        service.registrarSaida("001", 3);

        List<Movimentacao> historicoDo001 = service.listarHistoricoDoProduto("001");
        assertEquals(List.of(TipoMovimentacao.ENTRADA, TipoMovimentacao.SAIDA), tiposDe(historicoDo001));
    }

    @ParameterizedTest(name = "saída de {0} unidades não deve ir para o histórico")
    @ValueSource(ints = {-10, -1, 0})
    void naoDeveRegistrarSaidaComQuantidadeMenorOuIgualAZero(int quantidadeInvalida) {
        assertSaidaRecusada("001", quantidadeInvalida, MSG_QUANTIDADE_INVALIDA);
    }

    @Test
    void deveRegistrarSaidaComAMenorQuantidadeValida() {
        service.registrarSaida("001", 1);

        assertEquals(1, historico().size());
        assertEquals(1, historico().get(0).getQuantidade());
    }

    @ParameterizedTest(name = "código \"{0}\" deve ser recusado")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void naoDeveRegistrarSaidaSemCodigoDoProduto(String codigoInvalido) {
        assertSaidaRecusada(codigoInvalido, 3, MSG_CODIGO_OBRIGATORIO);
    }
}
