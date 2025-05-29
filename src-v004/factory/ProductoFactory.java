package factory;

import implementacion.modelo.Producto;

public class ProductoFactory {
    public static Producto crearProducto(String tipo, int id, String nombre) {
        switch (tipo.toLowerCase()) {
            case "bebida":
                return new Producto(id, nombre + " (Bebida)");
            case "plato":
                return new Producto(id, nombre + " (Plato)");
            case "postre":
                return new Producto(id, nombre + " (Postre)");
            default:
                return new Producto(id, nombre);
        }
    }
}