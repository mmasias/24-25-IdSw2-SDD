public class Ataque {
    private int daño;
    private double porcentajeExito;

    public Ataque(int daño, double porcentajeExito) {
        this.daño = daño;
        this.porcentajeExito = porcentajeExito;
    }

    public int getDaño() {
        return daño;
    }

    public double getPorcentajeExito() {
        return porcentajeExito;
    }

    public boolean esExitoso() {
        // Generamos un número aleatorio entre 0 y 1
        double random = Math.random();
        // Si el número es menor que el porcentaje de éxito, el ataque es exitoso
        return random < (porcentajeExito / 100.0);
    }
} 