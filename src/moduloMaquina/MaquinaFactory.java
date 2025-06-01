package src.moduloMaquina;

import src.moduloInventario.Celda;
import src.moduloInventario.Producto;
import src.moduloMaquina.modelo.Maquina;
import src.moduloCaja.Caja;

import java.util.ArrayList;
import java.util.List;

public class MaquinaFactory {
    public static List<Maquina> crearMaquinas(int numeroMaquinas) {

        Producto[] productos = {
            new Producto("Coca-Cola", 1.50),
            new Producto("Papas Fritas", 1.00),
            new Producto("Agua", 1.00),
            new Producto("Chocolate", 2.00),
            new Producto("Galletas", 1.20),
            new Producto("Jugo", 1.80),
            new Producto("Barra de Granola", 1.30),
            new Producto("Chicle", 0.50),
            new Producto("Café", 1.70),
            new Producto("Té", 1.60)
        };

        List<Maquina> maquinas = new ArrayList<>();
        for (int i = 0; i < numeroMaquinas; i++) {
            Caja caja = new Caja(50);
            List<Celda> celdas = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Producto producto = productos[j % productos.length];
                celdas.add(new Celda(producto, 5));
            }
            maquinas.add(new Maquina(celdas, caja));
        }
        return maquinas;
    }
}