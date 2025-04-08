public class Zona {
    private int nivelSuciedad;
    private Mueble mueble;

    public Zona() {
        this.nivelSuciedad = 0;
        this.mueble = null;
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

    public boolean tieneMueble() {
        return mueble != null;
    }

    public int getNivelSuciedad() {
        return nivelSuciedad;
    }

    public void colocarMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    public void removerMueble() {
        this.mueble = null;
    }
}
