# Sistema de Gerenciamento de Estoque – Loja de Roupas

## 1. Sobre o projeto

O **Sistema de Gerenciamento de Estoque** é um projeto acadêmico desenvolvido na disciplina de Verificação e Validação de Software, utilizando as práticas de desenvolvimento **BDD (Behavior Driven Development)** e **TDD (Test Driven Development)**.

O objetivo do sistema é facilitar o controle de produtos de uma loja de roupas, permitindo cadastrar, editar, excluir e consultar produtos, além de controlar entradas e saídas de mercadorias e acompanhar a quantidade disponível em estoque.

O projeto será desenvolvido em Java, utilizando o IntelliJ IDEA, com testes automatizados para verificar o funcionamento das funcionalidades implementadas.

## 2. Objetivos

- Desenvolver um sistema simples de gerenciamento de estoque.
- Aplicar BDD para descrever os comportamentos esperados do sistema.
- Aplicar TDD para orientar o desenvolvimento por meio de testes automatizados.
- Criar testes para situações positivas e negativas.
- Trabalhar com versionamento de código utilizando Git e GitHub.
- Documentar as etapas de desenvolvimento com evidências.

## 3. Tecnologias utilizadas

| Tecnologia    | Finalidade                              |
| ------------- | --------------------------------------- |
| Java          | Linguagem de programação                |
| IntelliJ IDEA | Ambiente de desenvolvimento             |
| Maven         | Gerenciamento do projeto e dependências |
| JUnit 5       | Testes automatizados (TDD)              |
| Cucumber      | Implementação dos cenários BDD          |
| Gherkin       | Escrita dos cenários de comportamento   |
| Git e GitHub  | Versionamento e integração do código    |

## 4. Funcionalidades do sistema

O desenvolvimento será dividido entre quatro integrantes, com responsabilidades específicas.

### Integrante 1 – Isa

1. Cadastrar produto.
2. Excluir produto.
3. Adicionar produto cadastrado ao estoque (entrada).

### Integrante 2 – Gabi

1. Editar informações do produto.
2. Listar todos os produtos.
3. Remover produto cadastrado do estoque (saída).

### Integrante 3 – Pedro

1. Registrar entrada de produtos (log).
2. Registrar saída de produtos (log).

### Integrante 4 – Laura

1. Buscar produtos pelo nome.
2. Consultar produtos com estoque baixo.

## 5. Estrutura do projeto

O projeto seguirá uma organização baseada na separação de responsabilidades, facilitando a manutenção e o desenvolvimento em equipe.

```text
SistemaEstoque/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/pucpr/sistemaestoque/
│   │           ├── model/
│   │           │   ├── Produto.java
│   │           │   └── Movimentacao.java
│   │           │
│   │           ├── service/
│   │           │   ├── ProdutoService.java
│   │           │   ├── EstoqueService.java
│   │           │   └── MovimentacaoService.java
│   │           │
│   │           └── repository/
│   │               ├── ProdutoRepository.java
│   │               └── MovimentacaoRepository.java
│   │
│   └── test/
│       ├── java/
│       │   └── br/pucpr/sistemaestoque/
│       │       ├── tests/
│       │       ├── steps/
│       │       └── runners/
│       │
│       └── resources/
│           └── features/
│               ├── cadastro.feature
│               ├── edicao.feature
│               ├── exclusao.feature
│               ├── listagem.feature
│               ├── movimentacao.feature
│               └── consulta.feature
│
├── evidencias/
│   ├── isa/
│   │   ├── RED/
│   │   ├── GREEN/
│   │   ├── REFACTOR/
│   │   └── BDD/
│   │
│   ├── gabi/
│   │   ├── RED/
│   │   ├── GREEN/
│   │   ├── REFACTOR/
│   │   └── BDD/
│   │
│   ├── pedro/
│   │   ├── RED/
│   │   ├── GREEN/
│   │   ├── REFACTOR/
│   │   └── BDD/
│   │
│   └── laura/
│       ├── RED/
│       ├── GREEN/
│       ├── REFACTOR/
│       └── BDD/
│
├── pom.xml
└── README.md
```

**Responsabilidade de cada pasta:**

- `model`: contém as classes que representam os dados do sistema.
- `service`: contém as regras de negócio e a implementação das funcionalidades.
- `repository`: responsável pelo armazenamento e recuperação dos dados.
- `tests`: contém os testes unitários desenvolvidos com JUnit.
- `steps`: contém as implementações dos passos dos cenários Cucumber.
- `runners`: responsáveis pela execução dos testes BDD.
- `features`: contém os arquivos `.feature`, escritos em Gherkin.
- `evidencias`: armazena os prints das etapas do desenvolvimento.

*Observação: a estrutura poderá receber novas classes conforme a necessidade do desenvolvimento, mantendo o padrão de organização.*

## 6. Características dos produtos

Cada produto cadastrado no sistema deverá possuir os seguintes atributos:

| Atributo       | Descrição                                      |
| -------------- | ---------------------------------------------- |
| ID             | Identificador único do produto                 |
| Nome           | Nome do produto                                |
| Categoria      | Tipo de roupa (calça, blusa, vestido etc.)     |
| Tamanho        | Tamanho da peça (P, M, G, GG)                  |
| Cor            | Cor do produto                                 |
| Preço          | Valor de venda do produto                      |
| Quantidade     | Quantidade disponível em estoque               |
| Estoque mínimo | Quantidade mínima para alerta de estoque baixo |

Também será utilizada uma classe `Movimentacao`, responsável por representar as operações realizadas no estoque.

Cada movimentação deverá conter:

- Identificador da movimentação.
- Código do produto.
- Tipo da movimentação (entrada ou saída).
- Quantidade movimentada.
- Data da movimentação.

## 7. Regras de negócio

O sistema deverá respeitar as seguintes regras:

1. Todo produto deverá possuir os campos obrigatórios preenchidos.
2. Não será permitido cadastrar dois produtos com o mesmo código.
3. O preço deverá ser maior que zero.
4. A quantidade inicial em estoque não poderá ser negativa.
5. Não será permitido registrar uma saída maior que a quantidade disponível.
6. As entradas deverão aumentar a quantidade disponível.
7. As saídas deverão diminuir a quantidade disponível.
8. Toda entrada ou saída deverá gerar um registro no histórico de movimentações.
9. O sistema deverá permitir consultar produtos pelo nome.
10. O sistema deverá identificar produtos com quantidade menor ou igual ao estoque mínimo.
11. Não será permitido editar ou excluir um produto inexistente.

## 8. Aplicação do TDD

O desenvolvimento das funcionalidades deverá seguir o ciclo TDD, composto por três etapas:

### RED – Teste falhando

- Criar um teste automatizado antes da implementação.
- Executar o teste.
- Verificar que o teste falhou, pois a funcionalidade ainda não foi implementada.
- Registrar um print da execução.

### GREEN – Teste passando

- Implementar o código necessário para atender ao teste.
- Executar novamente o teste.
- Verificar que o resultado foi aprovado.
- Registrar um print da execução.

### REFACTOR – Refatoração

- Melhorar e organizar o código.
- Eliminar possíveis repetições.
- Executar novamente os testes.
- Verificar se todos continuam passando.
- Registrar um print da execução.

Cada integrante deverá aplicar o ciclo nas funcionalidades sob sua responsabilidade.

## 9. Aplicação do BDD

Os cenários de comportamento serão escritos utilizando Gherkin, seguindo a estrutura:

- **Dado (Given):** apresenta a situação inicial.
- **Quando (When):** representa a ação realizada.
- **Então (Then):** descreve o resultado esperado.

Os cenários deverão contemplar situações positivas e negativas, verificando se o sistema apresenta os comportamentos esperados.

Exemplo:

```gherkin
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

Os arquivos `.feature` ficarão na pasta `src/test/resources/features`.

## 10. Organização do GitHub e branches

Para organizar o desenvolvimento em equipe, cada integrante deverá trabalhar em sua própria branch.

| Branch          | Responsável                 |
| --------------- | --------------------------- |
| `main`          | Versão principal do projeto |
| `feature/isa`   | Isa                         |
| `feature/gabi`  | Gabi                        |
| `feature/pedro` | Pedro                       |
| `feature/laura` | Laura                       |

### Fluxo de desenvolvimento

1. Baixar o projeto do GitHub.
2. Acessar ou criar a branch correspondente ao integrante.
3. Desenvolver as funcionalidades na própria branch.
4. Criar e executar os testes JUnit.
5. Desenvolver e executar os cenários BDD.
6. Registrar as evidências das etapas RED, GREEN e REFACTOR.
7. Realizar commits com mensagens que descrevam as alterações.
8. Enviar as alterações para a própria branch.
9. Criar um Pull Request para a `main` após finalizar e testar as funcionalidades.
10. Revisar e integrar as alterações ao projeto principal.

**Importante:** não realizar alterações diretamente na `main`. A integração deverá acontecer somente depois que cada integrante finalizar e testar sua parte na própria branch.

### Exemplos de comandos Git

```bash
# Baixar o projeto
git clone URL_DO_REPOSITORIO

# Entrar na pasta
cd SistemaEstoque

# Criar uma branch
git checkout -b feature/seu-nome

# Adicionar alterações
git add .

# Criar commit
git commit -m "Implementa funcionalidade de cadastro"

# Enviar para o GitHub
git push -u origin feature/seu-nome
```

## 11. Evidências e documentação

Cada integrante deverá documentar o desenvolvimento das suas funcionalidades.

As evidências deverão conter:

| Etapa    | Evidência necessária                                    |
| -------- | ------------------------------------------------------- |
| RED      | Print do teste falhando                                 |
| GREEN    | Print do teste passando após a implementação            |
| REFACTOR | Print do código refatorado e dos testes passando        |
| BDD      | Print dos cenários Gherkin e dos resultados da execução |

Cada imagem deverá possuir uma descrição explicando o que foi realizado e qual resultado foi obtido.

As evidências deverão ser organizadas nas pastas individuais dentro de `evidencias/`.

## 12. Entregáveis

Ao final do projeto, deverão ser entregues:

- Código-fonte completo do sistema.
- Casos de testes BDD.
- Testes automatizados desenvolvidos com JUnit.
- Relatório documentando os passos do TDD.
- Evidências com imagens e descrições das etapas RED, GREEN e REFACTOR.
- Evidências da execução dos cenários BDD.
- Repositório GitHub contendo o projeto integrado.

## 13. Considerações finais

O projeto busca aplicar, na prática, os conceitos de Verificação e Validação de Software, permitindo que os integrantes desenvolvam um sistema funcional e compreendam a importância dos testes durante o processo de desenvolvimento.

A divisão das funcionalidades, a utilização de branches individuais e a documentação das evidências têm como objetivo facilitar o trabalho em equipe e garantir que todos participem das etapas de implementação e validação do sistema.
