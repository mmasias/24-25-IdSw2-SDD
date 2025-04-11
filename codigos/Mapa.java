import java.util.Random;

public class Mapa {
    private int anchoMapa = 25;
    private int altoMapa = 10;
    private Zona[][] mapaZonas;

    public Mapa(VistaConsola vista) {
        mapaZonas = new Zona[altoMapa][anchoMapa];
        inicializarMapa(vista);
    }

    private void inicializarMapa(VistaConsola vista) {
        Random random = new Random();

        for (int i = 0; i < altoMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                if (i == 0 && j == 0) {
                    mapaZonas[i][j] = new ZonaDeRecarga(vista);
                } else {
                    mapaZonas[i][j] = new Zona();
                }
            }
        }

        for (int i = 0; i < altoMapa; i++) {
            int j = 0;
            while (j < anchoMapa) {
                if (i == 0 && j == 0) {
                    j++;
                    continue;
                }

                if (random.nextDouble() < 0.1) {
                    int tamañoMueble = random.nextInt(4) + 1;

                    if (j + tamañoMueble <= anchoMapa) {
                        boolean espacioLibre = true;

                        for (int k = 0; k < tamañoMueble; k++) {
                            if (mapaZonas[i][j + k].tieneMueble()) {
                                espacioLibre = false;
                                break;
                            }
                        }

                        if (espacioLibre) {
                            Mueble mueble = new Mueble(tamañoMueble);
                            for (int k = 0; k < tamañoMueble; k++) {
                                mapaZonas[i][j + k].colocarMueble(mueble);
                            }
                            j += tamañoMueble;
                            continue;
                        }
                    }
                }
                j++;
            }
        }
    }

    public Zona obtenerZona(int x, int y) {
        if (x >= 0 && x < anchoMapa && y >= 0 && y < altoMapa) {
            return mapaZonas[y][x];
        }
        return null;
    }

    public boolean colocarMueble(int xInicio, int y, Mueble mueble) {
        if (xInicio + mueble.getTamañoMueble() > anchoMapa)
            return false;

        if (y == 0 && xInicio == 0)
            return false;

        for (int i = 0; i < mueble.getTamañoMueble(); i++) {
            Zona zona = mapaZonas[y][xInicio + i];
            if (zona.tieneMueble())
                return false;
        }

        for (int i = 0; i < mueble.getTamañoMueble(); i++) {
            mapaZonas[y][xInicio + i].colocarMueble(mueble);
        }

        return true;
    }

    public int getAnchoMapa() {
        return anchoMapa;
    }

    public int getAltoMapa() {
        return altoMapa;
    }

}
