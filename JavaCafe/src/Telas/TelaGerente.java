package Telas;

import Paths.Caminhos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Classe que representa a interface do gerente
public class TelaGerente extends JFrame {
    private static TelaGerente instance;
    private Listagem listagem;
    private DefaultTableModel tabelaModel;
    private JTable tabelaEstoque;

    /**
     * Construtor da classe TelaGerente
     * @param listagem O inventário do Java Café
     */
    public TelaGerente(Listagem listagem) {
        this.listagem = listagem;
        instance = this;

        // Configuração da tela inicial
        setTitle("Administrador");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuração da tabela de estoque
        tabelaModel = new DefaultTableModel(new Object[]{"Produto", "Preço", "Quantidade", ""}, 0);
        tabelaEstoque = new JTable(tabelaModel);
        atualizarTabelaEstoque();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaEstoque.getColumnCount(); i++) {
            tabelaEstoque.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Configuração do evento de clique na tabela de estoque
        tabelaEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tabelaEstoque.rowAtPoint(e.getPoint());
                int column = tabelaEstoque.columnAtPoint(e.getPoint());
                if (column == 3) { // Coluna "Excluir"
                    String nomeProduto = (String) tabelaModel.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir o produto " + nomeProduto + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        listagem.deletarProduto(nomeProduto);
                        atualizarTabelaEstoque();
                        TelaCliente.atualizarComboProdutos(listagem);
                    }
                }
            }
        });

        // Configuração do painel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tabelaEstoque), BorderLayout.CENTER);

        // Configuração dos botões no painel
        JButton cadastrarButton = new JButton("Adicionar / Atualizar Produto");
        cadastrarButton.addActionListener(e -> abrirTelaCadastro());
        JButton relatorioButton = new JButton("Relatório de Vendas");
        relatorioButton.addActionListener(e -> exibirRelatorioVendas());
        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(e -> dispose()); // Fecha a janela atual

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(relatorioButton);
        topPanel.add(cadastrarButton);
        topPanel.add(sairButton);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // Centraliza o frame na tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width - getWidth(), screenSize.height - getHeight() - 50);
        setVisible(true);

        // Verifica os estoques para exibir o alerta
        verificarEstoqueBaixo();
    }

    /**
     * Abre a tela de cadastro de produtos.
     */
    private void abrirTelaCadastro() {
        new TelaNewProduto(this, listagem);
    }

    /**
     * Exibe o relatório de vendas em uma nova janela.
     */
    private void exibirRelatorioVendas() {
        JFrame relatorioFrame = new JFrame("Relatório");
        relatorioFrame.setSize(320, 400);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        try (BufferedReader reader = new BufferedReader(new FileReader(Caminhos.PEDIDOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                textArea.append(linha + "\n");
            }
        } catch (IOException e) {
            textArea.append("Erro ao carregar o relatório de vendas: " + e.getMessage());
        }

        relatorioFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        relatorioFrame.setVisible(true);
        relatorioFrame.setLocationRelativeTo(this);
    }

    /**
     * Obtém a instância única de TelaGerente.
     * @return A instância de TelaGerente
     */
    public static TelaGerente getInstance() {
        return instance;
    }

    /**
     * Atualiza a tabela de estoque com os dados do inventário.
     */
    public void atualizarTabelaEstoque() {
        tabelaModel.setRowCount(0); // Limpa a tabela
        for (Produto p : listagem.getProdutos().values()) {
            tabelaModel.addRow(new Object[]{p.getNome(), p.getPreco(), p.getQuantidade(), "DELETAR"});
        }
        verificarEstoqueBaixo();
    }

    /**
     * Verifica se algum produto no inventário está com o estoque baixo e exibe um aviso.
     */
    private void verificarEstoqueBaixo() {
        for (Produto p : listagem.getProdutos().values()) {
            if (p.getQuantidade() <= 2) {
                JOptionPane.showMessageDialog(this, "O estoque do produto " + p.getNome() + " está baixo. Por favor, reponha o estoque.", "Aviso de Estoque Baixo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
