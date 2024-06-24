/**
 * Classe principal que inicia a aplicação do cliente.
 */
package Main;

import Telas.Listagem;
import Paths.Caminhos;
import Telas.TelaCliente;

import javax.swing.*;
import java.io.IOException;

public class ClienteMain{
    private Listagem listagem;

    /**
     * Construtor da classe ClienteMain.
     * Carrega o inventário da lista de produtos.
     * Se ocorrer um erro de leitura, cria uma nova lista vazia.
     * Define o look and feel Nimbus para a aplicação.
     * Abre a tela do cliente.
     */
    public ClienteMain() {
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

        // Abre a tela do cliente
        abrirTelaCliente();
    }

    /**
     * Método privado que abre a tela do cliente.
     * Utiliza a classe SwingUtilities para garantir que a tela seja aberta na thread de despacho de eventos.
     */
    private void abrirTelaCliente() {
        SwingUtilities.invokeLater(() -> new TelaCliente(listagem));
    }

    /**
     * Método principal que inicia a aplicação.
     * Utiliza a classe SwingUtilities para garantir que a aplicação seja iniciada na thread de despacho de eventos.
     * @param args argumentos da linha de comando (não utilizado)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteMain::new);
    }
}
