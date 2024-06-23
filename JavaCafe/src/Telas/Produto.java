package Telas;

import Interfaces.ProdutoIU;

// Classe que representa um produto
public class Produto implements ProdutoIU {
    private int quantia;
    private String nome;
    private double preco;

    // Construtor que inicializa os atributos do produto
    public Produto(String nome, double preco, int quantia) {
        this.nome = nome;
        this.preco = preco;
        this.quantia = quantia;
    }

    // Retorna o nome do produto
    @Override
    public String nomeGet() {
        return nome;
    }

    // Retorna a quantidade em estoque do produto
    @Override
    public int quantidadePegar() {
        return quantia;
    }

    // Define a quantidade em estoque do produto
    @Override
    public void quantidadeDefinir(int quantidade) {
        this.quantia = quantidade;
    }

    // Retorna o preço do produto
    @Override
    public double valor() {
        return preco;
    }

    // Retorna uma representação em string do produto
    @Override
    public String toString() {
        return nome + " - R$ " + preco + " (Estoque: " + quantia + ")";
    }
}
