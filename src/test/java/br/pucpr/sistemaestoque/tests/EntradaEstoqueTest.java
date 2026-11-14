package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.EstoqueService;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EntradaEstoqueTest {

    private ProdutoRepository repository;
    private ProdutoService produtoService;
    private EstoqueService estoqueService;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        produtoService = new ProdutoService(repository);
        estoqueService = new EstoqueService(repository);
    }

    @Test
    void deveAumentarAQuantidadeDoProdutoNaEntrada() {
        produtoService.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));

        estoqueService.registrarEntrada("001", 5);

        assertEquals(15, repository.buscarPorCodigo("001").get().getQuantidade());
    }

    @Test
    void naoDeveRegistrarEntradaParaProdutoQueNuncaFoiCadastrado() {
        produtoService.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> estoqueService.registrarEntrada("999", 5));

        assertEquals("Produto não encontrado", erro.getMessage());
        assertEquals(10, repository.buscarPorCodigo("001").get().getQuantidade());
    }

    @Test
    void naoDeveRegistrarEntradaParaProdutoJaExcluido() {
        produtoService.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
        produtoService.excluir("001");

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> estoqueService.registrarEntrada("001", 5));

        assertEquals("Produto não encontrado", erro.getMessage());
    }

    @ParameterizedTest(name = "entrada de {0} unidades deve ser recusada")
    @ValueSource(ints = {-10, -1, 0})
    void naoDeveRegistrarEntradaComQuantidadeMenorOuIgualAZero(int quantidadeInvalida) {
        produtoService.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> estoqueService.registrarEntrada("001", quantidadeInvalida));

        assertEquals("A quantidade da entrada deve ser maior que zero", erro.getMessage());
        assertEquals(10, repository.buscarPorCodigo("001").get().getQuantidade());
    }

    @Test
    void deveRegistrarEntradaComAMenorQuantidadeValida() {
        produtoService.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));

        estoqueService.registrarEntrada("001", 1);

        assertEquals(11, repository.buscarPorCodigo("001").get().getQuantidade());
    }
}