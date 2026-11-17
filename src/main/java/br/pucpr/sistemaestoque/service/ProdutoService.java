package br.pucpr.sistemaestoque.service;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private static final String MSG_CAMPOS_OBRIGATORIOS = "Todos os campos obrigatórios devem ser preenchidos";
    private static final String MSG_PRECO_INVALIDO = "O preço deve ser maior que zero";
    private static final String MSG_QUANTIDADE_NEGATIVA = "A quantidade inicial não pode ser negativa";
    private static final String MSG_CODIGO_DUPLICADO = "Já existe um produto com este código";
    private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";

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
        validarProdutoExistente(codigo);
        repository.remover(codigo);
    }

    private void validarProdutoExistente(String codigo) {
        if (!repository.existe(codigo)) {
            throw new IllegalArgumentException(MSG_PRODUTO_NAO_ENCONTRADO);
        }
    }

    public void editar(Produto dados) {
        validarProdutoExistente(dados.getCodigo());
    }

    private void validarDadosDaEdicao(Produto dados) {
        validarCamposObrigatorios(dados);
        validarPreco(dados);
    }

    private void copiarDadosEditaveis(Produto origem, Produto destino) {
        destino.setNome(origem.getNome());
        destino.setCategoria(origem.getCategoria());
        destino.setTamanho(origem.getTamanho());
        destino.setCor(origem.getCor());
        destino.setPreco(origem.getPreco());
        destino.setEstoqueMinimo(origem.getEstoqueMinimo());
    }

    public List<Produto> listar() {
        return repository.listarTodos();
    }
}