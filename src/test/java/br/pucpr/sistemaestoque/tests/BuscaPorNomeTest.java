package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ConsultaService;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RN09 - Consultar produto pelo nome  ·  Laura
 */
class BuscaPorNomeTest {

    private ProdutoRepository repository;
    private ConsultaService consultaService;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        ProdutoService produtoService = new ProdutoService(repository);
        consultaService = new ConsultaService(repository);

        produtoService.cadastrar(new Produto("001", "Blusa Floral", "Blusa", "M", "Branca", 59.90, 10, 2));
        produtoService.cadastrar(new Produto("002", "Blusa Lisa", "Blusa", "G", "Preta", 49.90, 5, 2));
        produtoService.cadastrar(new Produto("003", "Calça Jeans", "Calça", "40", "Azul", 129.90, 8, 3));
    }

    @Test
    void deveEncontrarTodosOsProdutosQueContemONomeBuscado() {
        List<Produto> encontrados = consultaService.buscarPorNome("Blusa");

        assertEquals(2, encontrados.size());
    }

    @Test
    void deveEncontrarProdutoPorParteDoNome() {
        List<Produto> encontrados = consultaService.buscarPorNome("Floral");

        assertEquals(1, encontrados.size());
        assertEquals("001", encontrados.get(0).getCodigo());
    }

    @Test
    void deveIgnorarMaiusculasEMinusculasNaBusca() {
        List<Produto> encontrados = consultaService.buscarPorNome("blUSa");

        assertEquals(2, encontrados.size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumProdutoCorresponde() {
        List<Produto> encontrados = consultaService.buscarPorNome("Vestido");

        assertTrue(encontrados.isEmpty());
    }

    @ParameterizedTest(name = "busca com nome [{0}] deve ser recusada")
    @NullSource
    @ValueSource(strings = {"", "   "})
    void naoDeveBuscarComNomeVazio(String nomeInvalido) {
        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> consultaService.buscarPorNome(nomeInvalido));

        assertEquals("O nome para busca não pode estar vazio", erro.getMessage());
    }
}
