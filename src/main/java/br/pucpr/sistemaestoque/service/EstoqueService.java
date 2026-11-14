package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

public class EstoqueService {

    private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
    private static final String MSG_QUANTIDADE_ENTRADA_INVALIDA = "A quantidade da entrada deve ser maior que zero";

    private final ProdutoRepository repository;

    public EstoqueService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // ============================================================
    // ENTRADA NO ESTOQUE  ·  responsável: Isa
    // ============================================================
    public void registrarEntrada(String codigo, int quantidade) {
        validarQuantidadeDaEntrada(quantidade);
        Produto produto = buscarProdutoOuFalhar(codigo);
        produto.setQuantidade(produto.getQuantidade() + quantidade);
    }

    private void validarQuantidadeDaEntrada(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException(MSG_QUANTIDADE_ENTRADA_INVALIDA);
        }
    }

    // ============================================================
    // SAÍDA DO ESTOQUE  ·  responsável: Gabi
    // ============================================================
    public void registrarSaida(String codigo, int quantidade) {
        // a implementar (TDD: Gabi)
    }

    // ============================================================
    // APOIO (usado pela entrada e pela saída)
    // ============================================================

    /** Reutilizável: a saída da Gabi pode chamar este mesmo método (RN11) */
    private Produto buscarProdutoOuFalhar(String codigo) {
        return repository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException(MSG_PRODUTO_NAO_ENCONTRADO));
    }
}