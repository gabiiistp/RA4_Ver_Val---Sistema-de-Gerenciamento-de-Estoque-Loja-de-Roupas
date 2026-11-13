package br.pucpr.sistemaestoque.model;

public class Produto {

    private String codigo;
    private String nome;
    private String categoria;
    private String tamanho;
    private String cor;
    private double preco;
    private int quantidade;
    private int estoqueMinimo;

    public Produto(String codigo, String nome, String categoria, String tamanho,
                   String cor, double preco, int quantidade, int estoqueMinimo) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.cor = cor;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getTamanho() { return tamanho; }
    public String getCor() { return cor; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public int getEstoqueMinimo() { return estoqueMinimo; }

    // Setters: a Gabi precisa deles para editar, e a entrada/saída muda a quantidade
    public void setNome(String nome) { this.nome = nome; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public void setCor(String cor) { this.cor = cor; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
}