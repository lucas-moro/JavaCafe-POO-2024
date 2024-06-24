/**
 * Classe principal que inicia a aplicação do gerente.
 */
package Main;

import Telas.Listagem;
import Paths.Caminhos;
import Telas.TelaGerente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GerenteMain extends Component {
    private Listagem listagem;

    /**
     * Construtor da classe GerenteMain.
     * Carrega o inventário da lista de produtos.
     * Se ocorrer um erro de leitura, cria uma nova lista vazia.
     * Define o look and feel Nimbus para a aplicação.
     * Autentica o dono antes de abrir a tela do gerente.
     */
    public GerenteMain() {
        try {
            // Carrega o inventário
            listagem = Listagem.carregarInventario(Caminhos.INVENTARIO_FILE);
        } catch (IOException e) {
            listagem = new Listagem();
        }

        try {
            // Define o look and feel Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Autentica o dono
        autenticarDono();
    }

    /**
     * Método privado que autentica o dono através de uma caixa de diálogo para inserção da senha.
     * Se a senha estiver correta, abre a tela do gerente, caso contrário, exibe uma mensagem de erro.
     */
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

    /**
     * Método privado que abre a tela do gerente.
     * Utiliza a classe SwingUtilities para garantir que a tela seja aberta na thread de despacho de eventos.
     */
    private void abrirTelaGerente() {
        SwingUtilities.invokeLater(() -> new TelaGerente(listagem));
    }

    /**
     * Método principal que inicia a aplicação.
     * Utiliza a classe SwingUtilities para garantir que a aplicação seja iniciada na thread de despacho de eventos.
     * @param args argumentos da linha de comando (não utilizado)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GerenteMain::new);
    }
}
