package src.models;

import java.util.ArrayList;
import java.util.List;

public class Celda {
    private String idMaquina;
    private int capacidad;
    private double precio;
    private int cantidadDisponible;
    private List<Producto> productos;

    public Celda(String idMaquina, int capacidad, double precio) {
        this.idMaquina = idMaquina;
        this.capacidad = capacidad;
        this.precio = precio;
        this.cantidadDisponible = 0;
        this.productos = new ArrayList<>();
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        if (cantidadDisponible < capacidad) {
            productos.add(producto);
            cantidadDisponible++;
        } else {
            throw new IllegalStateException("La celda está llena. No se pueden agregar más productos.");
        }
    }

    public Producto retirarProducto() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--;
            return productos.remove(productos.size() - 1);
        } else {
            throw new IllegalStateException("La celda está vacía. No hay productos para retirar.");
        }
    }
}