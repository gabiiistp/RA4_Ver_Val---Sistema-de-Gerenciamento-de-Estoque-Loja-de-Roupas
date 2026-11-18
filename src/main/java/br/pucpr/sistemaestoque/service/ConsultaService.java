package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultaService {

    private static final String MSG_NOME_BUSCA_VAZIO = "O nome para busca não pode estar vazio";

    private final ProdutoRepository repository;

    public ConsultaService(ProdutoRepository repository) {
        this.repository = repository;
    }


    // RN09 · BUSCAR PELO NOME
    // Busca parcial e sem diferenciar maiúsculas de minúsculas.

    public List<Produto> buscarPorNome(String nome) {
        validarNomeBusca(nome);
        String procurado = nome.trim().toLowerCase();

        return repository.listarTodos().stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(procurado))
                .collect(Collectors.toList());
    }

    private void validarNomeBusca(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(MSG_NOME_BUSCA_VAZIO);
        }
    }


    // RN10 · ESTOQUE BAIXO (quantidade <= estoque mínimo)

    public List<Produto> listarEstoqueBaixo() {
        return repository.listarTodos().stream()
                .filter(this::estaComEstoqueBaixo)
                .collect(Collectors.toList());
    }

    private boolean estaComEstoqueBaixo(Produto produto) {
        return produto.getQuantidade() <= produto.getEstoqueMinimo();
    }
}
