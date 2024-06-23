import Telas.Listagem;
import Paths.Caminhos;
import Telas.TelaGerente;
import Telas.TelaCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// Classe principal que inicia a aplicação Java Café
public class Main extends JFrame {
    private Listagem listagem;

     // Construtor da classe Main que inicializa a aplicação Java Café.
    public Main() {
        // Carrega o inventário ou cria um novo se não existir
        try {
            listagem = Listagem.carregarInventario(Caminhos.INVENTARIO_FILE);
        } catch (IOException e) {
            listagem = new Listagem();
        }

        // Configura o Look and Feel Nimbus para melhor aparência
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Java Café");
        setSize(300, 80);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configuração dos botões na tela inicial
        JPanel panel = new JPanel();
        JButton compradorButton = new JButton("Cliente");
        JButton donoButton = new JButton("Gerente");
        JButton sairButton = new JButton("Encerrar");

        // Adiciona ações aos botões
        compradorButton.addActionListener(e -> abrirTelaComprador());
        donoButton.addActionListener(e -> autenticarDono());
        sairButton.addActionListener(e -> encerrarAplicacao());

        panel.add(compradorButton);
        panel.add(donoButton);
        panel.add(sairButton);

        add(panel, BorderLayout.CENTER);

        // Centraliza a janela na tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 8);

        // Salva o inventário ao fechar a aplicação
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

     //Abre a tela do cliente para realizar compras.
    private void abrirTelaComprador() {
        SwingUtilities.invokeLater(() -> new TelaCliente(listagem));
    }

    //Solicita a autenticação para abrir a tela de gerente.
    private void autenticarDono() {
        JPasswordField passwordField = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(this, passwordField, "Digite a senha:", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            String senha = new String(passwordField.getPassword());
            if (senha.equals("admin")) {
                abrirTelaAdmin();
            } else {
                JOptionPane.showMessageDialog(this, "Senha errada", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Abre a tela de gerente após autenticação bem-sucedida.
    private void abrirTelaAdmin() {
        SwingUtilities.invokeLater(() -> new TelaGerente(listagem));
    }

   // Método principal que inicia a aplicação Java Café.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    // Encerra a aplicação Java Café.
    private void encerrarAplicacao() {
        System.exit(0);
    }
}
