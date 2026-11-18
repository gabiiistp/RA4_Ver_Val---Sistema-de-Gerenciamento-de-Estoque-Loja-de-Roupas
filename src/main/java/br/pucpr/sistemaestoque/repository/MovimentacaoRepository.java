package br.pucpr.sistemaestoque.repository;

import br.pucpr.sistemaestoque.model.Movimentacao;

import java.util.ArrayList;
import java.util.List;

/** Histórico de movimentações guardado em memória (na ordem em que aconteceram) */
public class MovimentacaoRepository {

    private final List<Movimentacao> movimentacoes = new ArrayList<>();

    public void salvar(Movimentacao movimentacao) {
        movimentacoes.add(movimentacao);
    }

    /** O identificador é sequencial: 1, 2, 3... */
    public int proximoId() {
        return movimentacoes.size() + 1;
    }

    public List<Movimentacao> listarTodas() {
        return new ArrayList<>(movimentacoes);
    }
}
