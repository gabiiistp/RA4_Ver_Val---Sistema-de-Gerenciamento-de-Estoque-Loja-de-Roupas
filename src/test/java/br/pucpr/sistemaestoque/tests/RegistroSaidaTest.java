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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistroSaidaTest {

    private MovimentacaoService service;

    @BeforeEach
    void setUp() {
        service = new MovimentacaoService(new MovimentacaoRepository());
    }

    @Test
    void deveRegistrarSaidaNoHistorico() {
        service.registrarSaida("001", 3);

        List<Movimentacao> historico = service.listarHistorico();
        assertEquals(1, historico.size());

        Movimentacao movimentacao = historico.get(0);
        assertEquals("001", movimentacao.getCodigoProduto());
        assertEquals(TipoMovimentacao.SAIDA, movimentacao.getTipo());
        assertEquals(3, movimentacao.getQuantidade());
        assertEquals(LocalDate.now(), movimentacao.getData().toLocalDate());
    }

    @Test
    void deveManterEntradasESaidasNaOrdemEmQueAconteceram() {
        service.registrarEntrada("001", 10);
        service.registrarSaida("001", 3);

        List<Movimentacao> historico = service.listarHistorico();
        assertEquals(2, historico.size());
        assertEquals(TipoMovimentacao.ENTRADA, historico.get(0).getTipo());
        assertEquals(TipoMovimentacao.SAIDA, historico.get(1).getTipo());
        assertEquals(1, historico.get(0).getId());
        assertEquals(2, historico.get(1).getId());
    }

    @Test
    void deveConsultarSomenteOHistoricoDoProdutoInformado() {
        service.registrarEntrada("001", 10);
        service.registrarEntrada("002", 4);
        service.registrarSaida("001", 3);

        List<Movimentacao> historicoDo001 = service.listarHistoricoDoProduto("001");
        assertEquals(2, historicoDo001.size());
        assertEquals(TipoMovimentacao.ENTRADA, historicoDo001.get(0).getTipo());
        assertEquals(TipoMovimentacao.SAIDA, historicoDo001.get(1).getTipo());
    }

    @ParameterizedTest(name = "saída de {0} unidades não deve ir para o histórico")
    @ValueSource(ints = {-10, -1, 0})
    void naoDeveRegistrarSaidaComQuantidadeMenorOuIgualAZero(int quantidadeInvalida) {
        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.registrarSaida("001", quantidadeInvalida));

        assertEquals("A quantidade da movimentação deve ser maior que zero", erro.getMessage());
        assertTrue(service.listarHistorico().isEmpty());
    }

    @Test
    void deveRegistrarSaidaComAMenorQuantidadeValida() {
        service.registrarSaida("001", 1);

        List<Movimentacao> historico = service.listarHistorico();
        assertEquals(1, historico.size());
        assertEquals(1, historico.get(0).getQuantidade());
    }

    @ParameterizedTest(name = "código \"{0}\" deve ser recusado")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void naoDeveRegistrarSaidaSemCodigoDoProduto(String codigoInvalido) {
        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.registrarSaida(codigoInvalido, 3));

        assertEquals("O código do produto deve ser informado", erro.getMessage());
        assertTrue(service.listarHistorico().isEmpty());
    }
}
