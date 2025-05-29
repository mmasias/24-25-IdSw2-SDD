package src.services;

import src.models.Celda;
import src.models.Producto;

import java.util.List;

public class InventarioService {

    public void abastecerCelda(Celda celda, Producto producto, int cantidad) {
        int espacioDisponible = celda.getCapacidad() - celda.getCantidadDisponible();
        if (cantidad <= espacioDisponible) {
            for (int i = 0; i < cantidad; i++) {
                celda.getProductos().add(producto);
            }
            celda.setCantidadDisponible(celda.getCantidadDisponible() + cantidad);
            System.out.println("Celda abastecida con " + cantidad + " unidades de " + producto.getNombre());
        } else {
            System.out.println("No hay suficiente espacio en la celda para abastecer " + cantidad + " unidades.");
        }
    }

    public void asignarPrecioCelda(Celda celda, double precio) {
        celda.setPrecio(precio);
        System.out.println("Precio asignado a la celda: €" + precio);
    }

    public void mostrarInventario(List<Celda> celdas) {
        System.out.println("Inventario de la máquina:");
        for (Celda celda : celdas) {
            System.out.println("Celda ID: " + celda.getIdMaquina());
            System.out.println("Producto: " + (celda.getProductos().isEmpty() ? "Vacío" : celda.getProductos().get(0).getNombre()));
            System.out.println("Cantidad disponible: " + celda.getCantidadDisponible());
            System.out.println("Precio: €" + celda.getPrecio());
            System.out.println("----------------------------------------");
        }
    }
}