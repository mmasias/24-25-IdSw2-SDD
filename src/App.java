package src;

import src.moduloMaquina.Maquina;
import src.moduloInventario.Celda;
import src.moduloInventario.Producto;
import src.moduloCaja.Caja;
import src.moduloPago.Efectivo;
import src.moduloPago.Tarjeta;
import src.moduloUsuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class App {
     public static void main(String[] args) {
        Producto coca = new Producto("Coca-Cola", 1.50);
        Producto papas = new Producto("Papas Fritas", 1.00);
        Producto agua = new Producto("Agua", 1.00);

        Celda celda1 = new Celda(coca, 5);
        Celda celda2 = new Celda(papas, 3);
        Celda celda3 = new Celda(agua, 7);

        List<Celda> celdas = new ArrayList<>();
        celdas.add(celda1);
        celdas.add(celda2);
        celdas.add(celda3);

        Caja caja = new Caja(10.0);

        Efectivo efectivo = new Efectivo(5.0, caja);
        Tarjeta tarjeta = new Tarjeta("123456789", "Juan Perez", 10.0);
        Usuario usuario = new Usuario(efectivo, tarjeta);

        Maquina maquina = new Maquina(celdas, caja);

        maquina.iniciarInterfazUsuario(usuario);
    }
}
