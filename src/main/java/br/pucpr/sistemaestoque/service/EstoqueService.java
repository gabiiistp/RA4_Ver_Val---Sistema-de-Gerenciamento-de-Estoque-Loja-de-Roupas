package br.pucpr.sistemaestoque.service;
import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

public class EstoqueService {

    private final ProdutoRepository repository;


    public EstoqueService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // ============================================================
    // ENTRADA NO ESTOQUE  ·  responsável: Isa
    // ============================================================
    public void registrarEntrada(String codigo, int quantidade) {
        Produto produto = repository.buscarPorCodigo(codigo).get();
        produto.setQuantidade(produto.getQuantidade() + quantidade);
    }

    // ============================================================
    // SAÍDA DO ESTOQUE  ·  responsável: Gabi
    // ============================================================
    public void registrarSaida(String codigo, int quantidade) {
        // a implementar (TDD: Gabi)
    }
}