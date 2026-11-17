
package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListagemProdutosTest {

    private ProdutoService service;

    @BeforeEach
    void setUp() {
        service = new ProdutoService(new ProdutoRepository());
    }

    private void cadastrar(String codigo, String nome) {
        service.cadastrar(
                new Produto(
                        codigo, nome, nome,
                        "M", "Preta", 59.90, 10, 2
                )
        );
    }

    private List<String> codigosListados() {
        return service.listar()
                .stream()
                .map(Produto::getCodigo)
                .toList();
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
    void naoDeveListarProdutosComEstoqueZerado() {
        service.cadastrar(new Produto(
                "001", "Blusa", "Feminina",
                "M", "Preta", 59.90, 0, 2
        ));

        cadastrar("002", "Calça");

        List<Produto> produtos = service.listar();

        assertEquals(1, produtos.size());
        assertEquals("002", produtos.get(0).getCodigo());
    }

    @Test
    void deveListarSomenteProdutosComEstoqueAcimaDoMinimo() {
        service.cadastrar(new Produto(
                "001", "Blusa", "Feminina",
                "M", "Preta", 59.90, 10, 2
        ));

        service.cadastrar(new Produto(
                "002", "Calça", "Feminina",
                "M", "Preta", 79.90, 2, 2
        ));

        List<Produto> produtos = service.listar();

        assertEquals(1, produtos.size());
        assertEquals("001", produtos.get(0).getCodigo());
    }
}
