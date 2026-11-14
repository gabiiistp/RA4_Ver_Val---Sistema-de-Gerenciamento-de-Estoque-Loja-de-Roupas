package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.model.Produto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CadastroSteps {

    private static final String MSG_SUCESSO = "Produto cadastrado com sucesso";

    private final ContextoSistema contexto;

    // O Cucumber entrega o ContextoSistema aqui
    public CadastroSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Dado("que estou no sistema de estoque")
    public void queEstouNoSistemaDeEstoque() {
        // o sistema já começa vazio
    }

    @Dado("que já existe um produto com código {string}")
    public void queJaExisteUmProdutoComCodigo(String codigo) {
        contexto.produtoService.cadastrar(
                new Produto(codigo, "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
    }

    @Quando("cadastro um produto com código {string}, nome {string}, categoria {string}, tamanho {string}, cor {string}, preço {string} e quantidade {string}")
    public void cadastroUmProduto(String codigo, String nome, String categoria,
                                  String tamanho, String cor, String preco, String quantidade) {
        Produto produto = new Produto(codigo, nome, categoria, tamanho, cor,
                Double.parseDouble(preco), Integer.parseInt(quantidade), 2);
        contexto.tentar(() -> contexto.produtoService.cadastrar(produto), MSG_SUCESSO);
    }

    @Quando("tento cadastrar outro produto com o código {string}")
    public void tentoCadastrarOutroProdutoComOCodigo(String codigo) {
        Produto produto = new Produto(codigo, "Calça", "Calça", "G", "Azul", 99.90, 5, 1);
        contexto.tentar(() -> contexto.produtoService.cadastrar(produto), MSG_SUCESSO);
    }

    @Então("o resultado deve ser {string}")
    public void oResultadoDeveSer(String esperado) {
        assertEquals(esperado, contexto.resultado);
    }

    @E("o produto {string} deve estar cadastrado")
    public void oProdutoDeveEstarCadastrado(String codigo) {
        assertTrue(contexto.repository.existe(codigo));
    }

    @E("o produto {string} deve continuar com o nome {string}")
    public void oProdutoDeveContinuarComONome(String codigo, String nome) {
        assertEquals(nome, contexto.repository.buscarPorCodigo(codigo).get().getNome());
    }
}