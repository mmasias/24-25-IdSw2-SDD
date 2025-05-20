package v002.modelo.entidades;
import java.util.*;

import v002.modelo.interfaces.UnidadConMovimiento;
import v002.vista.VistaConsola;
import v002.modelo.mapa.Habitacion;
import v002.modelo.mapa.Zona;
import v002.modelo.mapa.ZonaDeRecarga;

public class Aspiradora implements UnidadConMovimiento {

    private int bolsa;
    private final int capacidadMaximaBolsa = 100;
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

    public void actuar(Habitacion habitacion) {
        if (bateria.getCarga() <= 1 || bolsa >= capacidadMaximaBolsa) {
            if (bolsa >= capacidadMaximaBolsa) {
                vista.mostrarBolsaLlena();
            } else {
                vista.mostrarBateriaBaja();
            }
            buscarYRecargar(habitacion);
            return;
        }

        calcularMovimiento(habitacion);
        Zona zonaActual = habitacion.getZona(posicionX, posicionY);
        limpiar(zonaActual);
    }
    
    @Override
    public void calcularMovimiento(Habitacion habitacion) {
        int maxNivel = -1;
        int objetivoX = posicionX;
        int objetivoY = posicionY;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 25; x++) {
                Zona zona = habitacion.getZona(x, y);
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

        List<int[]> ruta = calcularRuta(habitacion, objetivoX, objetivoY);
        if (ruta != null && !ruta.isEmpty()) {
            int[] siguientePaso = ruta.get(0);
            mover(siguientePaso[0], siguientePaso[1]);
        }
    }

    @Override
    public void mover(int nuevaX, int nuevaY) {
        this.posicionX = nuevaX;
        this.posicionY = nuevaY;
    }

    @Override
    public int getPosicionX() {
        return posicionX;
    }

    @Override
    public int getPosicionY() {
        return posicionY;
    }

    private List<int[]> calcularRuta(Habitacion habitacion, int destinoX, int destinoY) {
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
                Zona z = habitacion.getZona(nx, ny);

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

    private void buscarYRecargar(Habitacion habitacion) {
        int destinoX = -1, destinoY = -1;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 25; x++) {
                Zona zona = habitacion.getZona(x, y);
                if (esZonaDeRecarga(zona)) {
                    destinoX = x;
                    destinoY = y;
                    break;
                }
            }
            if (destinoX != -1)
                break;
        }

        moverHaciaZona(habitacion, destinoX, destinoY);
    }

    private boolean esZonaDeRecarga(Zona zona) {
        return zona instanceof ZonaDeRecarga;
    }

    private void moverHaciaZona(Habitacion habitacion, int destinoX, int destinoY) {
        List<int[]> ruta = calcularRuta(habitacion, destinoX, destinoY);
        if (ruta != null && !ruta.isEmpty()) {
            int[] siguientePaso = ruta.get(0);
            mover(siguientePaso[0], siguientePaso[1]);

            Zona zonaActual = habitacion.getZona(posicionX, posicionY);
            if (zonaActual instanceof ZonaDeRecarga) {
                ((ZonaDeRecarga) zonaActual).recargar(this);
                if (bolsa == capacidadMaximaBolsa) {
                    vaciarBolsa();
                }
            }
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
                    vista.mostrarBolsaLlena();
                }
            }
        }
    }

    public void consumirBateria() {
        bateria.consumir();
    }

    public void recargarBateria() {
        bateria.recargar();
    }

    public int getCapacidadMaximaBolsa() {
        return capacidadMaximaBolsa;
    }

    public int getBolsa() {
        return bolsa;
    }

    public int getCargaBateria() {
        return bateria.getCarga();
    }
}
