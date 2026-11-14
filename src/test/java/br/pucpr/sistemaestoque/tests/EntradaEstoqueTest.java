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

    private void cadastrarBlusaCom10Unidades() {
        produtoService.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
    }

    private int quantidadeDaBlusa() {
        return repository.buscarPorCodigo("001").get().getQuantidade();
    }

    @Test
    void deveAumentarAQuantidadeDoProdutoNaEntrada() {
        cadastrarBlusaCom10Unidades();

        estoqueService.registrarEntrada("001", 5);

        assertEquals(15, quantidadeDaBlusa());
    }

    @Test
    void naoDeveRegistrarEntradaParaProdutoQueNuncaFoiCadastrado() {
        cadastrarBlusaCom10Unidades();

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> estoqueService.registrarEntrada("999", 5));

        assertEquals("Produto não encontrado", erro.getMessage());
        assertEquals(10, quantidadeDaBlusa());
    }

    @Test
    void naoDeveRegistrarEntradaParaProdutoJaExcluido() {
        cadastrarBlusaCom10Unidades();
        produtoService.excluir("001");

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> estoqueService.registrarEntrada("001", 5));

        assertEquals("Produto não encontrado", erro.getMessage());
    }

    @ParameterizedTest(name = "entrada de {0} unidades deve ser recusada")
    @ValueSource(ints = {-10, -1, 0})
    void naoDeveRegistrarEntradaComQuantidadeMenorOuIgualAZero(int quantidadeInvalida) {
        cadastrarBlusaCom10Unidades();

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> estoqueService.registrarEntrada("001", quantidadeInvalida));

        assertEquals("A quantidade da entrada deve ser maior que zero", erro.getMessage());
        assertEquals(10, quantidadeDaBlusa());
    }

    @Test
    void deveRegistrarEntradaComAMenorQuantidadeValida() {
        cadastrarBlusaCom10Unidades();

        estoqueService.registrarEntrada("001", 1);

        assertEquals(11, quantidadeDaBlusa());
    }
}