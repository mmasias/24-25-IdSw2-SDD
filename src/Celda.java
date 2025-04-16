package src;

import java.util.List;

public class Celda {
    private List<Producto> productos;
    private double precio;
    private int cantidad_disponible;
    private int capacidad;
    private int id_maquina;

    public Celda(List<Producto> productos, double precio, int cantidad_disponible, int capacidad, int id_maquina) {
        this.productos = productos;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
        this.capacidad = capacidad;
        this.id_maquina = id_maquina;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidad_disponible() {
        return cantidad_disponible;
    }
    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public int getId_maquina() {
        return id_maquina;
    }
    public void setId_maquina(int id_maquina) {
        this.id_maquina = id_maquina;
    }
}
