package src;

import java.util.List;

public class Celda {
    private List<Producto> productos;
    private double precio;
    private int cantidadDisponible;
    private int capacidad;
    private int idMaquina;

    public Celda(List<Producto> productos, double precio, int cantidadDisponible, int capacidad, int idMaquina) {
        this.productos = productos;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.capacidad = capacidad;
        this.idMaquina = idMaquina;
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
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public int getIdMaquina() {
        return idMaquina;
    }
    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }
}
