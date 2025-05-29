package implementacion.vista;

import interfaces.vista.IProductoVista;

public class ProductoVista implements IProductoVista {
    @Override
    public void mostrarProducto(int id, String nombre) {
        System.out.println("Producto [" + id + "]: " + nombre);
    }
}
