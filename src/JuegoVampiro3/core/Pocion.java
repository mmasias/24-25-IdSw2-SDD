package JuegoVampiro3.core;

public class Pocion {
    private int turnosParaEfecto;
    private boolean enUso;
    private int turnosRestantes;
    
    private static final int TURNOS_TOTALES = 3;

    public Pocion(int energiaMaximaPersonaje) {
        this.turnosParaEfecto = TURNOS_TOTALES;
        this.enUso = false;
        this.turnosRestantes = 0;
    }

    public boolean EstaEnUso() {
        return enUso;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void beber() {
        if (!enUso) {
            this.enUso = true;
            this.turnosRestantes = turnosParaEfecto;
        }
    }

    public boolean avanzarTurno() {
        if (enUso) {
            turnosRestantes--;
            if (turnosRestantes <= 0) {
                enUso = false;
                return true;
            }
        }
        return false;
    }

    public void resetear() {
        this.enUso = false;
        this.turnosRestantes = 0;
    }
} 
