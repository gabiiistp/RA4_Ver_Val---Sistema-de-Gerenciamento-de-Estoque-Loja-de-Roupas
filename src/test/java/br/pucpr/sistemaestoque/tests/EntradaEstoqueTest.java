package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.EstoqueService;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}