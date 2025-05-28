package src.repositories;

import java.util.HashMap;
import java.util.Map;

public class ProductoRepository {
    private Map<String, Double> productos;

    public ProductoRepository() {
        this.productos = new HashMap<>();
    }

    public void agregarProducto(String nombre, double precio) {
        productos.put(nombre, precio);
    }

    public Map<String, Double> obtenerProductos() {
        return productos;
    }

    public Double obtenerPrecioProducto(String nombre) {
        return productos.get(nombre);
    }

    public boolean existeProducto(String nombre) {
        return productos.containsKey(nombre);
    }
}