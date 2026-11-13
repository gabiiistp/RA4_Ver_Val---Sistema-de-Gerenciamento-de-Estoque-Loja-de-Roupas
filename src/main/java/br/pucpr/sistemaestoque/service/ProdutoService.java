package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Produto produto) {
        if (repository.existe(produto.getCodigo())) {
            throw new IllegalArgumentException("Já existe um produto com este código");
        }
        repository.salvar(produto);
    }
}