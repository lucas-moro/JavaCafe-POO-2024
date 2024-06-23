package Telas;

import Interfaces.PedidoIU;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Ordem implements PedidoIU, Serializable {
    private static final long serialVersionUID = 1L;
    private List<Produto> itens;
    private double total;

    // Construtor que inicializa a lista de itens e o total
    public Ordem() {
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    // Adiciona um item ao pedido
    @Override
    public void addItem(Produto produto, int quantidade) {
        if (produto.quantidadePegar() >= quantidade) {
            produto.quantidadeDefinir(produto.quantidadePegar() - quantidade);
            for (int i = 0; i < quantidade; i++) {
                itens.add(produto);
            }
            total += produto.valor() * quantidade;
        } else {
            JOptionPane.showMessageDialog(null, "Nao sera possivel, temos apenas " + produto.quantidadePegar() + " unidades \n no estoque para o produto " + produto.nomeGet(), "Estoque Insuficiente", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Remove um item do pedido
    public void removerItem(String nomeProduto, int quantidade) {
        int count = 0;
        for (int i = itens.size() - 1; i >= 0; i--) {
            Produto p = itens.get(i);
            if (p.nomeGet().equals(nomeProduto) && count < quantidade) {
                itens.remove(i);
                p.quantidadeDefinir(p.quantidadePegar() + 1);
                total -= p.valor();
                count++;
            }
        }
        if (count < quantidade) {
            JOptionPane.showMessageDialog(null, "A quantidade para remoção excede a quantidade no pedido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Retorna a lista de itens do pedido
    @Override
    public List<Produto> getItens() {
        return itens;
    }

    // Calcula e retorna o total do pedido
    @Override
    public double calTotal() {
        return total;
    }

    // Finaliza o pedido (exibe o total no console)
    @Override
    public void finalizarPedido() {
        System.out.println("Pedido finalizado. Total: R$ " + calTotal());
    }

    // Salva os detalhes do pedido em um arquivo
    @Override
    public void salvarPedido(String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            writer.write("Data do Pedido: " + sdf.format(new Date()));
            writer.newLine();
            writer.newLine();

            // Agrupa itens por nome para evitar duplicatas
            Map<String, Integer> quantidadeMap = new HashMap<>();
            Map<String, Double> precoMap = new HashMap<>();

            for (Produto p : itens) {
                quantidadeMap.put(p.nomeGet(), quantidadeMap.getOrDefault(p.nomeGet(), 0) + 1);
                precoMap.put(p.nomeGet(), p.valor());
            }

            for (String nome : quantidadeMap.keySet()) {
                writer.write(String.format("Produto: " + nome + " | Preço Unitário: " + precoMap.get(nome) + " | Quantidade: " + quantidadeMap.get(nome)));
                writer.newLine();
            }

            writer.newLine();
            writer.write("VALOR             R$ " + String.format("%.2f", total));
            writer.newLine();
            writer.newLine();
            writer.write("-------------------------------------------------------");
            writer.newLine();
            writer.newLine();
        }
    }

    // Retorna a representação em string do pedido
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido:\n");
        for (Produto p : itens) {
            sb.append(p.nomeGet()).append(" - R$ ").append(p.valor()).append("\n");
        }
        return sb.toString();
    }
}
