package br.pucpr.sistemaestoque.tests;

import br.pucpr.sistemaestoque.model.Produto;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EdicaoProdutoTest {

    private static final String MSG_NAO_ENCONTRADO = "Produto não encontrado";
    private static final String MSG_CAMPOS_OBRIGATORIOS = "Todos os campos obrigatórios devem ser preenchidos";
    private static final String MSG_PRECO_INVALIDO = "O preço deve ser maior que zero";

    private ProdutoRepository repository;
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        service = new ProdutoService(repository);
    }

    private void cadastrarBlusa() {
        service.cadastrar(new Produto("001", "Blusa", "Blusa", "M", "Preta", 59.90, 10, 2));
    }

    private Produto blusaCadastrada() {
        return repository.buscarPorCodigo("001").get();
    }

    /** Dados de edição da blusa "001" (a quantidade enviada aqui deve ser ignorada) */
    private Produto dadosDaBlusa(String nome, String categoria, String tamanho, String cor, double preco) {
        return new Produto("001", nome, categoria, tamanho, cor, preco, 999, 4);
    }

    private IllegalArgumentException editarEEsperarErro(Produto dados) {
        return assertThrows(IllegalArgumentException.class, () -> service.editar(dados));
    }

    @Test
    void naoDeveEditarProdutoQueNuncaFoiCadastrado() {
        cadastrarBlusa();

        Produto dados = new Produto(
                "999", "Saia", "Saia", "P", "Verde",
                89.90, 5, 1
        );

        IllegalArgumentException erro = editarEEsperarErro(dados);

        assertEquals(MSG_NAO_ENCONTRADO, erro.getMessage());
    }

    @Test
    void naoDeveEditarProdutoJaExcluido() {
        cadastrarBlusa();
        service.excluir("001");

        IllegalArgumentException erro = editarEEsperarErro(
                dadosDaBlusa("Saia", "Saia", "P", "Verde", 89.90)
        );

        assertEquals(MSG_NAO_ENCONTRADO, erro.getMessage());
    }
}
