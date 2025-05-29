package implementacion.modelo;

public class Plato {
    private final String nombre;
    private final double precio;
    private final int tiempoPreparacion;

    public Plato(String nombre, double precio, int tiempoPreparacion) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (tiempoPreparacion <= 0) throw new IllegalArgumentException("El tiempo debe ser mayor que cero");
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " (Tiempo: " + tiempoPreparacion + " min)";
    }
}
