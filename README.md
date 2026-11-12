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
![Entrega](https://img.shields.io/badge/entrega-18%2F11_no_AVA-red?style=flat-square)
![Peso](https://img.shields.io/badge/RA4-60%25_da_nota-blue?style=flat-square)

**Verificação e Validação de Software · PUCPR**

</div>

---

## 📌 Resumo rápido

| | |
|---|---|
| 🎯 **Objetivo** | Praticar BDD e TDD com um sistema simples de estoque |
| 👥 **Equipe** | 4 integrantes (Isa, Gabi, Pedro, Laura) |
| 📦 **Entrega** | Código + cenários BDD + relatório TDD com evidências |
| 📅 **Prazo** | 18/11 no AVA |
| 👨‍🏫 **Professor** | _preencher_ · escopo aprovado? _preencher_ |
| 🧪 **Abordagem** | Teste de **caixa preta** (BDD com Cucumber) + TDD com JUnit |
| ☕ **Java** | JDK _preencher versão_ |

---

## 🗺️ Quem faz o quê

| | Integrante | Funcionalidades | Branch |
|:-:|---|---|---|
| 🥇 | **Isa** | Cadastrar · Excluir · Entrada no estoque | `feature/isa` |
| 🥈 | **Gabi** | Editar · Listar · Saída do estoque | `feature/gabi` |
| 🥈 | **Pedro** | Log de entrada · Log de saída | `feature/pedro` |
| 🥉 | **Laura** | Buscar por nome · Estoque baixo | `feature/laura` |

---

## 🚦 Ordem de desenvolvimento

> **Regra de ouro:** todo mundo depende da classe `Produto`. Por isso a **Isa começa**, e o Pedro começa em paralelo porque o log dele não depende de produto.

<img width="598" height="566" alt="Captura de tela_12-11-2026_175141_claude ai" src="https://github.com/user-attachments/assets/f4c21766-fb09-4964-94f3-1d4200c5835c" />


### 📆 Sugestão de cronograma

| Quando | Fase | Quem |
|---|---|---|
| 12–13/11 | 🛠️ Setup + 🥇 Fase 1 | Isabelle (base) · Pedro (log) |
| 13–15/11 | 🥈🥉 Fase 2 | Gabriela · Laura · Isa |
| 15–16/11 | Saída + 🔗 Integração | Gabriela · Pedro · Isabelle |
| 16–17/11 | 📸 Evidências e relatório | Todos |
| **18/11** | 🚀 **Entrega no AVA** | Todos |

---

## 🧑‍💻 Onde cada um desenvolve

### 🥇 Isa · `feature/isabelle`

| Tipo | Arquivo |
|---|---|
| 🧱 Model | `model/Produto.java` |
| 💾 Repository | `repository/ProdutoRepository.java` |
| ⚙️ Service | `service/ProdutoService.java` (cadastrar, excluir) · `service/EstoqueService.java` (entrada) |
| 🧪 JUnit | `tests/CadastroProdutoTest` · `ExclusaoProdutoTest` · `EntradaEstoqueTest` |
| 🥒 Feature | `cadastro.feature` · `exclusao.feature` |
| 👣 Steps | `steps/CadastroSteps` · `ExclusaoSteps` · `EntradaSteps` |
| 📸 Evidências | `evidencias/isa/` |

**Passo a passo**
- [ ] 1. Setup: subir o esqueleto do projeto (já vem com `pom.xml`, pastas e runner do Cucumber) e conferir que `mvn test` roda
- [ ] 2. `Produto` + `ProdutoRepository` (em memória) → **PR para a main logo**
- [ ] 3. Cadastrar produto (RN01–RN04)
- [ ] 4. Excluir produto (RN11)
- [ ] 5. Entrada no estoque (RN06) → cria o `EstoqueService`
- [ ] 6. Evidências RED / GREEN / REFACTOR / BDD

---

### 🥈 Gabi · `feature/gabriela`

| Tipo | Arquivo |
|---|---|
| ⚙️ Service | `service/ProdutoService.java` (editar, listar) · `service/EstoqueService.java` (saída) |
| 🧪 JUnit | `tests/EdicaoProdutoTest` · `ListagemProdutosTest` · `SaidaEstoqueTest` |
| 🥒 Feature | `edicao.feature` · `listagem.feature` |
| 👣 Steps | `steps/EdicaoSteps` · `ListagemSteps` · `SaidaSteps` |
| 📸 Evidências | `evidencias/gabi/` |

**Passo a passo**
- [ ] 1. ⏳ Espera o PR da Isa com `Produto` (enquanto isso: escreve os cenários `.feature`)
- [ ] 2. Editar produto (RN11)
- [ ] 3. Listar produtos
- [ ] 4. ⏳ Espera a Isa criar o `EstoqueService`
- [ ] 5. Saída do estoque (RN05, RN07)
- [ ] 6. Evidências RED / GREEN / REFACTOR / BDD

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
- [ ] 5. Combinar com Isa e Gabi o método que elas vão chamar (ex.: `registrar(codigo, tipo, quantidade)`)
- [ ] 6. Evidências RED / GREEN / REFACTOR / BDD

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
- [ ] 1. ⏳ Espera o PR da Isa com `Produto` (enquanto isso: escreve os cenários `.feature`)
- [ ] 2. Buscar produto pelo nome (RN09)
- [ ] 3. Consultar estoque baixo (RN10)
- [ ] 4. Evidências RED / GREEN / REFACTOR / BDD

---

## ⚠️ Arquivos compartilhados (cuidado com conflito)

| Arquivo | Quem mexe | Como evitar conflito |
|---|---|---|
| `Produto.java` | Isa (dona) | Ninguém altera sem avisar; pedir por PR/mensagem |
| `ProdutoService.java` | Isa, Gabi | Cada uma adiciona **métodos próprios**; dar `git pull origin main` antes de abrir PR |
| `EstoqueService.java` | Isa (entrada), Gabi (saída) | Isa cria primeiro e faz merge; Gabi adiciona a saída depois |
| `pom.xml` e runner | Isa (Setup) | Só a Isa mexe; os outros pedem o que precisarem |
| `steps/` | Todos | **Uma classe de steps por feature**, nunca no mesmo arquivo |

---

## 🔴🟢🔵 Ciclo TDD (para cada funcionalidade)

```text
 🔴 RED       escreve o teste → roda → FALHA        📸 print
      ↓
 🟢 GREEN     implementa o mínimo → roda → PASSA    📸 print
      ↓
 🔵 REFACTOR  limpa o código → roda → continua OK   📸 print
```

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
Funcionalidade: Cadastro de produtos

  Cenário: Cadastrar produto com sucesso
    Dado que estou no sistema de estoque
    Quando cadastro um produto com código "001"
    E informo o nome "Blusa"
    E informo o tamanho "M"
    E informo a cor "Preta"
    E informo o preço "59.90"
    Então o produto deve ser cadastrado com sucesso

  Cenário: Cadastrar produto com código duplicado
    Dado que já existe um produto com código "001"
    Quando tento cadastrar outro produto com o mesmo código
    Então o sistema deve impedir o cadastro
    E apresentar uma mensagem de erro
```

---

## ⬛ Teste de caixa preta (no Cucumber)

> Caixa preta = testar **o que o sistema deve fazer** (entrada → resultado), sem depender de **como** o código foi escrito.

### Regras para escrever os steps

| ✅ Pode | ❌ Não pode |
|---|---|
| Partir das **regras de negócio** (RN01–RN11) | Partir de como o código foi implementado |
| Chamar só **métodos públicos** dos services | Mexer direto no repository ou em atributos internos |
| Conferir só o **resultado** (mensagem, lista, quantidade) | Conferir variáveis internas ou chamadas de método |
| Escolher os casos com as **técnicas** abaixo | Escolher casos olhando os `if` do código |

### Técnicas e onde aplicar

| Técnica | Aplicação neste projeto |
|---|---|
| 🟦 **Partição de equivalência** | Tamanho: `P`, `M`, `G`, `GG` (válidos) vs. `XL` (inválido) · campo preenchido vs. vazio |
| 📏 **Valor limite** | Preço: `-1`, `0`, `0.01` · Quantidade inicial: `-1`, `0`, `1` · Saída: igual ao disponível vs. disponível + 1 |
| 📋 **Tabela de decisão** | Saída: produto existe? × quantidade suficiente? |
| 🔁 **Transição de estado** | Quantidade antes e depois de entrada e saída |

### Exemplo com `Esquema do Cenário` (valor limite)

```gherkin
# language: pt
Funcionalidade: Cadastro de produtos

  Esquema do Cenário: Validar preço no cadastro
    Quando cadastro um produto com preço "<preco>"
    Então o resultado deve ser "<resultado>"

    Exemplos:
      | preco | resultado                           |
      | -1    | Erro: preço deve ser maior que zero |
      | 0     | Erro: preço deve ser maior que zero |
      | 0.01  | Produto cadastrado com sucesso      |
      | 59.90 | Produto cadastrado com sucesso      |
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

| Passo | Comando |
|---|---|
| 1. Clonar | `git clone URL_DO_REPOSITORIO` |
| 2. Criar branch | `git checkout -b feature/seu-nome` |
| 3. Atualizar com a main | `git pull origin main` |
| 4. Commit | `git commit -m "test: cadastro de produto (RED)"` |
| 5. Enviar | `git push -u origin feature/seu-nome` |
| 6. Pull Request | Abrir PR para a `main` no GitHub |

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
│   │   │   ├── EstoqueService.java        🥇 Isabelle + 🥈 Gabriela
│   │   │   ├── MovimentacaoService.java   🥈 Pedro
│   │   │   └── ConsultaService.java       🥉 Laura
│   │   └── 📁 repository/
│   │       ├── ProdutoRepository.java     🥇 Isabelle
│   │       └── MovimentacaoRepository.java 🥈 Pedro
│   └── 📁 test/
│       ├── 📁 java/br/pucpr/sistemaestoque/
│       │   ├── 📁 tests/                  🧪 JUnit (todos)
│       │   ├── 📁 steps/                  👣 Steps (um por feature)
│       │   └── 📁 runners/                🏃 Runner (Isabelle)
│       └── 📁 resources/features/
│           ├── cadastro.feature           🥇 Isabelle
│           ├── exclusao.feature           🥇 Isabelle
│           ├── edicao.feature             🥈 Gabriela
│           ├── listagem.feature           🥈 Gabriela
│           ├── movimentacao.feature       🥈 Pedro
│           └── consulta.feature           🥉 Laura
├── 📁 evidencias/
│   ├── 📁 isabelle/    (RED · GREEN · REFACTOR · BDD)
│   ├── 📁 gabriela/   (RED · GREEN · REFACTOR · BDD)
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
| RN01 | Campos obrigatórios preenchidos | Isa |
| RN02 | Não repetir código de produto | Isa |
| RN03 | Preço maior que zero | Isa |
| RN04 | Quantidade inicial não negativa | Isa |
| RN05 | Saída não pode passar do disponível | Gabi |
| RN06 | Entrada aumenta a quantidade | Isa |
| RN07 | Saída diminui a quantidade | Gabi |
| RN08 | Toda entrada/saída gera histórico | Pedro |
| RN09 | Consultar produto pelo nome | Laura |
| RN10 | Identificar estoque ≤ mínimo | Laura |
| RN11 | Não editar/excluir produto inexistente | Gabi · Isa |

---

## ▶️ Como rodar

```bash
mvn test
```

No IntelliJ: botão direito em `src/test` → **Run 'All Tests'**.

---

## 📸 Evidências

| Etapa | O que mostrar |
|---|---|
| 🔴 RED | Print do teste falhando |
| 🟢 GREEN | Print do teste passando |
| 🔵 REFACTOR | Print do código refatorado + testes passando |
| 🥒 BDD | Print do Gherkin + resultado da execução |

- Cada imagem precisa de uma **descrição** (o que foi feito e o resultado)
- Nome sugerido: `funcionalidade_etapa_numero.png` → `cadastro_RED_01.png`
- Salvar em `evidencias/<nome>/<etapa>/`

---

## ✅ Checklist de entrega · 18/11 no AVA

- [ ] Escopo aprovado pelo professor
- [ ] Código-fonte completo
- [ ] Cenários BDD (`.feature`) positivos e negativos
- [ ] Testes JUnit
- [ ] Cenários de limite com `Esquema do Cenário` e técnicas de caixa preta citadas no relatório
- [ ] Relatório dos passos do TDD (imagem + descrição)
- [ ] Evidências RED / GREEN / REFACTOR de cada integrante
- [ ] Evidências dos cenários BDD
- [ ] Repositório GitHub integrado na `main`
- [ ] `mvn test` passando na `main`

---

<div align="center">

**👥 Isabelle · Gabriela · Pedro · Laura**

Feito para a disciplina de Verificação e Validação de Software · PUCPR

</div>
