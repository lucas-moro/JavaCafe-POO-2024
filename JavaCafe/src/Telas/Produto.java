/**
 * Classe que representa um produto.
 */
package Telas;

public class Produto {
    private int quantia;
    private String nome;
    private float imposto;
    private double preco;

    /**
     * Construtor que inicializa os atributos do produto.
     * Calcula o imposto como 10% do preço e adiciona ao preço total do produto.
     * @param nome o nome do produto
     * @param preco o preço do produto
     * @param quantia a quantidade em estoque do produto
     */
    public Produto(String nome, double preco, int quantia) {
        this.nome = nome;
        this.imposto = (float) (0.1 * preco);
        this.preco = preco + imposto;
        this.quantia = quantia;
    }

    /**
     * Retorna o nome do produto.
     * @return o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a quantidade em estoque do produto.
     * @return a quantidade em estoque do produto
     */
    public int getQuantidade() {
        return quantia;
    }

    /**
     * Define a quantidade em estoque do produto.
     * @param quantidade a nova quantidade em estoque do produto
     */
    public void setQuantidade(int quantidade) {
        this.quantia = quantidade;
    }

    /**
     * Retorna o preço total do produto (incluindo impostos).
     * @return o preço total do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Retorna a representação em string do produto.
     * @return a representação em string do produto
     */
    @Override
    public String toString() {
        return nome + " - R$ " + preco + " (Estoque: " + quantia + ")";
    }
}
