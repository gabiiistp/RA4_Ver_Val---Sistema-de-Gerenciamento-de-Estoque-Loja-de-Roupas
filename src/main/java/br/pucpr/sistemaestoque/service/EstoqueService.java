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
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade da entrada deve ser maior que zero");
        }
        Produto produto = repository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produto.setQuantidade(produto.getQuantidade() + quantidade);
    }

    // ============================================================
    // SAÍDA DO ESTOQUE  ·  responsável: Gabi
    // ============================================================
    public void registrarSaida(String codigo, int quantidade) {
        // a implementar (TDD: Gabi)
    }
}