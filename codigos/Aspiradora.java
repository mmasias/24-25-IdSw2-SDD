import java.util.*;

public class Aspiradora {

    private int bolsa;
    private final int capacidadMaximaBolsa = 10;
    private Bateria bateria;
    private int posicionX, posicionY;
    private VistaConsola vista;

    public Aspiradora(Bateria bateria, VistaConsola vista) {
        this.bateria = bateria;
        this.vista = vista;
        this.bolsa = 0;
        this.posicionX = 0;
        this.posicionY = 0;
    }

    public void actuar(Mapa mapa) {
        if (bateria.getCarga() <= 1 || bolsa >= capacidadMaximaBolsa) {
            if (bolsa >= capacidadMaximaBolsa) {
                vista.mostrarBolsaLlena();
            } else {
                vista.mostrarBateriaBaja();
            }
            buscarYRecargar(mapa);
            return;
        }

        movimientoInteligente(mapa);
        Zona zonaActual = mapa.obtenerZona(posicionX, posicionY);
        limpiar(zonaActual);
    }

    private void movimientoInteligente(Mapa mapa) {
        int maxNivel = -1;
        int objetivoX = posicionX;
        int objetivoY = posicionY;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 25; x++) {
                Zona zona = mapa.obtenerZona(x, y);
                if (zona != null && !zona.tieneMueble() && zona.getNivelSuciedad() > maxNivel
                        && !(zona instanceof ZonaDeRecarga)) {
                    maxNivel = zona.getNivelSuciedad();
                    objetivoX = x;
                    objetivoY = y;
                }
            }
        }

        if (maxNivel == 0)
            return;

        List<int[]> ruta = calcularRuta(mapa, objetivoX, objetivoY);
        if (ruta != null && !ruta.isEmpty()) {
            int[] siguientePaso = ruta.get(0);
            posicionX = siguientePaso[0];
            posicionY = siguientePaso[1];
        } else {
            vista.mostrarZonaSuciaInaccesible();
        }
    }

    private List<int[]> calcularRuta(Mapa mapa, int destinoX, int destinoY) {
        boolean[][] visitado = new boolean[10][25];
        int[][][] padre = new int[10][25][2];
        Queue<int[]> cola = new LinkedList<>();
        cola.add(new int[] { posicionX, posicionY });
        visitado[posicionY][posicionX] = true;

        int[][] direcciones = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        while (!cola.isEmpty()) {
            int[] actual = cola.poll();
            int x = actual[0];
            int y = actual[1];

            if (x == destinoX && y == destinoY)
                break;

            for (int[] d : direcciones) {
                int nx = x + d[0];
                int ny = y + d[1];
                Zona z = mapa.obtenerZona(nx, ny);

                if (z != null && !visitado[ny][nx] && !z.tieneMueble()) {
                    visitado[ny][nx] = true;
                    padre[ny][nx][0] = x;
                    padre[ny][nx][1] = y;
                    cola.add(new int[] { nx, ny });
                }
            }
        }

        if (!visitado[destinoY][destinoX])
            return null;

        LinkedList<int[]> camino = new LinkedList<>();
        int coordenadaXActual = destinoX;
        int coordenadaYActual = destinoY;

        while (coordenadaXActual != posicionX || coordenadaYActual != posicionY) {
            camino.addFirst(new int[] { coordenadaXActual, coordenadaYActual });
            int px = padre[coordenadaYActual][coordenadaXActual][0];
            int py = padre[coordenadaYActual][coordenadaXActual][1];
            coordenadaXActual = px;
            coordenadaYActual = py;
        }

        return camino;
    }

    private void buscarYRecargar(Mapa mapa) {
        int destinoX = -1, destinoY = -1;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 25; x++) {
                Zona zona = mapa.obtenerZona(x, y);
                if (esZonaDeRecarga(zona)) {
                    destinoX = x;
                    destinoY = y;
                    break;
                }
            }
            if (destinoX != -1)
                break;
        }

        if (destinoX == -1) {
            vista.mostrarZonaDeRecargaNoEncontrada();
            return;
        }

        moverHaciaZona(mapa, destinoX, destinoY);
    }

    private boolean esZonaDeRecarga(Zona zona) {
        return zona instanceof ZonaDeRecarga;
    }

    private void moverHaciaZona(Mapa mapa, int destinoX, int destinoY) {
        List<int[]> ruta = calcularRuta(mapa, destinoX, destinoY);
        if (ruta != null && !ruta.isEmpty()) {
            int[] siguientePaso = ruta.get(0);
            posicionX = siguientePaso[0];
            posicionY = siguientePaso[1];

            Zona zonaActual = mapa.obtenerZona(posicionX, posicionY);
            if (zonaActual instanceof ZonaDeRecarga) {
                ((ZonaDeRecarga) zonaActual).recargar(this);
                if (bolsa == capacidadMaximaBolsa) {
                    vaciarBolsa();
                }
            }
        } else {
            vista.mostrarZonaDeRecargaInaccesible();
        }
    }

    public void vaciarBolsa() {
        vista.mostrarVaciadoBolsa();
        bolsa = 0;
    }

    public void limpiar(Zona zona) {
        if (zona == null) {
            return;
        }

        if (zona.getNivelSuciedad() > 0) {
            if (bateria.getCarga() > 0) {
                zona.limpiar();
                bolsa++;
                consumirBateria();

                if (bolsa == capacidadMaximaBolsa) {
                    vista.mostrarBolsaLlenaDuranteLimpieza();
                }
            } else {
                vista.mostrarBateriaAgotada();
            }
        } else {
            vista.mostrarZonaLimpia();
        }
    }

    public void consumirBateria() {
        bateria.consumir();
    }

    public void recargarBateria() {
        bateria.recargar();
    }

    public int obtenerCapacidadMaximaBolsa() {
        return capacidadMaximaBolsa;
    }

    public int obtenerBolsa() {
        return bolsa;
    }

    public int obtenerCargaBateria() {
        return bateria.getCarga();
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }
}
