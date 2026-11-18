package br.pucpr.sistemaestoque.model;

import java.time.LocalDateTime;

/**
 * Um registro do histórico de movimentações  ·  responsável: Pedro
 * Guarda só o código do produto, por isso não depende da classe Produto.
 */
public class Movimentacao {

    private final int id;
    private final String codigoProduto;
    private final TipoMovimentacao tipo;
    private final int quantidade;
    private final LocalDateTime data;

    public Movimentacao(int id, String codigoProduto, TipoMovimentacao tipo,
                        int quantidade, LocalDateTime data) {
        this.id = id;
        this.codigoProduto = codigoProduto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }

    public int getId() { return id; }
    public String getCodigoProduto() { return codigoProduto; }
    public TipoMovimentacao getTipo() { return tipo; }
    public int getQuantidade() { return quantidade; }
    public LocalDateTime getData() { return data; }
}
