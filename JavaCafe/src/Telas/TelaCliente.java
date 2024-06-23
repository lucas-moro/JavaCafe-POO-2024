package Telas;

import Paths.Caminhos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Classe que representa a interface do cliente
public class TelaCliente extends JFrame {
    private static Ordem ordemAtual;
    private static JTable tabelaPedido;
    private static DefaultTableModel tabelaModel;
    private static JLabel totalLabel;
    private static JComboBox<String> comboProdutos;

    // Construtor que inicializa a tela do cliente
    public TelaCliente(Listagem listagem) {
        ordemAtual = new Ordem();

        setTitle("Comprador");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        comboProdutos = new JComboBox<>();
        atualizarComboProdutos(listagem);

        if (comboProdutos.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Desculpe, guarde um momento.", "Produtos indisponiveis", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        JTextField quantidadeField = new JTextField(5);
        JButton adicionarButton = new JButton("Adicionar ao Pedido");
        JButton finalizarButton = new JButton("Finalizar Pedido");
        JButton sairButton = new JButton("Sair");

        tabelaModel = new DefaultTableModel(new Object[]{"Produto", "Quantidade", "Valor", ""}, 0);
        tabelaPedido = new JTable(tabelaModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaPedido.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tabelaPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tabelaPedido.rowAtPoint(e.getPoint());
                int column = tabelaPedido.columnAtPoint(e.getPoint());
                if (column == 3) {
                    String nomeProduto = (String) tabelaModel.getValueAt(row, 0);
                    int quantidade = (int) tabelaModel.getValueAt(row, 1);
                    int confirm = JOptionPane.showConfirmDialog(null, "Você realmente deseja remover " + quantidade + " unidades do produto " + nomeProduto + "?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        ordemAtual.removerItem(nomeProduto, quantidade);
                        atualizarAreaPedido();
                        TelaGerente telaGerente = TelaGerente.getInstance();
                        if (telaGerente != null) {
                            telaGerente.atualizarTabelaEstoque();
                        }
                    }
                }
            }
        });

        adicionarButton.addActionListener(e -> {
            String nomeProduto = (String) comboProdutos.getSelectedItem();
            int quantidade = Integer.parseInt(quantidadeField.getText());
            Produto produto = listagem.pesquisarProduto(nomeProduto);
            if (produto != null) {
                ordemAtual.addItem(produto, quantidade);
                atualizarAreaPedido();
                TelaGerente telaGerente = TelaGerente.getInstance();
                if (telaGerente != null) {
                    telaGerente.atualizarTabelaEstoque();
                }
            }
        });

        finalizarButton.addActionListener(e -> {
            if (ordemAtual.calTotal() > 0) {
                double total = ordemAtual.calTotal();
                JOptionPane.showMessageDialog(TelaCliente.this, "Pedido realizado com sucesso! Total: R$ " + String.format("%.2f", total));
                try {
                    ordemAtual.salvarPedido(Caminhos.PEDIDOS_FILE);
                    listagem.salvarInv(Caminhos.INVENTARIO_FILE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ordemAtual.finalizarPedido();
                ordemAtual = new Ordem();
                atualizarAreaPedido();
            } else {
                JOptionPane.showMessageDialog(TelaCliente.this, "Nenhum produto foi selecionado! Adicione um produto ou aperte em 'Sair'", "Houve um Erro", JOptionPane.WARNING_MESSAGE);
            }
        });

        sairButton.addActionListener(e -> {
            dispose();
        });

        topPanel.add(new JLabel("Produto:"));
        topPanel.add(comboProdutos);
        topPanel.add(new JLabel("Quantidade:"));
        topPanel.add(quantidadeField);
        topPanel.add(adicionarButton);
        topPanel.add(finalizarButton);
        topPanel.add(sairButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total: R$ 0.00");
        totalLabel.setFont(new Font("Serif", Font.BOLD, 16));
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(finalizarButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(tabelaPedido), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setVisible(true);
    }

    // Atualiza o combo que exibe os produtos caso algum seja inserido ou removido
    public static void atualizarComboProdutos(Listagem listagem) {
        if (comboProdutos != null) {
            comboProdutos.removeAllItems();
            for (Produto p : listagem.getProdutos().values()) {
                comboProdutos.addItem(p.getNome());
            }
        }
    }

    // Atualiza a área que exibe os itens do pedido
    private void atualizarAreaPedido() {
        tabelaModel.setRowCount(0);

        Map<String, Integer> quantidadeMap = new HashMap<>();
        Map<String, Double> valorMap = new HashMap<>();

        for (Produto p : ordemAtual.getItens()) {
            quantidadeMap.put(p.getNome(), quantidadeMap.getOrDefault(p.getNome(), 0) + 1);
            valorMap.put(p.getNome(), valorMap.getOrDefault(p.getNome(), 0.0) + p.getPreco());
        }

        for (String nome : quantidadeMap.keySet()) {
            tabelaModel.addRow(new Object[]{
                    nome, quantidadeMap.get(nome), String.format("R$ %.2f", valorMap.get(nome)), "REMOVER"
            });
        }

        totalLabel.setText("Total: R$ " + String.format("%.2f", ordemAtual.calTotal()));
    }
}
