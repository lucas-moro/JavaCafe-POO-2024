package Main;

import Telas.Listagem;
import Paths.Caminhos;
import Telas.TelaGerente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GerenteMain extends Component {
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

        autenticarDono();
    }

    private void autenticarDono() {
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
}
