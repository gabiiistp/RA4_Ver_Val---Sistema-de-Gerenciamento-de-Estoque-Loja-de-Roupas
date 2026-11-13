package br.pucpr.sistemaestoque.service;

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
        // a implementar (TDD: Isa)
    }

    // ============================================================
    // SAÍDA DO ESTOQUE  ·  responsável: Gabi
    // ============================================================
    public void registrarSaida(String codigo, int quantidade) {
        // a implementar (TDD: Gabi)
    }
}