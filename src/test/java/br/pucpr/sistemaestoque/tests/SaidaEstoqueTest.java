package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.EstoqueService;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class SaidaEstoqueTest {

    private static final String MSG_NAO_ENCONTRADO = "Produto não encontrado";
    private static final String MSG_ESTOQUE_INSUFICIENTE = "Estoque insuficiente para a saída";
    private static final String MSG_QUANTIDADE_INVALIDA = "A quantidade da saída deve ser maior que zero";

    private ProdutoRepository repository;
    private ProdutoService produtoService;
    private EstoqueService estoqueService;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        produtoService = new ProdutoService(repository);
        estoqueService = new EstoqueService(repository);
    }

    private void cadastrarBlusaCom10Unidades() {
        produtoService.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
    }

    private int quantidadeDaBlusa() {
        return repository.buscarPorCodigo("001").get().getQuantidade();
    }

    private IllegalArgumentException saidaRecusada(String codigo, int quantidade) {
        return assertThrows(IllegalArgumentException.class,
                () -> estoqueService.registrarSaida(codigo, quantidade));
    }
    @Test
    void naoDevePermitirSaidaDe11QuandoExistem10Unidades() {
        cadastrarBlusaCom10Unidades();

        IllegalArgumentException erro = saidaRecusada("001", 11);

        assertEquals(MSG_ESTOQUE_INSUFICIENTE, erro.getMessage());
        assertEquals(10, quantidadeDaBlusa());
    }

    @Test
    void naoDevePermitirSaidaDe100QuandoExistem10Unidades() {
        cadastrarBlusaCom10Unidades();

        IllegalArgumentException erro = saidaRecusada("001", 100);

        assertEquals(MSG_ESTOQUE_INSUFICIENTE, erro.getMessage());
        assertEquals(10, quantidadeDaBlusa());
    }

    @Test
    void naoDevePermitirSegundaSaidaMaiorQueOEstoqueRestante() {
        cadastrarBlusaCom10Unidades();

        estoqueService.registrarSaida("001", 6);

        IllegalArgumentException erro = saidaRecusada("001", 5);

        assertEquals(MSG_ESTOQUE_INSUFICIENTE, erro.getMessage());
        assertEquals(4, quantidadeDaBlusa());
    }
}
