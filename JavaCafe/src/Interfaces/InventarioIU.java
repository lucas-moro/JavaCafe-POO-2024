package Interfaces;
import java.io.IOException;
import Telas.Produto;

// Interface que define as operações básicas de um inventário
public interface InventarioIU {
    public void deletarProduto(String nomeProduto);
    public Produto pesquisarProduto(String nome);
    public void salvarInv(String caminhoArquivo) throws IOException;
    public void addProduto(Produto produto);

}