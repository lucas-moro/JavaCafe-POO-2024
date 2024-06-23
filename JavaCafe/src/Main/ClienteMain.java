package Main;

import Telas.Listagem;
import Paths.Caminhos;
import Telas.TelaCliente;

import javax.swing.*;
import java.io.IOException;

public class ClienteMain{
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

        abrirTelaCliente();
    }

    private void abrirTelaCliente() {
        SwingUtilities.invokeLater(() -> new TelaCliente(listagem));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteMain::new);
    }
}
