/**
 * Classe que define os caminhos para os arquivos de pedidos e inventários do café.
 */
package Paths;

import java.nio.file.Paths;

public class Caminhos {
    // Caminho para o arquivo de inventário
    public static final String INVENTARIO_FILE = Caminhos.class.getResource("/Dados/inventario.txt").getPath();

    // Caminho para o arquivo de pedidos
    public static final String PEDIDOS_FILE = Caminhos.class.getResource("/Dados/pedidos.txt").getPath();
}
