import Telas.Listagem;
import Paths.Caminhos;
import Telas.TelaCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ClienteMain extends JFrame {
    private Listagem listagem;

    public ClienteMain() {
        // Carrega o inventário
        try {
            listagem = Listagem.carregarInventario(Caminhos.INVENTARIO_FILE);
        } catch (IOException e) {
            listagem = new Listagem();
        }

        // Define o look and feel Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurações da janela principal
        setTitle("Java Café - Cliente");
        setSize(300, 80);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do painel de botões
        JPanel panel = new JPanel();
        JButton clienteButton = new JButton("Abrir Tela Cliente");
        JButton sairButton = new JButton("Encerrar");

        // Ações dos botões
        clienteButton.addActionListener(e -> abrirTelaCliente());
        sairButton.addActionListener(e -> encerrarAplicacao());

        panel.add(clienteButton);
        panel.add(sairButton);

        add(panel, BorderLayout.CENTER);

        // Centraliza a janela na tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 8);

        // Salva o inventário ao fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    listagem.salvarInv(Caminhos.INVENTARIO_FILE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                super.windowClosing(e);
            }
        });

        setVisible(true);
    }

    private void abrirTelaCliente() {
        SwingUtilities.invokeLater(() -> new TelaCliente(listagem));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteMain::new);
    }

    private void encerrarAplicacao() {
        System.exit(0);
    }
}
