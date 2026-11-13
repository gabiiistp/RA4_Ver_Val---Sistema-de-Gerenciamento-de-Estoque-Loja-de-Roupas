package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CadastroProdutoTest {

    private ProdutoRepository repository;
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        service = new ProdutoService(repository);
    }

    @Test
    void deveCadastrarProdutoValido() {
        Produto produto = new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2);

        service.cadastrar(produto);

        assertTrue(repository.existe("001"));
        assertEquals("Blusa", repository.buscarPorCodigo("001").get().getNome());
    }

    @Test
    void naoDeveCadastrarProdutoComCodigoDuplicado() {
        service.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
        Produto duplicado = new Produto("001", "Calça", "Calça", "G", "Azul", 99.90, 5, 1);

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(duplicado));

        assertEquals("Já existe um produto com este código", erro.getMessage());
        assertEquals("Blusa", repository.buscarPorCodigo("001").get().getNome());
    }

    @ParameterizedTest(name = "preço {0} deve ser recusado")
    @ValueSource(doubles = {-1.0, -0.01, 0.0})
    void naoDeveCadastrarProdutoComPrecoMenorOuIgualAZero(double precoInvalido) {
        Produto produto = new Produto("002", "Saia", "Saia", "P", "Verde", precoInvalido, 5, 1);

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(produto));

        assertEquals("O preço deve ser maior que zero", erro.getMessage());
        assertFalse(repository.existe("002"));
    }

    @Test
    void deveCadastrarProdutoComMenorPrecoValido() {
        Produto produto = new Produto("003", "Meia", "Meia", "M", "Branca", 0.01, 5, 1);

        service.cadastrar(produto);

        assertTrue(repository.existe("003"));
    }

    @ParameterizedTest(name = "quantidade {0} deve ser recusada")
    @ValueSource(ints = {-10, -1})
    void naoDeveCadastrarProdutoComQuantidadeNegativa(int quantidadeInvalida) {
        Produto produto = new Produto("004", "Short", "Short", "M", "Azul", 49.90, quantidadeInvalida, 1);

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(produto));

        assertEquals("A quantidade inicial não pode ser negativa", erro.getMessage());
        assertFalse(repository.existe("004"));
    }

    @ParameterizedTest(name = "quantidade {0} deve ser aceita")
    @ValueSource(ints = {0, 1})
    void deveCadastrarProdutoComQuantidadeZeroOuPositiva(int quantidadeValida) {
        Produto produto = new Produto("005", "Boné", "Acessório", "M", "Preto", 39.90, quantidadeValida, 1);

        service.cadastrar(produto);

        assertTrue(repository.existe("005"));
        assertEquals(quantidadeValida, repository.buscarPorCodigo("005").get().getQuantidade());
    }
}