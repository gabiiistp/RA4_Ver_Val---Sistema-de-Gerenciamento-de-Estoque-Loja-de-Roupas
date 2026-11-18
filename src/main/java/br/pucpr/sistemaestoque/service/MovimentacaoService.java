package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Movimentacao;
import br.pucpr.sistemaestoque.model.TipoMovimentacao;
import br.pucpr.sistemaestoque.repository.MovimentacaoRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Histórico (log) de movimentações  ·  responsável: Pedro
 *
 * RN08 - Toda entrada ou saída gera registro no histórico de movimentações
 */
public class MovimentacaoService {

    private static final String MSG_CODIGO_OBRIGATORIO = "O código do produto deve ser informado";
    private static final String MSG_QUANTIDADE_INVALIDA = "A quantidade da movimentação deve ser maior que zero";

    private final MovimentacaoRepository repository;

    public MovimentacaoService(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    // ============================================================
    // LOG DE ENTRADA
    // ============================================================
    public void registrarEntrada(String codigoProduto, int quantidade) {
        registrar(codigoProduto, TipoMovimentacao.ENTRADA, quantidade);
    }

    // ============================================================
    // CONSULTA DO HISTÓRICO
    // ============================================================
    public List<Movimentacao> listarHistorico() {
        return repository.listarTodas();
    }

    // ============================================================
    // APOIO (reaproveitado pelo log de saída)
    // ============================================================
    private void registrar(String codigoProduto, TipoMovimentacao tipo, int quantidade) {
        validarCodigoDoProduto(codigoProduto);
        validarQuantidade(quantidade);
        repository.salvar(new Movimentacao(repository.proximoId(), codigoProduto, tipo,
                quantidade, LocalDateTime.now()));
    }

    private void validarCodigoDoProduto(String codigoProduto) {
        if (codigoProduto == null || codigoProduto.isBlank()) {
            throw new IllegalArgumentException(MSG_CODIGO_OBRIGATORIO);
        }
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException(MSG_QUANTIDADE_INVALIDA);
        }
    }
}
