public class Aspiradora {

    private int bolsa;
    private int capacidadMaximaBolsa = 100;
    private Bateria bateriaObj;
    private int posX, posY;

    public Aspiradora(Bateria bateria) {
        this.bateriaObj = bateria;
        this.bolsa = 0;
        this.posX = 0;
        this.posY = 0;
    }

    public void actuar(Superficie superficie) {
        if (bolsa >= capacidadMaximaBolsa) {
            System.out.println("La bolsa está llena. La aspiradora no puede seguir limpiando.");
            return;
        }

        if (bateriaObj.getCarga() <= 1) {
            buscarYRecargar(superficie);
        } else {
            moverInteligente(superficie);
            Zona zonaActual = superficie.obtenerZona(posX, posY);
            limpiar(zonaActual);
        }
    }

    private void moverInteligente(Superficie superficie) {
        int maxNivel = -1;
        int objetivoX = posX;
        int objetivoY = posY;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 25; x++) {
                Zona zona = superficie.obtenerZona(x, y);
                if (zona != null && zona.getNivelSuciedad() > maxNivel && !(zona instanceof ZonaRecarga)) {
                    maxNivel = zona.getNivelSuciedad();
                    objetivoX = x;
                    objetivoY = y;
                }
            }
        }

        if (posX < objetivoX) posX++;
        else if (posX > objetivoX) posX--;

        if (posY < objetivoY) posY++;
        else if (posY > objetivoY) posY--;
    }

    private void buscarYRecargar(Superficie superficie) {
        System.out.println("Batería baja. Buscando zona de recarga...");

        if (posX > 0) posX--;
        else if (posX < 0) posX++;

        if (posY > 0) posY--;
        else if (posY < 0) posY++;

        Zona zonaActual = superficie.obtenerZona(posX, posY);
        if (zonaActual instanceof ZonaRecarga) {
            ((ZonaRecarga) zonaActual).recargar(this);
        }
    }

    public void limpiar(Zona x) {
        if (x == null) return;

        if (bateriaObj.getCarga() > 0) {
            x.limpiar();
            bolsa++;
            consumirBateria();

            if (bolsa >= capacidadMaximaBolsa) {
                System.out.println("La bolsa está llena. Necesitas vaciarla.");
            }
        } else {
            System.out.println("La aspiradora se quedó sin batería. Necesita recargarse.");
        }
    }

    public void consumirBateria() {
        bateriaObj.consumir();
    }

    public void recargarBateria() {
        bateriaObj.recargar();
    }

    public int obtenerBolsa() {
        return bolsa;
    }

    public int obtenerCargaBateria() {
        return bateriaObj.getCarga();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
