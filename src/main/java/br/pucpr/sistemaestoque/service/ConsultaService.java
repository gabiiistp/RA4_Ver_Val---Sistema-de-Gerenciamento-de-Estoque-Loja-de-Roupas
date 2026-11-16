package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Consulta de produtos  ·  responsável: Laura
 *
 * RN09 - Consultar produto pelo nome
 * RN10 - Identificar produtos com estoque baixo (quantidade <= estoque mínimo)
 *
 * Classe nova, separada do ProdutoService, para não gerar conflito com a Isa/Gabi.
 */
public class ConsultaService {

    private final ProdutoRepository repository;

    public ConsultaService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // ============================================================
    // RN09 · BUSCAR PELO NOME
    // ============================================================
    public List<Produto> buscarPorNome(String nome) {
        // a implementar (TDD: RED)
        return new ArrayList<>();
    }

    // ============================================================
    // RN10 · ESTOQUE BAIXO (quantidade <= estoque mínimo)
    // ============================================================
    public List<Produto> listarEstoqueBaixo() {
        // a implementar (TDD: RED)
        return new ArrayList<>();
    }
}
