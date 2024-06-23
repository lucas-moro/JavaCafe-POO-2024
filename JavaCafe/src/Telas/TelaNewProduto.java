package Telas;

import Paths.Caminhos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Classe que representa a tela de cadastro de novos produtos
public class TelaNewProduto extends JFrame {
    private TelaGerente telaGerente;
    private Listagem listagem;

    /**
     * Construtor da classe TelaNewProduto
     * @param telaGerente A instância da tela de gerente para atualização da tabela de estoque
     * @param listagem O inventário do Java Café
     */
    public TelaNewProduto(TelaGerente telaGerente, Listagem listagem) {
        this.telaGerente = telaGerente;
        this.listagem = listagem;

        // Configuração da tela inicial
        setTitle("Adicionar / Atualizar Produto");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuração do painel
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
                    JOptionPane.showMessageDialog(TelaNewProduto.this, "Por favor, insira valores válidos. O preço deve ser um número com ponto (.) e a quantidade deve ser um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cria um novo objeto Produto com os dados fornecidos
                Produto produto = new Produto(nome, preco, quantidade);
                listagem.addProduto(produto); // Adiciona o produto à listagem
                JOptionPane.showMessageDialog(TelaNewProduto.this, "Produto cadastrado/atualizado com sucesso!");

                try {
                    listagem.salvarInv(Caminhos.INVENTARIO_FILE); // Salva o inventário atualizado
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                telaGerente.atualizarTabelaEstoque(); // Atualiza a tabela de estoque na tela de gerente
                TelaCliente.atualizarComboProdutos(listagem); // Atualiza o combo de produtos na tela de cliente
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

        add(panel, BorderLayout.CENTER);

        // Centraliza o frame na tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        setVisible(true);
    }
}
