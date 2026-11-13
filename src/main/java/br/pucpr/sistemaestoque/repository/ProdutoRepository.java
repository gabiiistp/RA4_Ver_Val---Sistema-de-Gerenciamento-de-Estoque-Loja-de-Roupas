package br.pucpr.sistemaestoque.repository;

import br.pucpr.sistemaestoque.model.Produto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProdutoRepository {

    private final Map<String, Produto> produtos = new LinkedHashMap<>();

    public void salvar(Produto produto) {
        produtos.put(produto.getCodigo(), produto);
    }

    public Optional<Produto> buscarPorCodigo(String codigo) {
        return Optional.ofNullable(produtos.get(codigo));
    }

    public boolean existe(String codigo) {
        return produtos.containsKey(codigo);
    }

    public void remover(String codigo) {
        produtos.remove(codigo);
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }
}