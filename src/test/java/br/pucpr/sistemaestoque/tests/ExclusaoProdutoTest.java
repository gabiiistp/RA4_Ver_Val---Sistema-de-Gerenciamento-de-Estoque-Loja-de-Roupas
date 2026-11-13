package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExclusaoProdutoTest {

    private ProdutoRepository repository;
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        service = new ProdutoService(repository);
    }

    @Test
    void deveExcluirProdutoExistente() {
        service.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
        assertTrue(repository.existe("001"));

        service.excluir("001");

        assertFalse(repository.existe("001"));
    }

    @Test
    void naoDeveExcluirProdutoQueNuncaFoiCadastrado() {
        service.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.excluir("999"));

        assertEquals("Produto não encontrado", erro.getMessage());
        assertTrue(repository.existe("001"));
    }

    @Test
    void naoDeveExcluirProdutoQueJaFoiExcluido() {
        service.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
        service.excluir("001");

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> service.excluir("001"));

        assertEquals("Produto não encontrado", erro.getMessage());
    }
}