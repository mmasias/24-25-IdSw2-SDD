import java.util.Random;

public class Gato {
    private int posicionX, posicionY;
    private Mapa mapa;
    private Random random;

    public Gato(Mapa mapa) {
        this.mapa = mapa;
        this.random = new Random();
        this.posicionX = random.nextInt(mapa.getAnchoMapa());
        this.posicionY = random.nextInt(mapa.getAltoMapa());
    }

    public void moverYEnsuciar() {
        int direccion = random.nextInt(4);
        switch (direccion) {
            case 0:
                if (posicionX > 0) posicionX--;
                break;
            case 1:
                if (posicionY > 0) posicionY--;
                break;
            case 2:
                if (posicionX < mapa.getAnchoMapa() - 1) posicionX++;
                break;
            case 3:
                if (posicionY < mapa.getAltoMapa() - 1) posicionY++;
                break;
        }

        Zona zona = mapa.obtenerZona(posicionX, posicionY);
        if (zona != null) {
            if (zona.getNivelSuciedad() < 4) {
                zona.ensuciar();
            }
        }
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }
}
