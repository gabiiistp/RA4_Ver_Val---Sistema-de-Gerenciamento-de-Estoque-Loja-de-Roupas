package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Movimentacao;
import br.pucpr.sistemaestoque.repository.MovimentacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class MovimentacaoService {

    private final MovimentacaoRepository repository;

    public MovimentacaoService(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    public void registrarEntrada(String codigoProduto, int quantidade) {
        // a implementar (TDD: Pedro)
    }

    public List<Movimentacao> listarHistorico() {
        return new ArrayList<>(); // a implementar (TDD: Pedro)
    }
}
