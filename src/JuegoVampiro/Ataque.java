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
        double random = Math.random();
        return random < (porcentajeExito / 100.0);
    }
} 