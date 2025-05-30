package src.moduloInventario.vista;

import src.moduloInventario.modelo.Celda;
import src.moduloInventario.modelo.Producto;

public class vistaInventario implements IVistaInventario {

    @Override
    public void mostrarCelda(Celda celda, int indice) {
        Producto producto = celda.getProducto();
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Precio: $" + producto.getPrecio());
        System.out.println("Cantidad: " + celda.getCantidad());
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }
}
