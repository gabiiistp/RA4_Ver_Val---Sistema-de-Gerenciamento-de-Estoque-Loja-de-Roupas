package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.EstoqueService;
import br.pucpr.sistemaestoque.service.ProdutoService;

/**
 * O sistema compartilhado pelas classes de steps de UM cenário.
 * O Cucumber cria um novo a cada cenário, então todos começam com o estoque vazio.
 *
 * Regra do grupo: cada um acrescenta só o seu service aqui, sem mexer no dos outros.
 */
public class ContextoSistema {

    public final ProdutoRepository repository = new ProdutoRepository();

    public final ProdutoService produtoService = new ProdutoService(repository);
    public final EstoqueService estoqueService = new EstoqueService(repository);
    // Pedro: acrescentar aqui o MovimentacaoService
    // Laura: acrescentar aqui o ConsultaService

    /** Mensagem do último resultado (de sucesso ou do erro lançado) */
    public String resultado;

    /** Executa uma ação e guarda a mensagem de sucesso ou a do erro lançado */
    public void tentar(Runnable acao, String mensagemSucesso) {
        try {
            acao.run();
            resultado = mensagemSucesso;
        } catch (IllegalArgumentException erro) {
            resultado = erro.getMessage();
        }
    }
}