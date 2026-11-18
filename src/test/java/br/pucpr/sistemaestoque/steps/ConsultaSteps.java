package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.model.Produto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsultaSteps {

    private final ContextoSistema contexto;

    // resultado da última consulta feita no cenário
    private List<Produto> resultado = new ArrayList<>();

    public ConsultaSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Dado("que o catálogo tem o produto {string} de código {string} com {int} em estoque e mínimo {int}")
    public void queOCatalogoTemOProduto(String nome, String codigo, int quantidade, int estoqueMinimo) {
        contexto.produtoService.cadastrar(
                new Produto(codigo, nome, "Blusa", "M", "Preta", 59.90, quantidade, estoqueMinimo));
    }

    // ---------- RN09 · busca por nome ----------

    @Quando("busco por produtos com o nome {string}")
    public void buscoPorProdutosComONome(String nome) {
        try {
            resultado = contexto.consultaService.buscarPorNome(nome);
            contexto.resultado = "Busca realizada com sucesso";
        } catch (IllegalArgumentException erro) {
            resultado = new ArrayList<>();
            contexto.resultado = erro.getMessage();
        }
    }

    @Então("a busca deve retornar {int} produto(s)")
    public void aBuscaDeveRetornarProdutos(int esperado) {
        assertEquals(esperado, resultado.size());
    }

    @E("a busca deve conter o produto de código {string}")
    public void aBuscaDeveConterOProdutoDeCodigo(String codigo) {
        assertTrue(resultado.stream().anyMatch(produto -> produto.getCodigo().equals(codigo)));
    }

    @Então("a busca deve ser recusada com a mensagem {string}")
    public void aBuscaDeveSerRecusadaComAMensagem(String mensagem) {
        assertEquals(mensagem, contexto.resultado);
    }

    // ---------- RN10 · estoque baixo ----------

    @Quando("consulto os produtos com estoque baixo")
    public void consultoOsProdutosComEstoqueBaixo() {
        resultado = contexto.consultaService.listarEstoqueBaixo();
    }

    @Então("a consulta de estoque baixo deve retornar {int} produto(s)")
    public void aConsultaDeEstoqueBaixoDeveRetornarProdutos(int esperado) {
        assertEquals(esperado, resultado.size());
    }

    @E("o alerta de estoque baixo deve conter o produto de código {string}")
    public void oAlertaDeEstoqueBaixoDeveConterOProdutoDeCodigo(String codigo) {
        assertTrue(resultado.stream().anyMatch(produto -> produto.getCodigo().equals(codigo)));
    }
}
