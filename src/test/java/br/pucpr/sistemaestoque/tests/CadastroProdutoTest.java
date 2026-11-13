package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}