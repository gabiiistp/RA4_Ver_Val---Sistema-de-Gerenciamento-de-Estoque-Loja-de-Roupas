package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ListagemProdutosTest {

    private ProdutoService service;

    @BeforeEach
    void setUp() {
        service = new ProdutoService(new ProdutoRepository());
    }

    private void cadastrar(String codigo, String nome) {
        service.cadastrar(
                new Produto(
                        codigo, nome, "Feminina",
                        "M", "Preta", 59.90, 10, 2
                )
        );
    }

    @Test
    void deveListarTodosOsProdutosCadastrados() {
        cadastrar("001", "Blusa");
        cadastrar("002", "Calça");
        cadastrar("003", "Saia");

        List<Produto> produtos = service.listar();

        assertEquals(3, produtos.size());
        assertEquals("001", produtos.get(0).getCodigo());
        assertEquals("002", produtos.get(1).getCodigo());
        assertEquals("003", produtos.get(2).getCodigo());
    }

    @Test
    void naoDeveListarProdutosExcluidos() {
        cadastrar("001", "Blusa");
        cadastrar("002", "Calça");

        service.excluir("001");

        List<Produto> produtos = service.listar();

        assertEquals(1, produtos.size());
        assertFalse(codigosListados().contains("001"));
        assertTrue(codigosListados().contains("002"));
    }

    @Test
    void deveRetornarListaVaziaQuandoTodosForemExcluidos() {
        cadastrar("001", "Blusa");

        service.excluir("001");

        List<Produto> produtos = service.listar();

        assertNotNull(produtos);
        assertTrue(produtos.isEmpty());
    }

    private List<String> codigosListados() {
        return service.listar()
                .stream()
                .map(Produto::getCodigo)
                .toList();
    }
}