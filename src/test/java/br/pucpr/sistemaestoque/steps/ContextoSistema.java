package br.pucpr.sistemaestoque.steps;

import br.pucpr.sistemaestoque.repository.MovimentacaoRepository;
import br.pucpr.sistemaestoque.repository.ProdutoRepository;
import br.pucpr.sistemaestoque.service.ConsultaService;
import br.pucpr.sistemaestoque.service.EstoqueService;
import br.pucpr.sistemaestoque.service.MovimentacaoService;
import br.pucpr.sistemaestoque.service.ProdutoService;

public class ContextoSistema {

    public final ProdutoRepository repository = new ProdutoRepository();

    public final ProdutoService produtoService = new ProdutoService(repository);
    public final EstoqueService estoqueService = new EstoqueService(repository);
    public final MovimentacaoService movimentacaoService = new MovimentacaoService(new MovimentacaoRepository());
    public final ConsultaService consultaService = new ConsultaService(repository);

    public String resultado;

    public void tentar(Runnable acao, String mensagemSucesso) {
        try {
            acao.run();
            resultado = mensagemSucesso;
        } catch (IllegalArgumentException erro) {
            resultado = erro.getMessage();
        }
    }
}