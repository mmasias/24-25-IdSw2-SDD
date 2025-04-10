import java.util.Random;

public class Superficie {
    private int ancho = 25;
    private int alto = 10;
    private Zona[][] zonas;

    public Superficie(VistaConsola vista) {
        zonas = new Zona[alto][ancho];
        inicializarSuperficie(vista);
    }

    private void inicializarSuperficie(VistaConsola vista) {
        Random random = new Random();

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 && j == 0) {
                    zonas[i][j] = new ZonaRecarga(vista);
                } else {
                    zonas[i][j] = new Zona();
                }
            }
        }

        for (int i = 0; i < alto; i++) {
            int j = 0;
            while (j < ancho) {
                if (i == 0 && j == 0) {
                    j++;
                    continue;
                }

                if (random.nextDouble() < 0.1) {
                    int tamaño = random.nextInt(4) + 1;

                    if (j + tamaño <= ancho) {
                        boolean espacioLibre = true;

                        for (int k = 0; k < tamaño; k++) {
                            if (zonas[i][j + k].tieneMueble()) {
                                espacioLibre = false;
                                break;
                            }
                        }

                        if (espacioLibre) {
                            Mueble mueble = new Mueble(tamaño);
                            for (int k = 0; k < tamaño; k++) {
                                zonas[i][j + k].colocarMueble(mueble);
                            }
                            j += tamaño;
                            continue;
                        }
                    }
                }
                j++;
            }
        }
    }

    public Zona obtenerZona(int x, int y) {
        if (x >= 0 && x < ancho && y >= 0 && y < alto) {
            return zonas[y][x];
        }
        return null;
    }

    public boolean colocarMueble(int xInicio, int y, Mueble mueble) {
        if (xInicio + mueble.getTamaño() > ancho)
            return false;

        if (y == 0 && xInicio == 0)
            return false;

        for (int i = 0; i < mueble.getTamaño(); i++) {
            Zona zona = zonas[y][xInicio + i];
            if (zona.tieneMueble())
                return false;
        }

        for (int i = 0; i < mueble.getTamaño(); i++) {
            zonas[y][xInicio + i].colocarMueble(mueble);
        }

        return true;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

}
