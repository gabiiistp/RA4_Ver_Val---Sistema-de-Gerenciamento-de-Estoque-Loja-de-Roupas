<div align="center">

# 👕 Sistema de Gerenciamento de Estoque

### Loja de Roupas · BDD + TDD em Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

![Status](https://img.shields.io/badge/status-em_desenvolvimento-yellow?style=flat-square)
![Entrega](https://img.shields.io/badge/entrega-17%2F11-red?style=flat-square)
![Consultoria](https://img.shields.io/badge/consultoria-ter%C3%A7a_17%2F11-orange?style=flat-square)
![Peso](https://img.shields.io/badge/RA4-60%25_da_nota-blue?style=flat-square)

**Verificação e Validação de Software · PUCPR**

</div>

---

## 📌 Resumo rápido

| | |
|---|---|
| 🎯 **Objetivo** | Praticar BDD e TDD com um sistema simples de estoque |
| 👥 **Equipe** | 4 integrantes (Isabelle, Gabriela, Pedro e Laura) |
| 📦 **Entrega** | Código + cenários BDD + relatório TDD com evidências |
| 📅 **Prazo** | **Entrega até 17/11** · 🗣️ **consultoria com o professor na terça, 17/11** |
| 👨‍🏫 **Professor** | Marcelo Augusto Goncalves Bardi |
| 🧪 **Abordagem** | Teste de **caixa preta** (BDD com Cucumber) + TDD com JUnit |
| ☕ **Java** | JDK 17 ou superior (o `pom.xml` compila para Java 17) |

---

> [!IMPORTANT]
> ### 📝 Não esqueçam de preencher o relatório!
> A entrega inclui o **relatório do processo BDD/TDD** (`Relatorio_BDD_TDD_Sistema_Estoque.docx`). Cada integrante preenche **só a sua seção**, conforme o guia do próprio documento.
>
> - 🔗 **Documento compartilhado do grupo:** https://pucpredu-my.sharepoint.com/:w:/r/personal/gabriela_terniovicz_pucpr_edu_br/Documents/Relatorio_BDD_TDD_Sistema_Estoque.docx?d=w8a9c7317499d4f18971218c6ed93d050&csf=1&web=1&e=tV0NGS
> - 🧑‍💻 Preencham conforme forem terminando cada funcionalidade, e não só no fim (os prints de **RED, GREEN e REFACTOR** precisam ser tirados durante o desenvolvimento).
> - 📸 Cada print entra **no relatório** e também na sua pasta `evidencias/seu-nome/`, com descrição do que foi feito e do resultado.
> - 🧹 Antes de entregar: apagar a página de guia e as caixas amarelas "Como preencher".

---

## 🗺️ Quem faz o quê

| | Integrante | Funcionalidades | Branch |
|:-:|---|---|---|
| 🥇 | **Isabelle** | Cadastrar · Excluir · Entrada no estoque | `feature/isa` |
| 🥈 | **Gabriela** | Editar · Listar · Saída do estoque | `feature/gabi` |
| 🥈 | **Pedro** | Log de entrada · Log de saída | `feature/pedro` |
| 🥉 | **Laura** | Buscar por nome · Estoque baixo | `feature/laura` |

---

## 🚦 Ordem de desenvolvimento

> **Regra de ouro:** todo mundo depende da classe `Produto`. Por isso a **Isabelle começou**, e o Pedro começa em paralelo porque o log dele não depende de produto.

<img width="598" height="566" alt="Captura de tela_12-11-2026_175141_claude ai" src="https://github.com/user-attachments/assets/f4c21766-fb09-4964-94f3-1d4200c5835c" />

### 🚧 Situação da `main`

| Já está pronto | Quem usa |
|---|---|
| ✅ `Produto` e `ProdutoRepository` | Todos |
| ✅ Cadastrar e excluir produto (TDD + BDD) | Isabelle |
| ✅ Entrada no estoque (TDD + BDD) | Isabelle |
| ✅ `EstoqueService` (entrada pronta, **saída vazia para a Gabriela**) | Isabelle · Gabriela |
| ✅ `ContextoSistema` (sistema compartilhado dos steps do Cucumber) | Todos |
| ⏳ Editar, listar e saída | Gabriela |
| ⏳ Log de entrada e saída | Pedro |
| ⏳ Busca por nome e estoque baixo | Laura |

> 🔄 **Antes de começar ou de abrir PR:** `Git → Pull` na `main` e depois **Merge 'origin/main' into** a sua branch. O `pom.xml` mudou (PicoContainer), então recarreguem o Maven.

### 📆 Cronograma

| Quando | Fase | Quem |
|---|---|---|
| 12–13/11 | ✅ Setup + base + cadastrar e excluir | Isabelle |
| 14/11 | ✅ Entrada no estoque + relatório da Isabelle | Isabelle |
| 14–15/11 | 🥈🥉 Funcionalidades de cada um (TDD + BDD) | Gabriela · Pedro · Laura |
| 16/11 | 🔗 Integração + PRs na `main` + relatório fechado | Todos |
| **17/11** | 🗣️ **Consultoria + 🚀 entrega** | Todos |

---

## 🧑‍💻 Onde cada um desenvolve

### 🥇 Isabelle · `feature/isa`

| Tipo | Arquivo |
|---|---|
| 🧱 Model | `model/Produto.java` |
| 💾 Repository | `repository/ProdutoRepository.java` |
| ⚙️ Service | `service/ProdutoService.java` (cadastrar, excluir) · `service/EstoqueService.java` (entrada) |
| 🧪 JUnit | `tests/CadastroProdutoTest` · `ExclusaoProdutoTest` · `EntradaEstoqueTest` |
| 🥒 Feature | `cadastro.feature` · `exclusao.feature` · `entrada.feature` |
| 👣 Steps | `steps/CadastroSteps` · `ExclusaoSteps` · `EntradaSteps` · `steps/ContextoSistema` |
| 📸 Evidências | `evidencias/isa/` |

**Passo a passo**
- [X] 1. Setup: subir o esqueleto do projeto (já vem com `pom.xml`, pastas e runner do Cucumber)
- [X] 2. `Produto` + `ProdutoRepository` (em memória) → PR para a main
- [X] 3. Cadastrar produto (RN01–RN04)
- [X] 4. Excluir produto (RN11)
- [X] 5. Entrada no estoque (RN06) → `EstoqueService`
- [X] 6. Evidências RED / GREEN / REFACTOR / BDD e relatório

---

### 🥈 Gabriela · `feature/gabi`

| Tipo | Arquivo |
|---|---|
| ⚙️ Service | `service/ProdutoService.java` (editar, listar) · `service/EstoqueService.java` (saída) |
| 🧪 JUnit | `tests/EdicaoProdutoTest` · `ListagemProdutosTest` · `SaidaEstoqueTest` |
| 🥒 Feature | `edicao.feature` · `listagem.feature` · `saida.feature` |
| 👣 Steps | `steps/EdicaoSteps` · `ListagemSteps` · `SaidaSteps` |
| 📸 Evidências | `evidencias/gabi/` |

**Passo a passo**
- [ ] 1. ✅ A base já está na `main`: faça o pull e atualize a sua branch
- [ ] 2. Editar produto (RN11): chame o `validarProdutoExistente(codigo)` que já existe no `ProdutoService`
- [ ] 3. Listar produtos (`repository.listarTodos()` já existe)
- [ ] 4. Saída do estoque (RN05, RN07): implemente o `registrarSaida` e chame o `buscarProdutoOuFalhar(codigo)` do `EstoqueService`
- [ ] 5. Evidências RED / GREEN / REFACTOR / BDD

---

### 🥈 Pedro · `feature/pedro`

| Tipo | Arquivo |
|---|---|
| 🧱 Model | `model/Movimentacao.java` |
| 💾 Repository | `repository/MovimentacaoRepository.java` |
| ⚙️ Service | `service/MovimentacaoService.java` |
| 🧪 JUnit | `tests/RegistroEntradaTest` · `RegistroSaidaTest` |
| 🥒 Feature | `movimentacao.feature` |
| 👣 Steps | `steps/MovimentacaoSteps` |
| 📸 Evidências | `evidencias/pedro/` |

**Passo a passo**
- [ ] 1. ✅ Pode começar já (usa só o código do produto, sem depender de `Produto`)
- [ ] 2. `Movimentacao` + `MovimentacaoRepository`
- [ ] 3. Registrar entrada no log
- [ ] 4. Registrar saída no log (RN08)
- [ ] 5. Acrescentar o `MovimentacaoService` no `ContextoSistema` (uma linha)
- [ ] 6. Combinar com Isabelle e Gabriela o método que elas vão chamar na integração (ex.: `registrar(codigo, tipo, quantidade)`)
- [ ] 7. Evidências RED / GREEN / REFACTOR / BDD

---

### 🥉 Laura · `feature/laura`

| Tipo | Arquivo |
|---|---|
| ⚙️ Service | `service/ConsultaService.java` (classe nova, para não dar conflito com o `ProdutoService`) |
| 🧪 JUnit | `tests/BuscaPorNomeTest` · `EstoqueBaixoTest` |
| 🥒 Feature | `consulta.feature` |
| 👣 Steps | `steps/ConsultaSteps` |
| 📸 Evidências | `evidencias/laura/` |

**Passo a passo**
- [ ] 1. ✅ A base já está na `main`: faça o pull e atualize a sua branch (`service.cadastrar(...)` monta os produtos dos testes)
- [ ] 2. Buscar produto pelo nome (RN09)
- [ ] 3. Consultar estoque baixo (RN10)
- [ ] 4. Acrescentar o `ConsultaService` no `ContextoSistema` (uma linha)
- [ ] 5. Evidências RED / GREEN / REFACTOR / BDD

---

## ⚠️ Arquivos compartilhados (cuidado com conflito)

| Arquivo | Quem mexe | Como evitar conflito |
|---|---|---|
| `Produto.java` | Isabelle (dona) | Ninguém altera sem avisar; pedir por PR/mensagem |
| `ProdutoService.java` | Isabelle, Gabriela | Cada uma adiciona **métodos próprios** no final; dar pull da `main` antes de abrir PR |
| `EstoqueService.java` | Isabelle (entrada), Gabriela (saída) | Cada uma escreve **só na sua faixa** (`ENTRADA` / `SAÍDA`); a Gabriela atualiza a branch antes de começar |
| `ContextoSistema.java` | Todos | Só **acrescentar uma linha** com o seu service (Pedro e Laura); não alterar o que já existe |
| `pom.xml` e runner | Isabelle (Setup) | Só a Isabelle mexe; os outros pedem o que precisarem |
| `steps/` | Todos | **Uma classe de steps por feature**; a frase de um passo não pode se repetir em outra classe |

---

## 🔴🟢🔵 Ciclo TDD (para cada funcionalidade)

```text
 🔴 RED       escreve o teste → roda → FALHA        📸 print
      ↓
 🟢 GREEN     implementa o mínimo → roda → PASSA    📸 print
      ↓
 🔵 REFACTOR  limpa o código → roda → continua OK   📸 print
```

> 💡 Rodem o teste pela **setinha verde** ao lado do método (e não pelo botão `test` do Maven) para o print do RED ficar limpo.

## 🥒 Ciclo BDD (para cada funcionalidade)

```text
 1. Escrever o cenário no .feature (Dado / Quando / Então)
 2. Escrever os steps
 3. Rodar o cenário
 4. 📸 Print do Gherkin + resultado
```

> 💡 Sempre incluir `# language: pt` na primeira linha dos arquivos `.feature`.
> 💡 Cada funcionalidade precisa de **pelo menos 1 cenário positivo e 1 negativo**.

```gherkin
# language: pt
Funcionalidade: Exclusão de produtos

  Cenário: Excluir produto existente
    Dado que o estoque possui o produto de código "001"
    Quando excluo o produto de código "001"
    Então a exclusão deve resultar em "Produto excluído com sucesso"
    E o produto de código "001" não deve mais existir no estoque

  Cenário: Excluir o mesmo produto duas vezes
    Dado que o estoque possui o produto de código "001"
    Quando excluo o produto de código "001"
    E excluo o produto de código "001"
    Então a exclusão deve resultar em "Produto não encontrado"
```

> Modelos completos: `cadastro.feature`, `exclusao.feature` e `entrada.feature`.

---

## 👣 Como escrever os steps (Cucumber)

Os steps de **todas** as funcionalidades usam o mesmo estoque por meio da classe `ContextoSistema`. O Cucumber cria um contexto novo a cada cenário (por isso cada cenário começa com o estoque vazio) e entrega **o mesmo objeto** para todas as classes de steps. Quem faz isso é o **PicoContainer** (já está no `pom.xml`).

**Modelo de classe de steps** (veja `CadastroSteps`, `ExclusaoSteps` e `EntradaSteps`):

```java
public class SuaFuncionalidadeSteps {

    private final ContextoSistema contexto;

    // O Cucumber entrega o ContextoSistema aqui
    public SuaFuncionalidadeSteps(ContextoSistema contexto) {
        this.contexto = contexto;
    }

    @Quando("frase única do seu Quando")
    public void seuQuando() {
        // contexto.tentar guarda a mensagem de sucesso OU a do erro lançado
        contexto.tentar(() -> contexto.produtoService.algo(...), "Mensagem de sucesso");
    }

    @Então("a sua frase única deve resultar em {string}")
    public void seuEntao(String esperado) {
        assertEquals(esperado, contexto.resultado);
    }
}
```

| Combinado | Detalhe |
|---|---|
| **Sistema compartilhado** | Use `contexto.produtoService`, `contexto.estoqueService` e `contexto.repository`. Não crie `new ProdutoRepository()` nos steps. |
| **Resultado** | Use `contexto.tentar(...)` e confira com `contexto.resultado` |
| **Seu service no contexto** | Pedro (`MovimentacaoService`) e Laura (`ConsultaService`) acrescentam **uma linha** no `ContextoSistema` |
| **Frases únicas** | A mesma frase em duas classes dá `DuplicateStepDefinitionException`. Coloque o **assunto da sua feature** na frase (ex.: `excluo o produto...`, `dou entrada de...`, `dou saída de...`) |
| **Passos de outra classe** | Podem ser usados no seu `.feature`, porque o estoque é o mesmo (ex.: `excluo o produto de código "001"` em um cenário de entrada) |

---

## ⬛ Teste de caixa preta (no Cucumber)

> Caixa preta = testar **o que o sistema deve fazer** (entrada → resultado), sem depender de **como** o código foi escrito.

### Regras para escrever os steps

| ✅ Pode | ❌ Não pode |
|---|---|
| Partir das **regras de negócio** (RN01–RN11) | Partir de como o código foi implementado |
| Chamar só **métodos públicos** dos services | Mexer direto em atributos internos |
| Conferir só o **resultado** (mensagem, lista, quantidade) | Conferir variáveis internas ou chamadas de método |
| Escolher os casos com as **técnicas** abaixo | Escolher casos olhando os `if` do código |

### Técnicas e onde aplicar

| Técnica | Aplicação neste projeto |
|---|---|
| 🟦 **Partição de equivalência** | Campos obrigatórios: preenchido vs. vazio · Código de produto: existente, inexistente e já excluído |
| 📏 **Valor limite** | Preço: `-1`, `0`, `0.01` · Quantidade inicial: `-1`, `0`, `1` · Entrada: `-1`, `0`, `1` · Saída: igual ao disponível vs. disponível + 1 |
| 📋 **Tabela de decisão** | Saída: produto existe? × quantidade suficiente? |
| 🔁 **Transição de estado** | Quantidade antes e depois de entrada e saída |

### Exemplo com `Esquema do Cenário` (valor limite)

```gherkin
# language: pt
Funcionalidade: Entrada de produtos no estoque

  Esquema do Cenário: Validar a quantidade da entrada
    Dado que o produto de código "001" tem 10 unidades em estoque
    Quando dou entrada de <quantidade> unidades no produto de código "001"
    Então a entrada deve resultar em "<resultado>"
    E o produto de código "001" deve ficar com <estoque_final> unidades em estoque

    Exemplos:
      | quantidade | resultado                                       | estoque_final |
      | -1         | A quantidade da entrada deve ser maior que zero | 10            |
      | 0          | A quantidade da entrada deve ser maior que zero | 10            |
      | 1          | Entrada registrada com sucesso                  | 11            |
```

---

## 🌿 Fluxo Git

```text
main ─────────────●────────●────────●──────────▶   (nunca commitar direto)
                   ↑        ↑        ↑
feature/isa  ──────┘        │        │
feature/pedro ──────────────┘        │
feature/gabi / laura ────────────────┘
```

| Passo | Comando / ação |
|---|---|
| 1. Clonar | `git clone URL_DO_REPOSITORIO` |
| 2. Criar branch | `git checkout -b feature/seu-nome` |
| 3. Atualizar com a main | `git pull origin main` (ou, no IntelliJ: **Merge 'origin/main' into** a sua branch) |
| 4. Commit | `git commit -m "test: cadastro de produto (RED)"` |
| 5. Enviar | `git push -u origin feature/seu-nome` |
| 6. Pull Request | Abrir PR para a `main` no GitHub e pedir para alguém do grupo dar o merge |

**Padrão de commit:** `test:` (RED) · `feat:` (GREEN) · `refactor:` · `docs:`

---

## 🏗️ Estrutura do projeto

```text
SistemaEstoque/
├── 📁 src/
│   ├── 📁 main/java/br/pucpr/sistemaestoque/
│   │   ├── 📁 model/
│   │   │   ├── Produto.java               🥇 Isabelle
│   │   │   └── Movimentacao.java          🥈 Pedro
│   │   ├── 📁 service/
│   │   │   ├── ProdutoService.java        🥇 Isabelle + 🥈 Gabriela
│   │   │   ├── EstoqueService.java        🥇 Isabelle (entrada) + 🥈 Gabriela (saída)
│   │   │   ├── MovimentacaoService.java   🥈 Pedro
│   │   │   └── ConsultaService.java       🥉 Laura
│   │   └── 📁 repository/
│   │       ├── ProdutoRepository.java     🥇 Isabelle
│   │       └── MovimentacaoRepository.java 🥈 Pedro
│   └── 📁 test/
│       ├── 📁 java/br/pucpr/sistemaestoque/
│       │   ├── 📁 tests/                  🧪 JUnit (todos)
│       │   ├── 📁 steps/                  👣 Steps (um por feature) + ContextoSistema
│       │   └── 📁 runners/                🏃 Runner (Isabelle)
│       └── 📁 resources/features/
│           ├── cadastro.feature           🥇 Isabelle
│           ├── exclusao.feature           🥇 Isabelle
│           ├── entrada.feature            🥇 Isabelle
│           ├── edicao.feature             🥈 Gabriela
│           ├── listagem.feature           🥈 Gabriela
│           ├── saida.feature              🥈 Gabriela
│           ├── movimentacao.feature       🥈 Pedro
│           └── consulta.feature           🥉 Laura
├── 📁 evidencias/
│   ├── 📁 gabriela/    (RED · GREEN · REFACTOR · BDD)
│   ├── 📁 isabelle/   (RED · GREEN · REFACTOR · BDD)
│   ├── 📁 pedro/  (RED · GREEN · REFACTOR · BDD)
│   └── 📁 laura/  (RED · GREEN · REFACTOR · BDD)
├── pom.xml
└── README.md
```

---

## 🧾 Modelo de dados

<details>
<summary><b>📦 Produto</b></summary>

| Atributo | Descrição |
|---|---|
| Código | Identificador único |
| Nome | Nome do produto |
| Categoria | Calça, blusa, vestido... |
| Tamanho | P, M, G, GG |
| Cor | Cor do produto |
| Preço | Valor de venda |
| Quantidade | Quantidade em estoque |
| Estoque mínimo | Limite para alerta de estoque baixo |

</details>

<details>
<summary><b>🔁 Movimentação</b></summary>

| Atributo | Descrição |
|---|---|
| Identificador | ID da movimentação |
| Código do produto | Produto movimentado |
| Tipo | Entrada ou saída |
| Quantidade | Quantidade movimentada |
| Data | Data da movimentação |

</details>

## 📏 Regras de negócio

| # | Regra | Quem testa |
|---|---|:-:|
| RN01 | Campos obrigatórios preenchidos | Isabelle |
| RN02 | Não repetir código de produto | Isabelle |
| RN03 | Preço maior que zero | Isabelle |
| RN04 | Quantidade inicial não negativa | Isabelle |
| RN05 | Saída não pode passar do disponível | Gabriela |
| RN06 | Entrada aumenta a quantidade | Isabelle |
| RN07 | Saída diminui a quantidade | Gabriela |
| RN08 | Toda entrada/saída gera histórico | Pedro |
| RN09 | Consultar produto pelo nome | Laura |
| RN10 | Identificar estoque ≤ mínimo | Laura |
| RN11 | Não editar/excluir/movimentar produto inexistente | Isabelle · Gabriela |

### 💬 Mensagens de erro padrão

Todos os erros são `IllegalArgumentException` com mensagem. Reaproveitem estes textos para o sistema ficar consistente:

| Situação | Mensagem |
|---|---|
| Campo obrigatório vazio | `Todos os campos obrigatórios devem ser preenchidos` |
| Preço menor ou igual a zero | `O preço deve ser maior que zero` |
| Quantidade inicial negativa | `A quantidade inicial não pode ser negativa` |
| Código duplicado | `Já existe um produto com este código` |
| Produto inexistente (RN11) | `Produto não encontrado` |
| Quantidade da entrada menor ou igual a zero | `A quantidade da entrada deve ser maior que zero` |

> Para a RN11, use os métodos que já existem: `validarProdutoExistente(codigo)` (no `ProdutoService`) e `buscarProdutoOuFalhar(codigo)` (no `EstoqueService`).

---

## ▶️ Como rodar

No IntelliJ:

- **Cucumber (BDD):** botão direito em `runners/RunCucumberTest` → **Run 'RunCucumberTest'**
- **JUnit (TDD):** botão direito na classe de teste → **Run**, ou na setinha verde ao lado de cada método
- **Tudo:** painel **Maven → Lifecycle → test**

No terminal (precisa do Maven instalado):

```bash
mvn test
```

---

## 📸 Evidências

| Etapa | O que mostrar |
|---|---|
| 🔴 RED | Print do teste falhando |
| 🟢 GREEN | Print do teste passando |
| 🔵 REFACTOR | Print do código refatorado + testes passando |
| 🥒 BDD | Print do Gherkin + resultado da execução |

- Cada imagem precisa de uma **descrição** (o que foi feito e o resultado)
- Nome sugerido: `funcionalidade_etapa_numero.png` → `cadastrar-produto_RED.png`
- Salvar em `evidencias/<nome>/<etapa>/`

---

## ✅ Checklist de entrega · até 17/11

- [ ] Escopo aprovado pelo professor
- [ ] Código-fonte completo
- [ ] Cenários BDD (`.feature`) positivos e negativos
- [ ] Testes JUnit
- [ ] Cenários de limite com `Esquema do Cenário` e técnicas de caixa preta citadas no relatório
- [ ] Relatório dos passos do TDD (imagem + descrição) preenchido por todos, sem a página de guia e sem as caixas "Como preencher"
- [ ] Evidências RED / GREEN / REFACTOR de cada integrante
- [ ] Evidências dos cenários BDD
- [ ] Integração: entrada e saída registram no histórico do Pedro
- [ ] Repositório GitHub integrado na `main`
- [ ] Todos os testes passando na `main` (JUnit e Cucumber)

---

<div align="center">

**👥 Isabelle · Gabriela · Pedro · Laura**

Feito para a disciplina de Verificação e Validação de Software · PUCPR

</div>
