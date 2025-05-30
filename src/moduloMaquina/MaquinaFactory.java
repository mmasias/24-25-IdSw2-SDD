package src.moduloMaquina;

import src.moduloCaja.Caja;
import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class MaquinaFactory {
    public static List<Maquina> crearMaquinas(Caja caja1, Caja caja2) {
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

        List<Celda> celdas1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            celdas1.add(new Celda(productos[i], 5));
        }
        List<Celda> celdas2 = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            celdas2.add(new Celda(productos[i], 5));
        }

        List<Maquina> maquinas = new ArrayList<>();
        maquinas.add(new Maquina(celdas1, caja1));
        maquinas.add(new Maquina(celdas2, caja2));
        return maquinas;
    }
}