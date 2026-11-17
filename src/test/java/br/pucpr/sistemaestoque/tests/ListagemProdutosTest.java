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

        List<String> codigos = codigosListados();

        assertEquals(List.of("001", "002", "003"), codigos);
    }

    @Test
    void naoDeveListarProdutosExcluidos() {
        cadastrar("001", "Blusa");
        cadastrar("002", "Calça");

        service.excluir("001");

        List<String> codigos = codigosListados();

        assertEquals(List.of("002"), codigos);
    }

    @Test
    void deveRetornarListaVaziaQuandoTodosForemExcluidos() {
        cadastrar("001", "Blusa");

        service.excluir("001");

        List<String> codigos = codigosListados();

        assertEquals(List.of(), codigos);
    }
}