package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Movimentacao;
import br.pucpr.sistemaestoque.model.TipoMovimentacao;
import br.pucpr.sistemaestoque.repository.MovimentacaoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MovimentacaoService {

    private final MovimentacaoRepository repository;

    public MovimentacaoService(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    public void registrarEntrada(String codigoProduto, int quantidade) {
        if (codigoProduto == null || codigoProduto.isBlank()) {
            throw new IllegalArgumentException("O código do produto deve ser informado");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade da movimentação deve ser maior que zero");
        }
        int id = repository.listarTodas().size() + 1;
        repository.salvar(new Movimentacao(id, codigoProduto, TipoMovimentacao.ENTRADA,
                quantidade, LocalDateTime.now()));
    }

    public List<Movimentacao> listarHistorico() {
        return repository.listarTodas();
    }
}
