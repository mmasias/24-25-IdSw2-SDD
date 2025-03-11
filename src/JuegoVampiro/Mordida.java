public class Mordida extends Ataque {
    private String nombre;

    public Mordida(String nombre, int daño, double porcentajeExito) {
        super(daño, porcentajeExito);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (Daño: " + getDaño() + ", Probabilidad: " + getPorcentajeExito() + "%)";
    }
} 