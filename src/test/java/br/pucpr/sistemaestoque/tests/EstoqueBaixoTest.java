package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ConsultaService;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EstoqueBaixoTest {

    private ProdutoRepository repository;
    private ProdutoService produtoService;
    private ConsultaService consultaService;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        produtoService = new ProdutoService(repository);
        consultaService = new ConsultaService(repository);
    }

    private void cadastrar(String codigo, int quantidade, int estoqueMinimo) {
        produtoService.cadastrar(
                new Produto(codigo, "Blusa " + codigo, "Blusa", "M", "Preta", 59.90, quantidade, estoqueMinimo));
    }

    @Test
    void deveListarProdutoComQuantidadeAbaixoDoMinimo() {
        cadastrar("001", 1, 5);

        List<Produto> baixo = consultaService.listarEstoqueBaixo();

        assertEquals(1, baixo.size());
        assertEquals("001", baixo.get(0).getCodigo());
    }

    @Test
    void deveListarProdutoComQuantidadeIgualAoMinimo() {
        cadastrar("001", 5, 5);

        List<Produto> baixo = consultaService.listarEstoqueBaixo();

        assertEquals(1, baixo.size());
        assertEquals("001", baixo.get(0).getCodigo());
    }

    @Test
    void naoDeveListarProdutoComQuantidadeAcimaDoMinimo() {
        cadastrar("001", 6, 5);

        List<Produto> baixo = consultaService.listarEstoqueBaixo();

        assertTrue(baixo.isEmpty());
    }

    @Test
    void deveListarApenasOsProdutosComEstoqueBaixo() {
        cadastrar("001", 1, 5);  // baixo
        cadastrar("002", 20, 5); // ok
        cadastrar("003", 5, 5);  // baixo (igual ao mínimo)

        List<Produto> baixo = consultaService.listarEstoqueBaixo();

        assertEquals(2, baixo.size());
    }

    @Test
    void deveRetornarListaVaziaQuandoTodosTemEstoqueSuficiente() {
        cadastrar("001", 10, 2);
        cadastrar("002", 8, 3);

        List<Produto> baixo = consultaService.listarEstoqueBaixo();

        assertTrue(baixo.isEmpty());
    }

    // Técnica de valor limite: a fronteira do estoque baixo está entre o mínimo e mínimo+1
    @ParameterizedTest(name = "quantidade {0} com mínimo 5 → estoque baixo? {1}")
    @CsvSource({
            "4, true",
            "5, true",
            "6, false"
    })
    void deveAplicarOLimiteDoEstoqueMinimoCorretamente(int quantidade, boolean esperaBaixo) {
        cadastrar("001", quantidade, 5);

        boolean estaBaixo = !consultaService.listarEstoqueBaixo().isEmpty();

        assertEquals(esperaBaixo, estaBaixo);
    }
}
