package model;

public class Plato {
    private String nombre;
    private double precio;
    private int tiempoPreparacion;

    public Plato(String nombre, double precio, int tiempoPreparacion) {
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " (Tiempo: " + tiempoPreparacion + " min)";
    }
}
