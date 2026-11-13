package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Produto produto) {
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("A quantidade inicial não pode ser negativa");
        }
        if (repository.existe(produto.getCodigo())) {
            throw new IllegalArgumentException("Já existe um produto com este código");
        }
        repository.salvar(produto);
    }
}