package br.pucpr.sistemaestoque.repository;

import br.pucpr.sistemaestoque.model.Movimentacao;

import java.util.ArrayList;
import java.util.List;

public class MovimentacaoRepository {

    private final List<Movimentacao> movimentacoes = new ArrayList<>();

    public void salvar(Movimentacao movimentacao) {
        movimentacoes.add(movimentacao);
    }

    public List<Movimentacao> listarTodas() {
        return new ArrayList<>(movimentacoes);
    }
}
