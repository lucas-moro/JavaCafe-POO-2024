package Interfaces;
import java.io.IOException;
import java.util.List;
import Telas.Produto;

public interface PedidoIU {
    public void salvarPedido(String caminhoArquivo) throws IOException;
    public List<Produto> getItens();
    public double calTotal();
    public void finalizarPedido();
    public void addItem(Produto produto, int quantidade);
}