package Telas;

import Paths.Caminhos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Classe que representa a interface do gerente */
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
                        //TelaCliente.atualizarComboProdutos(listagem);
                    }
                }
            }
        });

        // Configuração do painel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tabelaEstoque), BorderLayout.CENTER);

        // Configuração dos botões no painel
        JButton cadastrarButton = new JButton("Adicionar / Atualizar Produto");
        cadastrarButton.addActionListener(e -> abrirDialogoCadastro());
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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setVisible(true);

        // Verifica os estoques para exibir o alerta
        verificarEstoqueBaixo();
    }

    /**
     * Abre o diálogo de cadastro de produtos.
     */
    private void abrirDialogoCadastro() {
        JDialog dialogoCadastro = new JDialog(this, "Adicionar / Atualizar Produto", true);
        dialogoCadastro.setSize(350, 300);
        dialogoCadastro.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Configuração dos elementos no painel
        JLabel nomeLabel = new JLabel("Nome do Produto:");
        JTextField nomeField = new JTextField();
        JLabel precoLabel = new JLabel("Preço do Produto:");
        JTextField precoField = new JTextField();
        JLabel quantidadeLabel = new JLabel("Quantidade:");
        JTextField quantidadeField = new JTextField();
        JButton cadastrarButton = new JButton("Adicionar/Atualizar");

        // Escuta de ações no botão de cadastrar/atualizar
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                double preco;
                int quantidade;

                // Tentativa de converter texto para números
                try {
                    preco = Double.parseDouble(precoField.getText());
                    quantidade = Integer.parseInt(quantidadeField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialogoCadastro, "Por favor, insira valores válidos. O preço deve ser um número com ponto (.) e a quantidade deve ser um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cria um novo objeto Produto com os dados fornecidos
                Produto produto = new Produto(nome, preco, quantidade);
                listagem.addProduto(produto); // Adiciona o produto à listagem
                JOptionPane.showMessageDialog(dialogoCadastro, "Produto cadastrado/atualizado com sucesso!");

                try {
                    listagem.salvarInv(Caminhos.INVENTARIO_FILE); // Salva o inventário atualizado
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                atualizarTabelaEstoque(); // Atualiza a tabela de estoque na tela de gerente
                //TelaCliente.atualizarComboProdutos(listagem); // Atualiza o combo de produtos na tela de cliente

                dialogoCadastro.dispose(); // Fecha o diálogo de cadastro
            }
        });

        // Adiciona os elementos no painel
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(precoLabel);
        panel.add(precoField);
        panel.add(quantidadeLabel);
        panel.add(quantidadeField);
        panel.add(new JLabel()); // Espaço em branco para ajuste de layout
        panel.add(cadastrarButton);

        dialogoCadastro.add(panel, BorderLayout.CENTER);

        // Centraliza o diálogo na tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialogoCadastro.setLocation((screenSize.width - dialogoCadastro.getWidth()) / 2, (screenSize.height - dialogoCadastro.getHeight()) / 2);
        dialogoCadastro.setVisible(true);
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
            tabelaModel.addRow(new Object[]{p.getNome(), String.format("%.2f", p.getPreco()), p.getQuantidade(), "DELETAR"});
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
