package v002.modelo.mapa;

public class Zona {
    private int nivelSuciedad;

    public Zona() {
        this.nivelSuciedad = 0;
    }

    public void ensuciar() {
        if (nivelSuciedad < 4) {
            nivelSuciedad++;
        }
    }

    public void limpiar() {
        if (nivelSuciedad > 0) {
            nivelSuciedad--;
        }
    }

    public int getNivelSuciedad() {
        return nivelSuciedad;
    }

    public boolean tieneMueble() {
        return false;
    }
}