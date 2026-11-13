package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

public class ProdutoService {

    private static final String MSG_CAMPOS_OBRIGATORIOS = "Todos os campos obrigatórios devem ser preenchidos";
    private static final String MSG_PRECO_INVALIDO = "O preço deve ser maior que zero";
    private static final String MSG_QUANTIDADE_NEGATIVA = "A quantidade inicial não pode ser negativa";
    private static final String MSG_CODIGO_DUPLICADO = "Já existe um produto com este código";

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Produto produto) {
        validarCamposObrigatorios(produto);
        validarPreco(produto);
        validarQuantidade(produto);
        validarCodigoUnico(produto);
        repository.salvar(produto);
    }

    private void validarCamposObrigatorios(Produto produto) {
        if (estaVazio(produto.getCodigo())
                || estaVazio(produto.getNome())
                || estaVazio(produto.getCategoria())
                || estaVazio(produto.getTamanho())
                || estaVazio(produto.getCor())) {
            throw new IllegalArgumentException(MSG_CAMPOS_OBRIGATORIOS);
        }
    }

    private void validarPreco(Produto produto) {
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException(MSG_PRECO_INVALIDO);
        }
    }

    private void validarQuantidade(Produto produto) {
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException(MSG_QUANTIDADE_NEGATIVA);
        }
    }

    private void validarCodigoUnico(Produto produto) {
        if (repository.existe(produto.getCodigo())) {
            throw new IllegalArgumentException(MSG_CODIGO_DUPLICADO);
        }
    }

    private boolean estaVazio(String texto) {
        return texto == null || texto.isBlank();
    }

    public void excluir(String codigo) {
        // ainda não implementado (TDD: etapa RED)
    }
}