package Paths;

import java.nio.file.Paths;

//Caminhos para os arquivos de pedidos e inventários do café
public class Caminhos {
    private static final String _PATH = System.getProperty("user.dir");
    public static final String INVENTARIO_FILE = Paths.get(_PATH, "src", "Dados", "inventario.txt").toString();
    public static final String PEDIDOS_FILE = Paths.get(_PATH, "src", "Dados", "pedidos.txt").toString();
}
