/**
 * Classe que representa a lista de produtos.
 */
package Telas;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Listagem implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Produto> produtos;

    /**
     * Construtor padrão que inicializa o mapa de produtos.
     */
    public Listagem() {
        produtos = new HashMap<>();
    }

    /**
     * Adiciona um produto ao mapa de produtos.
     * @param produto o produto a ser adicionado
     */
    public void addProduto(Produto produto) {
        produtos.put(produto.getNome(), produto);
    }

    /**
     * Pesquisa um produto pelo nome.
     * @param nome o nome do produto a ser pesquisado
     * @return o produto encontrado ou null se não for encontrado
     */
    public Produto pesquisarProduto(String nome) {
        return produtos.get(nome);
    }

    /**
     * Deleta um produto do mapa pelo nome.
     * @param nomeProduto o nome do produto a ser deletado
     */
    public void deletarProduto(String nomeProduto) {
        produtos.remove(nomeProduto);
    }

    /**
     * Carrega o inventário de um arquivo e retorna uma instância de Listagem.
     * @param caminhoArquivo o caminho do arquivo de inventário
     * @return uma instância de Listagem com os produtos carregados
     * @throws IOException se houver um erro de leitura do arquivo
     */
    public static Listagem carregarInventario(String caminhoArquivo) throws IOException {
        Listagem listagem = new Listagem();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                double preco = Double.parseDouble(partes[1]);
                int quantidade = Integer.parseInt(partes[2]);
                listagem.addProduto(new Produto(nome, preco, quantidade));
            }
        }
        return listagem;
    }

    /**
     * Salva o inventário em um arquivo.
     * @param caminhoArquivo o caminho do arquivo onde o inventário será salvo
     * @throws IOException se houver um erro ao escrever no arquivo
     */
    public void salvarInv(String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Produto produto : produtos.values()) {
                writer.write(produto.getNome() + "," + produto.getPreco() + "," + produto.getQuantidade());
                writer.newLine();
            }
        }
    }

    /**
     * Retorna o mapa de produtos.
     * @return o mapa de produtos
     */
    public Map<String, Produto> getProdutos() {
        return produtos;
    }
}
