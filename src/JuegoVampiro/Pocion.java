public class Pocion {
    private int poderCurativo;
    private int turnosRestantes;
    private boolean enUso;

    public Pocion(int poderCurativo) {
        this.poderCurativo = poderCurativo;
        this.turnosRestantes = 0;
        this.enUso = false;
    }

    public int getPoderCurativo() {
        return poderCurativo;
    }

    public boolean estaEnUso() {
        return enUso;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void beber() {
        this.enUso = true;
        this.turnosRestantes = 3; // La poción tarda 3 turnos en hacer efecto
    }

    public boolean avanzarTurno() {
        if (enUso) {
            turnosRestantes--;
            if (turnosRestantes <= 0) {
                enUso = false;
                return true; // La poción ha hecho efecto
            }
        }
        return false; // La poción aún no ha hecho efecto
    }

    public void resetear() {
        this.enUso = false;
        this.turnosRestantes = 0;
    }
} 