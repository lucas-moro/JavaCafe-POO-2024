import Telas.Listagem;
import Paths.Caminhos;
import Telas.TelaGerente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GerenteMain extends JFrame {
    private Listagem listagem;

    public GerenteMain() {
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
        setTitle("Java Café - Gerente");
        setSize(300, 80);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação do painel de botões
        JPanel panel = new JPanel();
        JButton gerenteButton = new JButton("Abrir Tela Gerente");
        JButton sairButton = new JButton("Encerrar");

        // Ações dos botões
        gerenteButton.addActionListener(e -> autenticarGerente());
        sairButton.addActionListener(e -> encerrarAplicacao());

        panel.add(gerenteButton);
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

    private void autenticarGerente() {
        JPasswordField passwordField = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(this, passwordField, "Digite a senha:", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            String senha = new String(passwordField.getPassword());
            if (senha.equals("admin")) {
                abrirTelaGerente();
            } else {
                JOptionPane.showMessageDialog(this, "Senha errada", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirTelaGerente() {
        SwingUtilities.invokeLater(() -> new TelaGerente(listagem));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GerenteMain::new);
    }

    private void encerrarAplicacao() {
        System.exit(0);
    }
}
