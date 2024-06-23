package Telas;

import Interfaces.ProdutoIU;

public class Produto implements ProdutoIU {
    private int quantia;
    private String nome;
    private float imposto;
    private double preco;

    // Construtor que inicializa os atributos do produto
    public Produto(String nome, double preco, int quantia) {
        this.nome = nome;
        this.imposto = (float) (0.1 * preco);
        this.preco = preco + imposto;
        this.quantia = quantia;
    }

    // Getters e setters
    @Override
    public String getNome() {
        return nome;
    }
    @Override
    public int getQuantidade() {
        return quantia;
    }
    @Override
    public void setQuantidade(int quantidade) {
        this.quantia = quantidade;
    }
    @Override
    public double getPreco() {
        return preco;
    }

    // Representação em string do produto
    @Override
    public String toString() {
        return nome + " - R$ " + preco + " (Estoque: " + quantia + ")";
    }
}
